package education.model;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUid = 1l;

    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private List<Lesson> lessons;

}
