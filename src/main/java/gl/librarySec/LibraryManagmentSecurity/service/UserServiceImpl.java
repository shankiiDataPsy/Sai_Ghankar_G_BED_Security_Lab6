package gl.librarySec.LibraryManagmentSecurity.service;

import gl.librarySec.LibraryManagmentSecurity.entity.User;
import gl.librarySec.LibraryManagmentSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo repo;

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public User getUserById(long id) {
        return repo.findById(id).get();
    }

    @Override
    public User putUserById(long id, User user) {
        return repo.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        repo.deleteById(id);
    }
}
