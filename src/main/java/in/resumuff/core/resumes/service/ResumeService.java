package in.resumuff.core.resumes.service;

import in.resumuff.core.comments.entities.Comment;
import in.resumuff.core.comments.logic.CommentService;
import in.resumuff.core.resumes.entity.Resume;
import in.resumuff.core.resumes.repository.ResumeRepository;
import in.resumuff.core.users.entities.Permission;
import in.resumuff.core.users.entities.User;
import in.resumuff.core.users.logic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ResumeService {
    
    @Autowired
    private ResumeRepository repository;
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private UserService userService;
    
    public Optional<Resume> storeResume(long uid, MultipartFile file, String[] tags, String title, String description){
        String contentType = file.getContentType();
        int[] intTags = convertTags(tags);
        
        if(contentType == null || !tagService.validTags(intTags))
            return Optional.empty();
        
        try {
            Resume resume = new Resume(uid, file.getBytes(), contentType.startsWith("image/"), intTags, title, description);
            return Optional.of(repository.save(resume));
        } catch(IOException exc) {
            exc.printStackTrace();
            return Optional.empty();
        }
    }
    
    public Optional<Resume> getResume(long id){
        return repository.findById(id);
    }
    
    public Iterable<Resume> getAllResumes(){
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    public Page<Resume> getResumes(int pageNum, int pageLen){
        return repository.findAll(PageRequest.of(pageNum, pageLen));
    }
    
    private int[] convertTags(String[] tags){
        int[] intTags = new int[tags.length];
        for(int i = 0; i < intTags.length; i++)
            intTags[i] = Integer.parseInt(tags[i]);
        return intTags;
    }
    
    @Transactional
    public void deleteResume(HttpSession session, long id){
        User user = userService.getUserDetailsFull(session);
        Comment comment = commentService.getComment(id);
        if(user == null || comment == null)
            return;
        
        if(!user.hasPermission(Permission.DELETE_COMMENT))
            return;
        
        repository.deleteById(id);
        commentService.deleteCommentsRecursive(id);
    }
    
}
