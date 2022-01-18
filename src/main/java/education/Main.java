package education;

import education.model.*;
import education.util.FileUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
//        User user1 = new User("test1","test1111","test1232","testt", UserType.USER);
//        User user = new User("test","test","email","test", UserType.USER);
        List<User> users = new ArrayList<>();
        Lesson lesson = new Lesson("test","test","test",123);
        Student student  = new Student("test","test",12,"test","test",new Date(), Collections.singletonList(lesson));

//        users.add(user);
//        users.add(user1);
//        FileUtil.addEntry(users, EntryType.USER);

        Collection entries = FileUtil.getEntries(EntryType.USER);
        System.out.println(entries);


    }
}
