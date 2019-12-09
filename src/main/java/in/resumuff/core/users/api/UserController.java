package in.resumuff.core.users.api;

import javax.servlet.http.HttpSession;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @ApiOperation(value="Registers a new user into the database")
    boolean registerUser(@PathVariable String email, @PathVariable String username, @PathVariable String password) {
        email = Encode.forJava(email);
        username = Encode.forJava(username);
        password = Encode.forJava(password);
        return userService.registerUser(email, username, password);
    }

    @GetMapping(value="/users/authenticate/{userOrEmail}/{password}")
    @ApiOperation(value="Authenticates login info and fills in session variables")
    boolean authenticate(@ApiIgnore HttpSession session, @PathVariable String userOrEmail, @PathVariable String password) {
        userOrEmail = Encode.forJava(userOrEmail);
        password = Encode.forJava(password);
        return userService.authenticate(session, userOrEmail, password);
    } 

    @GetMapping(value="/users/getUserDetails/{username}")
    @ApiOperation(value="Gets a user's details (Should display non-sensitive info only)")
    User getUser(@PathVariable String username) {
        username = Encode.forJava(username);
        return userService.getUserDetails(username);
    }

    @ApiOperation(value="Gets a user's complete details EX: When a user views themself, uses sessions for security")
    @GetMapping(value="/users/getUserDetailsFull/{username}")
    User getUser(@ApiIgnore HttpSession session, @PathVariable String username) {
        username = Encode.forJava(username);
        return userService.getUserDetailsFull(session, username);
    }

    @ApiOperation(value="Gets all users in the database")
    @GetMapping(value="/users/get/all")
    Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value="Gets a page of users from the database, able to set page length")
    @GetMapping(value="/users/get/page/{pageNum}/{pageLen}")
    Page<User> getUsers(@PathVariable int pageNum, @PathVariable int pageLen) {
        return userService.getUsers(pageNum, pageLen);
    }

}