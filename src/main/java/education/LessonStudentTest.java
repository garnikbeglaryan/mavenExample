package education;

import education.exception.UserNotFoundException;
import education.model.Lesson;
import education.model.Student;
import education.model.User;
import education.model.UserType;
import education.storage.LessonStorage;
import education.storage.StudentStorage;
import education.storage.UserStorage;
import education.util.DateUtil;

import java.text.ParseException;
import java.util.*;

public class LessonStudentTest implements StudentLessonCommand, UserCommand {

    static Scanner scanner = new Scanner(System.in);
    static LessonStorage lessonStorage = new LessonStorage();
    static StudentStorage studentStorage = new StudentStorage();
    static UserStorage userStorage = new UserStorage();


    public static void main(String[] args) throws Exception {

//        studentStorage.add(new Student("poxos", "poxosyan", 22, "poxos@mail.ru", "0987776655", "cragravorum"));
//        studentStorage.add(new Student("petros", "petrosyan", 23, "petros@mail.ru", "097665544", "matem"));
//        studentStorage.add(new Student("martiros", "martirosyan", 25, "martiros@mail.ru", "094558800", "english"));
        boolean isRun = true;

        while (isRun) {
            initAllStorages();
            UserCommand.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTOR:
                    registor();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void initAllStorages() throws Exception {
        lessonStorage.initData();
        studentStorage.initData();
        userStorage.initData();
    }

    private static void registor() {
        System.out.println("Please input email");
        String email = scanner.nextLine();

        try {
            User byEmail = userStorage.getByEmail(email);
            System.out.println("there is such a user");

        } catch (UserNotFoundException e) {
            System.out.println("please input name");
            String name = scanner.nextLine();
            System.out.println("please input surname");
            String surname = scanner.nextLine();
            System.out.println("please input password");
            String password = scanner.nextLine();
            System.out.println("please input type");
            String type = scanner.nextLine();
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setPassword(password);
            user.setType(UserType.valueOf(type.toUpperCase()));
            userStorage.add(user);
            userStorage.insertDataToFile();
            System.out.println("User was registered");
        }
    }


    private static void login() throws ParseException {
        System.out.println("please input email");
        String email = scanner.nextLine();
        User user;
        try {
            user = userStorage.getByEmail(email);
            System.out.println("please input password");
            String password = scanner.nextLine();
            if (user.getPassword().equals(password)) {
                if (user.getType() == UserType.USER) {
                    printCommandsUser();
                }
                if (user.getType() == UserType.ADMIN) {
                    printCommandsForAdmin();
                }
            } else {
                System.err.println("Invalid password");
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printCommandsForAdmin() throws ParseException {
        boolean isCommand = true;

        while (isCommand) {
            StudentLessonCommand.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT1:
                    isCommand = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentByLesson();
                    break;
                case DELETE_LESSON_BY_NAME:
                    deleteLessonByName();
                    break;
                case DELETE_STUDENT_BY_EMAIL:
                    deleteStudentByEmail();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.err.println("Invalid command");
            }
            lessonStorage.insertDataToFile();
            studentStorage.insertDataToFile();
        }
    }


    private static void printCommandsUser() throws ParseException {

        boolean isCommand = true;

        while (isCommand) {

            StudentLessonCommand.printCommands1();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT1:
                    isCommand = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentByLesson();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.err.println("Invalid command");
            }
            lessonStorage.insertDataToFile();
            studentStorage.insertDataToFile();
        }
    }

    private static void deleteStudentByEmail() {
        System.out.println("please choose by email");
        System.out.println("------------");
        studentStorage.print();
        System.out.println("------------");
        String email = scanner.nextLine();
        studentStorage.deleteByStudent(email);
    }

    private static void deleteLessonByName() {
        System.out.println("please choose by name");
        System.out.println("----------");
        lessonStorage.print();
        System.out.println("----------");
        String name = scanner.nextLine();
        lessonStorage.deleteByLesson(name);
    }

    private static void printStudentByLesson() {
        System.out.println("please choose by Lesson");
        String lessonName = scanner.nextLine();
        Lesson lesson = lessonStorage.getByName(lessonName);

        if (lesson != null) {
            studentStorage.printByLesson(lesson);
        } else {
            System.err.println("Lesson does not exists");
        }
    }

    private static void addStudent() throws ParseException {
        System.out.println("please input lesson name");
        String lessonByName = scanner.nextLine();
        String[] lessonByNames = lessonByName.split(",");
        List<Lesson> lessons = lessonStorage.getLessonsByNames(lessonByNames);
        System.out.println("please input student's name,surname,age,email,phone,dataOfBirth[12/07/2007]");
        String studentDataStr = scanner.nextLine();
        String[] studentData = studentDataStr.split(",");
        if (studentData.length == 6) {

            int age = Integer.parseInt(studentData[2]);
            Date date = DateUtil.stringToDate(studentData[5]);

            Student student = new Student(studentData[0], studentData[1], age, studentData[3], studentData[4], date, lessons);

            if (studentStorage.getByEmail(student.getEmail()) != null) {
                System.err.println("Invalid email. Author with this email already exists");
            } else {
                studentStorage.add(student);
                System.out.println("Student was aded");
            }
        } else {
            System.err.println("Invalid data");
        }
    }

    private static void addLesson() {

        System.out.println("please input name");
        String name = scanner.nextLine();
        if (lessonStorage.getByName(name) == null) {
            System.out.println("please input duration");
            String duration = scanner.nextLine();
            System.out.println("please input lecturerName");
            String lecturerName = scanner.nextLine();
            System.out.println("please input price");
            int price = Integer.parseInt(scanner.nextLine());


            lessonStorage.add(new Lesson(name, duration, lecturerName, price));
            System.out.println("Lesson was aded");

        } else {
            System.err.println("Invalid name! lesson with this name alreade exists");
            addLesson();
        }
    }
}


