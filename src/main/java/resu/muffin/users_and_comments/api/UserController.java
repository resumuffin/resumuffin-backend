package resu.muffin.users_and_comments.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import resu.muffin.users_and_comments.access.UserRepo;
import resu.muffin.users_and_comments.entities.User;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getUser")
    @ResponseBody
    User getUser() {
        User user = new User();
        return user;    
    }

    @GetMapping("/addUser")
    void addUser(User user) {
        userRepo.save(user);
    }
}