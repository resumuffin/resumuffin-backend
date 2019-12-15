package in.resumuff.core.users.api;

import in.resumuff.core.users.entities.User;
import in.resumuff.core.users.logic.UserService;
import io.swagger.annotations.ApiOperation;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping(value = "/users/addUser")
    @ApiOperation(value = "Registers a new user into the database, sets session variables")
    boolean registerUser(@RequestBody Map<String, String> json){
        String email = Encode.forJava(json.get("email"));
        String username = Encode.forJava(json.get("username"));
        String password = Encode.forJava(json.get("password"));
        return userService.registerUser(email, username, password);
    }
    
    @PostMapping(value = "/users/authenticate")
    @ApiOperation(value = "Authenticates login info and fills in session variables")
    User authenticate(@ApiIgnore HttpSession session, @RequestBody Map<String, String> json){
        String userOrEmail = Encode.forJava(json.get("username"));
        String password = Encode.forJava(json.get("password"));
        return userService.authenticate(session, userOrEmail, password);
    }
    
    @GetMapping(value = "/users/getUserDetailsByName/{username}")
    @ApiOperation(value = "Gets a user's details by username (Should display non-sensitive info only)")
    User getUser(@PathVariable String username){
        username = Encode.forJava(username);
        return userService.getUserDetails(username);
    }
    
    @GetMapping(value = "/users/getUserDetailsById/{id}")
    @ApiOperation(value = "Gets a user's details by id (Should display non-sensitive info only)")
    User getUserById(@PathVariable Long id){
        return userService.getUserDetails(id);
    }
    
    @ApiOperation(value = "Gets a user's complete details EX: When a user views themself, uses sessions for security")
    @GetMapping(value = "/users/getUserDetailsFull")
    User getUserFull(@ApiIgnore HttpSession session){
        return userService.getUserDetailsFull(session);
    }
    
    @ApiOperation(value = "Gets all users in the database")
    @GetMapping(value = "/users/get/all")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    
    @ApiOperation(value = "Gets a page of users from the database, able to set page length")
    @GetMapping(value = "/users/get/page/{pageNum}/{pageLen}")
    Page<User> getUsers(@PathVariable int pageNum, @PathVariable int pageLen){
        return userService.getUsers(pageNum, pageLen);
    }
    
}