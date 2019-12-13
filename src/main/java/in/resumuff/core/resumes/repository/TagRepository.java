package in.resumuff.core.resumes.repository;

import in.resumuff.core.resumes.entity.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
    
    Optional<Tag> findByText(String text);
    
}
