package in.resumuff.core.users.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import in.resumuff.core.users.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.resumuff.core.users.db.RoleRepository;
import in.resumuff.core.users.db.UserRepository;
import in.resumuff.core.users.entities.User;

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
        if(userRepo.existsByEmail(email) || userRepo.existsByUsername(username))
            return false;
        
        Role userRole = roleRepo.findById(ROLE_USER).orElseThrow(IllegalStateException::new);
        User user = new User(email, username, pwEncoder.encode(password));
        user.setRole(userRole);
        
        userRepo.save(user);
        return true;
    }

    // logging in
    public User authenticate(HttpSession session, String userOrEmail, String password) {
        Optional<User> user;
        if(userOrEmail.contains("@")){
            user = userRepo.findByEmail(userOrEmail);
        } else {
            user = userRepo.findByUsername(userOrEmail);
        }
        
        // no user found by that user/email
        if(!user.isPresent())
            return null;
        
        User userObj = user.get();
        // if passwords don't match, don't continue
        if(!pwEncoder.matches(password, userObj.getPassword()))
            return null;
        
        // generate and set our token and uid
        userObj.setAccessToken(StringGenerator.generate(32));
        session.setAttribute("TOKEN", userObj.getAccessToken());
        session.setAttribute("USER_ID", userObj.getId());
        // update the user object in db
        userRepo.save(userObj);
        return userObj;
        
    }
    
    public User getUserDetails(String username) {
        Optional<User> found = userRepo.findByUsername(username);
        return found.map(this::censorUserInfo).orElse(null);
    }
    
    public User getUserDetails(Long id) {
        Optional<User> found = userRepo.findById(id);
        return found.map(this::censorUserInfo).orElse(null);
    }

    public User getUserDetailsFull(HttpSession session) {
        if(!isSessionValid(session))
            return null;
        
        return userRepo.findById((Long)session.getAttribute("USER_ID")).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepo.findAllByOrderByIdAsc();
    }

    public Page<User> getUsers(int pageNum, int pageLen) {
        return userRepo.findAll(PageRequest.of(pageNum, pageLen));
    }
    
    public boolean isSessionValid(HttpSession session){
        String token = (String)session.getAttribute("TOKEN");
        Long userId = (Long)session.getAttribute("USER_ID");
        
        if(token == null || userId == null)
            return false;
        
        Optional<User> user = userRepo.findById(userId);
        return user.isPresent() && user.get().getAccessToken().equals(token);
    }
    
    private User censorUserInfo(User userObj){
        // as long as we don't save this back to db, we're fine to set it in-memory
        userObj.setAccessToken("");
        userObj.setPassword("");
        return userObj;
    }
    
}