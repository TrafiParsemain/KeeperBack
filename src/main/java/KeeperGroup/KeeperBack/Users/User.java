package KeeperGroup.KeeperBack.Users;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "user",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(name = "username", columnNames = {"username"})
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",updatable = false)
    private Long id;

    @Column(name="username")
    private String username;

    private String password;
    private String role;
    private Date registrationDate;

    protected User(){

    }

    public User(Long id, String username, String password, String role, Date registrationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}