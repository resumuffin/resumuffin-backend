package in.resumuff.core.resumes.service;

import in.resumuff.core.resumes.entity.Tag;
import in.resumuff.core.resumes.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    
    @Autowired
    private TagRepository repository;
    
    public Optional<Tag> createTag(Tag tag){
        Optional<Tag> existing = getTag(tag.getId());
        
        if(existing.isPresent())
            return existing;

        if((existing = getTagByText(tag.getText())).isPresent())
            return existing;

        try {
            tag = repository.save(tag);
        } catch (PersistenceException exc){
            repository.deleteById(tag.getId());
        }
        
        return Optional.of(tag);
    }
    
    public Optional<Tag> getTag(long id){
        return repository.findById(id);
    }

    public Optional<Tag> getTagByText(String text){
        return repository.findByText(text);
    }

    public Iterable<Tag> getAllTags(){
        return repository.findAll();
    }

    public Page<Tag> getTags(int pageNum, int pageLength) {
        return repository.findAll(PageRequest.of(pageNum, pageLength));
    }

    public boolean validTags(int[] tags){
        for(int id : tags){
            if(!getTag(id).isPresent())
                return false;
        }
        return true;
    }
    
}
