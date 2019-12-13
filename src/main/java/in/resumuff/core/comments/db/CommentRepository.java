package in.resumuff.core.comments.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.resumuff.core.comments.entities.Comment;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    List<Comment> findByResumeIdAndIsThreadStarterOrderByIdAsc(long resumeId, boolean isThreadStarter);

    List<Comment> findByUserIdAndIsThreadStarterOrderByIdDesc(long userId, boolean isThreadStarter);

    Page<Comment> findAllByIsThreadStarter(boolean isThreadStarter, Pageable pageable);

    void deleteAllByResumeId(long resumeId);
}