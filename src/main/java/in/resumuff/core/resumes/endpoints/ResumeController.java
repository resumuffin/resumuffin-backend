package in.resumuff.core.resumes.endpoints;

import in.resumuff.core.comments.logic.CommentService;
import in.resumuff.core.resumes.entity.Resume;
import in.resumuff.core.resumes.service.ResumeService;
import in.resumuff.core.users.logic.UserService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/resume/upload", consumes = "multipart/form-data")
    @ApiOperation(value="Uploads a resume to the database, also creates a thread in the comment database")
    public ResponseEntity<Resume> uploadResume(@ApiIgnore HttpSession session,
                                               @RequestParam("file") MultipartFile resumeFile,
                                               @RequestPart("tags") String[] tags,
                                               @RequestParam("title") String title,
                                               @RequestParam("description") String description) {
        if(!userService.isSessionValid(session))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        Long uid = (Long)session.getAttribute("USER_ID");
        Optional<Resume> storedResume = resumeService.storeResume(uid, resumeFile, tags, title, description);
        
        if(storedResume.isPresent()){
            commentService.createThread(session, storedResume.get().getId(), title, description);
            return ResponseEntity.of(storedResume);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/resume/get/{id}")
    public ResponseEntity<Resume> getResume(@PathVariable long id){
        return ResponseEntity.of(resumeService.getResume(id));
    }
    
    @GetMapping(value="/resume/get/all")
    public Iterable<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }
    
    @GetMapping(value="/resume/get/page/{pageNum}/{pageLen}")
    public Page<Resume> getResumes(@PathVariable int pageNum, @PathVariable int pageLen) {
        return resumeService.getResumes(pageNum, pageLen);
    }

}
