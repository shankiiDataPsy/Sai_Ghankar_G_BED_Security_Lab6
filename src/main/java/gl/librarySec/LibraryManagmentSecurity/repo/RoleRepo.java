package gl.librarySec.LibraryManagmentSecurity.repo;

import gl.librarySec.LibraryManagmentSecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
