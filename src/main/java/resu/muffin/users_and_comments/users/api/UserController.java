package resu.muffin.users_and_comments.users.api;

import javax.servlet.http.HttpSession;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import resu.muffin.users_and_comments.users.entities.User;
import resu.muffin.users_and_comments.users.logic.UserService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping(value="/addUser")
    @ApiOperation(value="SUP")
    boolean registerUser(String email, String username, String password) {
        email = Encode.forJava(email);
        username = Encode.forJava(username);
        password = Encode.forJava(password);
        return userService.registerUser(email, username, password);
    }

    @GetMapping(value="/authenticate")
    boolean authenticate(@ApiIgnore HttpSession session, String email, String username, String password) {
        email = Encode.forJava(email);
        username = Encode.forJava(username);
        password = Encode.forJava(password);
        return userService.authenticate(session, email, username, password);
    } 

    @GetMapping(value="/getUser")
    User getUser(HttpSession session, String username) {
        username = Encode.forJava(username);
        return userService.getUserDetails(session, username);
    }
}