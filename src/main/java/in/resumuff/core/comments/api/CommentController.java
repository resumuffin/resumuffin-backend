package in.resumuff.core.comments.api;

import in.resumuff.core.comments.entities.Comment;
import in.resumuff.core.comments.logic.CommentService;
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
public class CommentController {
    
    @Autowired
    CommentService commentService;
    
    @GetMapping("/comment/getComment/{id}")
    @ApiOperation(value = "Get a comment from the database based on id")
    public Comment getComment(@PathVariable long id){
        return commentService.getComment(id);
    }
    
    @ApiOperation(value = "Get a thread from the database based on resume id")
    @GetMapping("/comment/getThread/{resumeId}")
    public List<Comment> getThread(@PathVariable long resumeId){
        return commentService.getThread(resumeId);
    }
    
    @ApiOperation(value = "Get a page of threads from the database, able to set page length")
    @GetMapping(value = "/comment/get/page/{pageNum}/{pageLen}")
    public Page<Comment> getThreads(@PathVariable int pageNum, @PathVariable int pageLen){
        return commentService.getThreads(pageNum, pageLen);
    }
    
    @ApiOperation(value = "Get all threads from the database based on a userId")
    @GetMapping(value = "/comment/get/user/{userId}")
    public List<Comment> getThreadsByUser(@PathVariable long userId){
        return commentService.getThreadsByUser(userId);
    }
    
    /*
    // check if allowed to create thread
    @ApiOperation(value="Creates a thread, checks if the user has sufficient privileges, uses sessions for security")
    @PostMapping("/comment/createThread/{userId}/{resumeId}/{subject}/{content}")
    public Comment createThread(@ApiIgnore HttpSession session, @PathVariable long resumeId, @PathVariable String title, @PathVariable String description) {
        return commentService.createThread(session, resumeId, title, description);
    }
    */
    @ApiOperation(value = "Creates a comment using a resumeId and a description, checks if the user has sufficient privileges, uses sessions for security")
    @PostMapping(value = "/comment/createComment", consumes = "application/json")
    public Comment addComment(@ApiIgnore HttpSession session, @RequestBody Map<String, Object> json){
        long resumeId = Long.parseLong(json.get("resumeId").toString());
        String description = Encode.forJava((String) json.get("description"));
        return commentService.createComment(session, resumeId, description);
    }
    
    @ApiOperation(value = "Deletes a comment, checks if the user has sufficient privileges, uses sessions for security")
    @DeleteMapping("/comment/deleteComment/{id}")
    public void deleteComment(@ApiIgnore HttpSession session, @PathVariable String id){
        commentService.deleteComment(session, Long.parseLong(id));
    }
    
    // need to make sure user can only rate once
    @ApiOperation(value = "Upvotes a comment, checks if the user has sufficient privileges, uses sessions for security")
    @GetMapping(value = "/comment/upvoteComment", consumes = "application/json")
    public void upvoteComment(@ApiIgnore HttpSession session, @RequestBody Map<String, Long> json){
        commentService.upvoteComment(session, json.get("id"));
    }
    
    @ApiOperation(value = "Downvotes a comment, checks if the user has sufficient privileges, uses sessions for security")
    @GetMapping(value = "/comment/downvoteComment", consumes = "application/json")
    public void downvoteComment(@ApiIgnore HttpSession session, @RequestBody Map<String, Long> json){
        commentService.downvoteComment(session, json.get("id"));
    }
    
}
