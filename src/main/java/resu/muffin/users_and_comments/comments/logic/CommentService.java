package resu.muffin.users_and_comments.comments.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import resu.muffin.users_and_comments.comments.db.CommentRepository;
import resu.muffin.users_and_comments.comments.entities.Comment;
import resu.muffin.users_and_comments.users.db.RoleRepository;
import resu.muffin.users_and_comments.users.db.UserRepository;
import resu.muffin.users_and_comments.users.entities.User;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    public Comment getComment(long id) {
        return commentRepo.findById(id).orElse(null);
    }

    public Comment getThread(long threadId) {
        return commentRepo.findByThreadId(threadId).orElse(null);
    }

	public ArrayList<Comment> getTopTenThreads() {
		return commentRepo.findTop10OrderByTimestampDesc();
	}

	public void createThread(long userId, long resumeId, String subject, String content) {
        User user = userRepo.findById(userId).orElse(null);
        if(user != null) {
            if(user.getRole().getSTART_NEW_THEAD()) {
                Comment comment = new Comment(userId, resumeId, subject, content);
                commentRepo.save(comment);
            }
        }
	}

	public void createComment(long userId, long parentId, long threadId, String content) {
        User user = userRepo.findById(userId).orElse(null);
        if(user != null) {
            if(user.getRole().getPOST_COMMENTS()) {
                Comment comment = new Comment(userId, parentId, threadId, content);
                commentRepo.save(comment);
            }
        }

	}

	public void deleteComment(long id, long userId) {
        Comment comment = commentRepo.findById(id).orElse(null);
        if(comment != null) {
            if(comment.getUserId() == userId) {

            }
            else {
                User user = userRepo.findById(userId).orElse(null);
                if(user != null && user.getRole().getDELETE_COMMENTS()) {
                    commentRepo.deleteById(id);
                }
            }
        }
	}

	public void upvoteComment(long id) {
        Comment comment = commentRepo.findById(id).orElse(null);
        if(comment != null) {
            comment.setUpvotes(comment.getUpvotes()+1);
            commentRepo.save(comment);
        }
	}

	public void downvoteComment(long id) {
        Comment comment = commentRepo.findById(id).orElse(null);
        if(comment != null) {
            comment.setDownvotes(comment.getDownvotes()+1);
            commentRepo.save(comment);
        }
	}
}