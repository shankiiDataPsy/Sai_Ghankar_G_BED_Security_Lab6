package gl.librarySec.LibraryManagmentSecurity.controller;

import gl.librarySec.LibraryManagmentSecurity.entity.Role;
import gl.librarySec.LibraryManagmentSecurity.entity.User;
import gl.librarySec.LibraryManagmentSecurity.repo.RoleRepo;
import gl.librarySec.LibraryManagmentSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyDataBooks implements CommandLineRunner {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    public void saveRoles(){
        Role userRole = new Role();
        userRole.setRname("USER");

        Role adminRole = new Role();
        adminRole.setRname("ADMIN");

        roleRepo.save(userRole);
        roleRepo.save(adminRole);
    }

    public void saveUsers(){

// ---------------------------------PASSWORDS-----------------------------------------------

//        admin -> { admin }
//        "$2y$10$SHmrUuRjH6ZP/umJr/9QFuBORJxQUgMd/n1VPlhoq48Tz6Jj2NYoq"

//        password -> { Suresh, Tanya  Murali Daniel}
//        "$2y$10$05QvCkhS/Wy2Mjn70TrNt.lGtaCax9jXx1rVNrnPPKtPPgjjOztGS"

//        user -> {user}
//        "$2y$10$RSySLZyP2Tyd8YYk9kuUh.0/rAgP8Ka81er02WdRFyruhSJriWlFi"
// -----------------------------------------------------------------------------------------
        List<User> users = List.of(
                new User("admin","$2y$10$SHmrUuRjH6ZP/umJr/9QFuBORJxQUgMd/n1VPlhoq48Tz6Jj2NYoq", "Sai", "Shankar", "M.Tech", "India", roleRepo.findAll()),
                new User("Suresh","$2y$10$05QvCkhS/Wy2Mjn70TrNt.lGtaCax9jXx1rVNrnPPKtPPgjjOztGS", "Suresh", "Reddy", "India", "B.Tech", List.of(roleRepo.findAll().get(0))),
                new User("Murali","$2y$10$05QvCkhS/Wy2Mjn70TrNt.lGtaCax9jXx1rVNrnPPKtPPgjjOztGS", "Murali ", "Mohan", "B.Arch", "Canada", List.of(roleRepo.findAll().get(0))),
                new User("Tanya","$2y$10$05QvCkhS/Wy2Mjn70TrNt.lGtaCax9jXx1rVNrnPPKtPPgjjOztGS", "Tanya", "Gupta", "B.Com", "USA", List.of(roleRepo.findAll().get(0))),
                new User("user","$2y$10$RSySLZyP2Tyd8YYk9kuUh.0/rAgP8Ka81er02WdRFyruhSJriWlFi", "User FName", "User LName", "Demo Course", "Demo Country", List.of(roleRepo.findAll().get(0))),
                new User("Daniel","$2y$10$05QvCkhS/Wy2Mjn70TrNt.lGtaCax9jXx1rVNrnPPKtPPgjjOztGS", "Daniel", "Denson", "B.Tech", "New Zealand", List.of(roleRepo.findAll().get(0)))
        );

        System.out.println(
                """
                    
                    ##############   PASSWORDS   ##############
                    
                    admin -> { admin }
                    "$2y$10$SHmrUuRjH6ZP/umJr/9QFuBORJxQUgMd/n1VPlhoq48Tz6Jj2NYoq"
                                        
                    password -> { Suresh, Tanya, Murali, Daniel }
                    "$2y$10$05QvCkhS/Wy2Mjn70TrNt.lGtaCax9jXx1rVNrnPPKtPPgjjOztGS"
                                        
                    user -> {user}
                    "$2y$10$RSySLZyP2Tyd8YYk9kuUh.0/rAgP8Ka81er02WdRFyruhSJriWlFi"
                """
        );



        users.stream()
                .map(userIter -> userRepo.save(userIter))
                .forEach(System.out::println);
    }

    @Override
    public void run(String... args) throws Exception {
        saveRoles();
        saveUsers();
//        System.out.println(userRepo.findAll());
    }
}
