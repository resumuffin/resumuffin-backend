package in.resumuff.core.resumes.repository;

import in.resumuff.core.resumes.entity.Resume;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends PagingAndSortingRepository<Resume, Long> {}
