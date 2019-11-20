package resu.muffin.users_and_comments.users.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import resu.muffin.users_and_comments.users.db.RoleRepository;
import resu.muffin.users_and_comments.users.db.UserRepository;
import resu.muffin.users_and_comments.users.entities.User;

@Service
public class UserService{
    private static final short ROLE_ADMIN = 1;
    private static final short ROLE_USER = 2;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder pwEncoder;

    public boolean registerUser(String email, String username, String password) {
        if(!userRepo.existsByEmail(email) && !userRepo.existsByUsername(username)) {
            User user = new User(email, username, pwEncoder.encode(password));
            user.setRole(roleRepo.findById(ROLE_USER).get());
            userRepo.save(user);
            return true;
        }
        return false;
    }


    // logging in
    public boolean authenticate(String email, String username, String password) {
        User user;
        user = userRepo.findByEmail(email).orElse(null);
        if(user == null) {
            user = userRepo.findByUsername(username).orElse(null);
            if(user == null)
                return false;
        }
        return pwEncoder.matches(password, user.getPassword());
    }

    public User getUser(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

}