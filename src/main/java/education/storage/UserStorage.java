package education.storage;

import education.exception.UserNotFoundException;
import education.model.EntryType;
import education.model.Student;
import education.model.User;
import education.util.FileUtil;

import java.io.IOException;
import java.util.*;

public class UserStorage {

    private Map<String, User> userMap = new HashMap<>();


    public void add(User user) {
        userMap.put(user.getEmail(), user);

    }


    public void print() {
        for (User value : userMap.values()) {
            System.out.println(value);
        }
    }

    public User getByEmail(String email) throws UserNotFoundException {

        User user = userMap.get(email);
        if (user == null) {
            throw new UserNotFoundException("User does not exists . email:" + email);
        }

        return user;

    }

    public void initData() throws IOException, ClassNotFoundException {
        Collection usersFromFile = FileUtil.getEntries(EntryType.USER);
        for (Object user : usersFromFile) {
            User newUser = (User) user;
            userMap.put(newUser.getEmail(), newUser);
        }
    }

    public void insertDataToFile() {
        List<User> users = new ArrayList<>();
        for (User value : userMap.values()) {
            users.add(value);
        }
        try {
            FileUtil.addEntry(users, EntryType.USER);
        } catch (Exception ex) {
            System.err.println("Exception during user data saving to file");
        }
    }
}
