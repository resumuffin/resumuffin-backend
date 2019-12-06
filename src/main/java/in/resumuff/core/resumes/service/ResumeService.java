package in.resumuff.core.resumes.service;

import in.resumuff.core.resumes.entity.Resume;
import in.resumuff.core.resumes.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository repository;

    @Autowired
    private TagService tagService;

    public Optional<Resume> storeResume(long uid, MultipartFile file, int[] tags){
        String contentType = file.getContentType();
        if(contentType == null)
            return Optional.empty();

        if(!tagService.validTags(tags))
            return Optional.empty();

        try {
            Resume resume = new Resume(uid, file.getBytes(), contentType.startsWith("image/"), tags);
            return Optional.of(repository.save(resume));
        } catch (IOException exc){
            exc.printStackTrace();
        }
        
        return Optional.empty();
    }
    
    public Optional<Resume> getResume(long id){
        return repository.findById(id);
    }

    public Iterable<Resume> getAllResumes() {
        return repository.findAll();
    }

    public Page<Resume> getResumes(int pageNum, int pageLen) {
        Pageable pageable = PageRequest.of(pageNum, pageLen);
        Page<Resume> resumes = repository.findAll(pageable);
        return resumes;
    }
    

}
