package education.storage;

import education.LessonStudentTest;
import education.model.EntryType;
import education.model.Lesson;
import education.model.Student;
import education.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StudentStorage {

    List<Student> students = new ArrayList<>();

    public void add(Student student) {

        students.add(student);
    }


    public void print() {
        for (Student student : students) {
            System.out.println(student);
        }
    }


    public Student getByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }


    public void deleteByStudent(String email) {
        Student studentByEmail = getByEmail(email);
        if (studentByEmail != null) {
            students.remove(studentByEmail);
        }
    }


    private void delete(int index) {
        students.remove(index);
    }

    public void printByLesson(Lesson lesson) {
        for (Student student : students) {
            if (student.getLessons().contains(lesson)) {
                System.out.println(student);
                break;
            }
        }
    }

    public void initData() throws IOException, ClassNotFoundException {
        Collection studentsFromFile = FileUtil.getEntries(EntryType.STUDENT);
        for (Object student : studentsFromFile) {
            students.add((Student) student);
        }
    }

    public void insertDataToFile() {
        try {
            FileUtil.addEntry(students, EntryType.STUDENT);
        } catch (Exception ex) {
            System.err.println("Exception during students data saving to file");
        }
    }
}