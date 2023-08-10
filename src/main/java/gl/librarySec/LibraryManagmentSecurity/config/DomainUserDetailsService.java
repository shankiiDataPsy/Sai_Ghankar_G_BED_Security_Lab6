package gl.librarySec.LibraryManagmentSecurity.config;

import gl.librarySec.LibraryManagmentSecurity.entity.User;
import gl.librarySec.LibraryManagmentSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser =  this.repo.findByUname(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("invalid username given");
        }
        DomainUserDetails userDetails = new DomainUserDetails(optionalUser.get());
        return userDetails;
    }
}
