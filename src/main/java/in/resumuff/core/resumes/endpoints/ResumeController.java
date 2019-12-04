package in.resumuff.core.resumes.endpoints;

import in.resumuff.core.resumes.entity.Resume;
import in.resumuff.core.resumes.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PostMapping(value = "/resume/upload", consumes = "multipart/form-data")
    public ResponseEntity<Resume> uploadResume(@ApiIgnore HttpSession session,
                                               @RequestParam("file") MultipartFile resumeFile,
                                               @RequestParam("tags") int[] tags){
//        Long uid = (Long)session.getAttribute("uid");
//        if(uid == null)
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        Optional<Resume> storedResume = resumeService.storeResume(0, resumeFile, tags);
        if(storedResume.isPresent()){
            return ResponseEntity.ok(storedResume.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/resume/get/{id}")
    public ResponseEntity<Resume> getResume(@PathVariable long id){
        return ResponseEntity.of(resumeService.getResume(id));
    }

}
