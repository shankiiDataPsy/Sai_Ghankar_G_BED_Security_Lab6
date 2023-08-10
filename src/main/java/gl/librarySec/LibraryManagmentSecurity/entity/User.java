package gl.librarySec.LibraryManagmentSecurity.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
@NoArgsConstructor
@Table(name = "user_table")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uname;
    private String pwd;
    private String fname;
    private String lname;
    private String course;
    private String country;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<Role>();

    public User(String uname, String pwd, String fname, String lname, String course, String country, List<Role> roles) {
        this.uname = uname;
        this.pwd = pwd;
        this.fname = fname;
        this.lname = lname;
        this.course = course;
        this.country = country;
        this.roles = roles;
    }
}
