package in.resumuff.core.resumes.endpoints;

import in.resumuff.core.resumes.entity.Tag;
import in.resumuff.core.resumes.service.ResumeService;
import in.resumuff.core.resumes.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    
    @Autowired
    private TagService tagService;
    
    @PostMapping("/tag/create")
    public ResponseEntity<Tag> createTag(Tag tag){
        return ResponseEntity.of(tagService.createTag(tag));
    }
    
    @GetMapping("/tag/get/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable long id){
        return ResponseEntity.of(tagService.getTag(id));
    }

    @GetMapping("/tag/get/all")
    public Iterable<Tag> getAllTags(){
        return tagService.getAllTags();
    }

    @GetMapping(value="/tag/get/page/{pageNum}/{pageLen}")
    public Page<Tag> getTags(@PathVariable int pageNum, @PathVariable int pageLen) {
        return tagService.getTags(pageNum, pageLen);
    }

}
