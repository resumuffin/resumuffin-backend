package in.resumuff.core.users.api;

import javax.servlet.http.HttpSession;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import in.resumuff.core.users.entities.User;
import in.resumuff.core.users.logic.UserService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping(value="/users/addUser/{email}/{username}/{password}")
    @ApiOperation(value="SUP")
    boolean registerUser(@PathVariable String email, @PathVariable String username, @PathVariable String password) {
        email = Encode.forJava(email);
        username = Encode.forJava(username);
        password = Encode.forJava(password);
        return userService.registerUser(email, username, password);
    }

    @GetMapping(value="/users/authenticate/{userOrEmail}/{password}")
    boolean authenticate(@ApiIgnore HttpSession session, @PathVariable String userOrEmail, @PathVariable String password) {
        userOrEmail = Encode.forJava(userOrEmail);
        password = Encode.forJava(password);
        return userService.authenticate(session, userOrEmail, password);
    } 

    @GetMapping(value="/users/getUserDetails/{username}")
    User getUser(@PathVariable String username) {
        username = Encode.forJava(username);
        return userService.getUserDetails(username);
    }

    @GetMapping(value="/users/getUserDetailsFull/{username}")
    User getUser(@ApiIgnore HttpSession session, @PathVariable String username) {
        username = Encode.forJava(username);
        return userService.getUserDetailsFull(session, username);
    }

}