package resu.muffin.users_and_comments.comments.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import resu.muffin.users_and_comments.comments.entities.Comment;
import resu.muffin.users_and_comments.comments.logic.CommentService;

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
    public void createThread(HttpSession session, long userId, long resumeId, String subject, String content) {
        commentService.createThread(session, userId, resumeId, subject, content);
    }

    @GetMapping("/comment/createComment")
    public void addComment(HttpSession session, long userId, long parentId, long threadId, String content) {
        commentService.createComment(session, userId, parentId, threadId, content);
    }

    @GetMapping("/comment/deleteComment")
    public void deleteComment(HttpSession session, long id, long userId) {
        commentService.deleteComment(session, id, userId);
    }

    @GetMapping("/comment/upvoteComment")
    public void upvoteComment(HttpSession session, long id) {
        commentService.upvoteComment(session, id);
    }

    @GetMapping("/comment/downvoteComment")
    public void downvoteComment(HttpSession session, long id) {
        commentService.downvoteComment(session, id);
    }
}