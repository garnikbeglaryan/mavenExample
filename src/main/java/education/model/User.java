package education.model;
import lombok.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUid = 1l;

    private String name;
    private String surname;
    private String email;
    private String password;
    private UserType type;

}
