package in.resumuff.core.comments.logic;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.resumuff.core.comments.db.CommentRepository;
import in.resumuff.core.comments.entities.Comment;
import in.resumuff.core.users.db.RoleRepository;
import in.resumuff.core.users.db.UserRepository;
import in.resumuff.core.users.entities.User;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    public Comment getComment(long id) {
        return commentRepo.findById(id).orElse(null);
    }

    public List<Comment> getThread(long resumeId) {
        List<Comment> thread = commentRepo.findByResumeIdAndIsThreadStarterOrderByIdAsc(resumeId, true);
        thread.addAll(commentRepo.findByResumeIdAndIsThreadStarterOrderByIdAsc(resumeId, false));
        return thread;
    }

    public List<Comment> getThreadsByUser(long userId) {
        return commentRepo.findByUserIdAndIsThreadStarterOrderByIdDesc(userId, true);
    }

    public Page<Comment> getThreads(int pageNum, int pageLen) {
        Pageable pageable = PageRequest.of(pageNum, pageLen);
        Page<Comment> threads = commentRepo.findAllByIsThreadStarter(true, pageable);
        return threads;
    }

  

	public Comment createThread(HttpSession session, long resumeId, String title, String description) {
        long userId = (long)session.getAttribute("USER_ID");
        if(session.getAttribute("TOKEN") != null) {
            User user = userRepo.findById(userId).orElse(null);
            if(user != null) {
                if(user.getAccessToken().equals(session.getAttribute("TOKEN")) && user.getRole().getSTART_NEW_THREAD()) {
                    Comment comment = new Comment(userId, resumeId, title, description);
                    commentRepo.save(comment);
                    return comment;
                }
            }
        }
        return null;
	}

	public Comment createComment(HttpSession session, long resumeId, String description) {
        long userId = (long)session.getAttribute("USER_ID");
        if(session.getAttribute("TOKEN") != null) {
            User user = userRepo.findById(userId).orElse(null);
            if(user != null) {
                if(user.getAccessToken().equals(session.getAttribute("TOKEN")) && user.getRole().getPOST_COMMENTS()) {
                    Comment comment = new Comment(userId, resumeId, description);
                    commentRepo.save(comment);
                    return comment;
                }
            }
        }
        return null;
	}

	public void deleteComment(HttpSession session, long id) {
        long userId = (Long)session.getAttribute("USER_ID");
        if(session.getAttribute("TOKEN") != null) {
            Comment comment = commentRepo.findById(id).orElse(null);
            if(comment != null) {
                User user = userRepo.findById(userId).orElse(null);
                if(user != null && user.getAccessToken().equals(session.getAttribute("TOKEN"))) {
                    if(comment.getUserId() == userId || user.getRole().getDELETE_COMMENTS()) {
                        commentRepo.deleteById(id);
                    }
                }
            }
        }
    }
    
    public void deleteCommentsRecursive(long resumeId) {
        commentRepo.deleteAllByResumeId(resumeId);
    }

	public void upvoteComment(HttpSession session, long id) {
        if(session.getAttribute("TOKEN") != null) {
            Comment comment = commentRepo.findById(id).orElse(null);
            if(comment != null) {
                comment.setUpvotes(comment.getUpvotes()+1);
                commentRepo.save(comment);
            }
        }
	}

	public void downvoteComment(HttpSession session, long id) {
        if(session.getAttribute("TOKEN") != null) {
            Comment comment = commentRepo.findById(id).orElse(null);
            if(comment != null) {
                comment.setDownvotes(comment.getDownvotes()+1);
                commentRepo.save(comment);
            }
        }
	}
}