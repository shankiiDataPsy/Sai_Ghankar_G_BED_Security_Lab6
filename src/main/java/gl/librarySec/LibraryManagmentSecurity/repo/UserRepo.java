package gl.librarySec.LibraryManagmentSecurity.repo;

import gl.librarySec.LibraryManagmentSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUname(String name);
}
