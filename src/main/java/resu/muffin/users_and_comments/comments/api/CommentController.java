package resu.muffin.users_and_comments.comments.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import resu.muffin.users_and_comments.comments.db.CommentRepo;
import resu.muffin.users_and_comments.comments.entities.Comment;

@RestController
public class CommentController {
    @Autowired
    private CommentRepo commentRepo;
    
    @GetMapping("/getComment")
    @ResponseBody
    Comment getComment() {
        Comment comment = new Comment();
        return comment;    
    }

    @GetMapping("/addComment")
    void addComment(Comment comment) {
        commentRepo.save(comment);
    }
}