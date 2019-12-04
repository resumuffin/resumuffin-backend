package in.resumuff.core.comments.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.resumuff.core.comments.entities.Comment;
import in.resumuff.core.comments.logic.CommentService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/getComment/{id}")
    public Comment getComment(@PathVariable long id) {
        Comment comment = commentService.getComment(id);
        return comment;
    }

    @GetMapping("/getThread/{threadId}")
    public Comment getThread(@PathVariable long threadId) {
        Comment comment = commentService.getThread(threadId);
        return comment;    
    }

    @GetMapping("/getThreads")
    public List<Comment> getTopTenThreads() {
        List<Comment> comments = commentService.getTopTenThreads();
        return comments;
    }
    
    // check if allowed to create thread
    @PostMapping("/comment/createThread/{userId}/{resumeId}/{subject}/{content}")
    public void createThread(@ApiIgnore HttpSession session, @PathVariable long userId, @PathVariable long resumeId, @PathVariable String subject, @PathVariable String content) {
        commentService.createThread(session, userId, resumeId, subject, content);
    }

    @GetMapping("/comment/createComment/{userId}/{parentId}/{threadId}/{content}")
    public void addComment(@ApiIgnore HttpSession session, @PathVariable long userId, @PathVariable long parentId, @PathVariable long threadId, @PathVariable String content) {
        commentService.createComment(session, userId, parentId, threadId, content);
    }

    @GetMapping("/comment/deleteComment/{id}/{userId}")
    public void deleteComment(@ApiIgnore HttpSession session, @PathVariable long id, @PathVariable long userId) {
        commentService.deleteComment(session, id, userId);
    }

    @GetMapping("/comment/upvoteComment/{id}")
    public void upvoteComment(@ApiIgnore HttpSession session, @PathVariable long id) {
        commentService.upvoteComment(session, id);
    }

    @GetMapping("/comment/downvoteComment/{id}")
    public void downvoteComment(@ApiIgnore HttpSession session, @PathVariable long id) {
        commentService.downvoteComment(session, id);
    }
}
