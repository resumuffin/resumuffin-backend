package in.resumuff.core.comments.db;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.resumuff.core.comments.entities.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findByThreadId(long threadId);

    ArrayList<Comment> findTop10By();
}