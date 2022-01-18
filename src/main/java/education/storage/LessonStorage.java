package education.storage;

import education.LessonStudentTest;
import education.model.EntryType;
import education.util.ArrayUtil;
import education.model.Lesson;
import education.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LessonStorage {

    List<Lesson> lessons = new ArrayList<>();


    public void add(Lesson lesson) {

        lessons.add(lesson);

    }


    public void print() {
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }

    public Lesson getByName(String name) {
        for (Lesson lesson : lessons) {
            if (lesson.getName().equals(name)) {
                return lesson;
            }
        }
        return null;
    }

    public void deleteByLesson(String name) {
        Lesson lessonByName = getByName(name);
        if (lessonByName != null) {
            lessons.remove(lessonByName);
        }
    }

    public List<Lesson> getLessonsByNames(String[] names) {
        List<Lesson> result = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Lesson lessonByName = getByName(names[i]);
            if (lessonByName != null) {
                result.add(lessonByName);
            }
        }
        return lessons;
    }

    public void initData() throws IOException, ClassNotFoundException {
        Collection lessonsFromFile = FileUtil.getEntries(EntryType.LESSON);
        for (Object fileLesson : lessonsFromFile) {
            lessons.add((Lesson) fileLesson);
        }
    }

    public void insertDataToFile() {
        try {
            FileUtil.addEntry(lessons, EntryType.LESSON);
        } catch (Exception ex) {
            System.err.println("Exception during lessons data saving to file");
        }
    }

}
