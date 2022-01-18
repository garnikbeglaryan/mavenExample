package education.util;

import education.model.EntryType;
import java.io.*;
import java.util.Collection;
import java.util.Collections;

public class FileUtil {

    private static final String USERS_FILE = "C:\\Users\\Dell\\IdeaProjects\\untitled\\maven\\mavenExample\\src\\main\\java\\education\\data\\";
    private static final String EXTENSION = "txt";

    public static void addEntry(Collection objects, EntryType entryType) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(getPath(entryType)))) {
            objectOutputStream.writeObject(objects);
        }
    }

    public static Collection getEntries(EntryType entryType) throws IOException, ClassNotFoundException {
        Collection result;
        File file = new File(getPath(entryType));
        if (!file.exists()) {
            file.createNewFile();
        }

        if (file.length() == 0) {
            return Collections.EMPTY_LIST;
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            result = (Collection) objectInputStream.readObject();
        }
        return result;
    }

    private static String getPath(EntryType entryType) {
        String pathToAdd = USERS_FILE;
        switch (entryType) {
            case USER:
                pathToAdd += "users";
                break;
            case STUDENT:
                pathToAdd += "students";
                break;
            case LESSON:
                pathToAdd += "lessons";
                break;
        }
        return pathToAdd + ("." + EXTENSION);
    }
}
