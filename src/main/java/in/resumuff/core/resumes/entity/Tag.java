package in.resumuff.core.resumes.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "rm_tags")
public class Tag {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    @Column(name = "id", nullable = false)
    @ApiModelProperty(readOnly = true, hidden = true)
    private long id;

    @Column(name = "text", nullable = false, unique = true)
    private String text;

    public Tag(){}

    public Tag(String text){
        this.text = text;
    }

    public long getId(){
        return id;
    }

    public String getText(){
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
