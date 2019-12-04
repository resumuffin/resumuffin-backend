package in.resumuff.core.comments.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.resumuff.core.comments.entities.Comment;
import in.resumuff.core.comments.logic.CommentService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/getComment")
    public Comment getComment(long id) {
        Comment comment = commentService.getComment(id);
        return comment;
    }

    @GetMapping("/getThread")
    public Comment getThread(long threadId) {
        Comment comment = commentService.getThread(threadId);
        return comment;    
    }

    @GetMapping("/getThreads/")
    public List<Comment> getTopTenThreads() {
        List<Comment> comments = commentService.getTopTenThreads();
        return comments;
    }
    
    // check if allowed to create thread
    @PostMapping("/comment/createThread")
    public void createThread(@ApiIgnore HttpSession session, long userId, long resumeId, String subject, String content) {
        commentService.createThread(session, userId, resumeId, subject, content);
    }

    @GetMapping("/comment/createComment")
    public void addComment(@ApiIgnore HttpSession session, long userId, long parentId, long threadId, String content) {
        commentService.createComment(session, userId, parentId, threadId, content);
    }

    @GetMapping("/comment/deleteComment")
    public void deleteComment(@ApiIgnore HttpSession session, long id, long userId) {
        commentService.deleteComment(session, id, userId);
    }

    @GetMapping("/comment/upvoteComment")
    public void upvoteComment(@ApiIgnore HttpSession session, long id) {
        commentService.upvoteComment(session, id);
    }

    @GetMapping("/comment/downvoteComment")
    public void downvoteComment(@ApiIgnore HttpSession session, long id) {
        commentService.downvoteComment(session, id);
    }
}
