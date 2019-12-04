package in.resumuff.core.resumes.repository;

import in.resumuff.core.resumes.entity.Resume;
import in.resumuff.core.resumes.entity.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findByText(String text);

}
