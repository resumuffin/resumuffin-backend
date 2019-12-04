package in.resumuff.core.resumes.repository;

import in.resumuff.core.resumes.entity.Resume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends CrudRepository<Resume, Long> {}
