package in.resumuff.core.comments.logic;

import in.resumuff.core.comments.db.CommentRepository;
import in.resumuff.core.comments.entities.Comment;
import in.resumuff.core.users.entities.Permission;
import in.resumuff.core.users.entities.User;
import in.resumuff.core.users.logic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepo;
    
    @Autowired
    private UserService userService;
    
    public Comment getComment(long id){
        return commentRepo.findById(id).orElse(null);
    }
    
    public List<Comment> getThread(long resumeId){
        List<Comment> thread = commentRepo.findByResumeIdAndIsThreadStarterOrderByIdAsc(resumeId, true);
        thread.addAll(commentRepo.findByResumeIdAndIsThreadStarterOrderByIdAsc(resumeId, false));
        return thread;
    }
    
    public List<Comment> getThreadsByUser(long userId){
        return commentRepo.findByUserIdAndIsThreadStarterOrderByIdDesc(userId, true);
    }
    
    public Page<Comment> getThreads(int pageNum, int pageLen){
        return commentRepo.findAllByIsThreadStarter(true, PageRequest.of(pageNum, pageLen));
    }
    
    public Comment createComment(HttpSession session, long resumeId, String description){
        User user = userService.getUserDetailsFull(session);
        if(user == null || !user.hasPermission(Permission.POST_COMMENT))
            return null;
        
        Comment comment = new Comment(user.getId(), resumeId, description);
        commentRepo.save(comment);
        return comment;
    }
    
    public Comment createThread(HttpSession session, long resumeId, String title, String description){
        User user = userService.getUserDetailsFull(session);
        if(user == null || !user.hasPermission(Permission.START_NEW_THREAD))
            return null;
        
        Comment comment = new Comment(user.getId(), resumeId, title, description);
        commentRepo.save(comment);
        return comment;
    }
    
    
    public void deleteComment(HttpSession session, long id){
        User user = userService.getUserDetailsFull(session);
        Comment comment = commentRepo.findById(id).orElse(null);
        if(user == null || comment == null || !user.hasPermission(Permission.DELETE_COMMENT))
            return;
        
        if(comment.getUserId() != user.getId())
            return;
        
        commentRepo.deleteById(id);
    }
    
    public void deleteCommentsRecursive(long resumeId){
        commentRepo.deleteAllByResumeId(resumeId);
    }
    
    public void upvoteComment(HttpSession session, long id){
        applyVote(session, id, false);
    }
    
    public void downvoteComment(HttpSession session, long id){
        applyVote(session, id, true);
    }
    
    private void applyVote(HttpSession session, long cid, boolean isDownvote){
        User user = userService.getUserDetailsFull(session);
        Comment comment = commentRepo.findById(cid).orElse(null);
        if(user == null || comment == null || !user.hasPermission(Permission.RATE_COMMENT))
            return;
        
        if(isDownvote){
            comment.setDownvotes(comment.getDownvotes() + 1);
        } else {
            comment.setUpvotes(comment.getUpvotes() + 1);
        }
        
        commentRepo.save(comment);
    }
    
}