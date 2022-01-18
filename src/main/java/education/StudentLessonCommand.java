package education;

public interface StudentLessonCommand {


     String EXIT1 = "0";
     String ADD_LESSON = "3";
     String ADD_STUDENT = "4";
     String PRINT_STUDENTS = "5";
     String PRINT_STUDENTS_BY_LESSON = "6";
     String PRINT_LESSONS = "7";
     String DELETE_LESSON_BY_NAME = "8";
     String DELETE_STUDENT_BY_EMAIL = "9";


     static void printCommands() {
        System.out.println("\u001B[34m" + "please input " + EXIT1 + " for exit");
        System.out.println("please input " + ADD_LESSON + " for add lesson");
        System.out.println("please input " + ADD_STUDENT + " for add student");
        System.out.println("please input " + PRINT_STUDENTS + " for print students");
        System.out.println("please input " + PRINT_STUDENTS_BY_LESSON + " for print students by lesson");
        System.out.println("please input " + PRINT_LESSONS + " for print lessons");
        System.out.println("please input " + DELETE_LESSON_BY_NAME + " for delete lesson by name");
        System.out.println("please input " + DELETE_STUDENT_BY_EMAIL + " for delete student by email" + "\u001B[0m");
    }

    static void printCommands1(){
        System.out.println("\u001B[34m" + "please input " + EXIT1 + " for exit");
        System.out.println("please input " + ADD_LESSON + " for add lesson");
        System.out.println("please input " + ADD_STUDENT + " for add student");
        System.out.println("please input " + PRINT_STUDENTS + " for print students");
        System.out.println("please input " + PRINT_STUDENTS_BY_LESSON + " for print students by lesson");
        System.out.println("please input " + PRINT_LESSONS + " for print lessons" + "\u001B[34m");

    }
}
