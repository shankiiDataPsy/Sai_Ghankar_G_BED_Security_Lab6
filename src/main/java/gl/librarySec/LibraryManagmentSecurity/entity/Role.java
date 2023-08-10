package gl.librarySec.LibraryManagmentSecurity.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "role_table")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String rname;

}
