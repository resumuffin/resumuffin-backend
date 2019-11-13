package resu.muffin.users_and_comments.comments.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import resu.muffin.users_and_comments.comments.entities.Comment;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    
}