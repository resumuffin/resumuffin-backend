package in.resumuff.core.resumes.entity;

import javax.persistence.*;

@Entity
@Table(name = "rm_resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "uid", nullable = false)
    private long uid;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;
    
    @Column(name = "isImage", nullable = false)
    private boolean isImage;
    
    @Column(name = "tags", nullable = false)
    private int[] tagIds;

    public Resume(){}

    public Resume(long uid, byte[] data, boolean isImage, int[] tagIds){
        this.uid = uid;
        this.data = data;
        this.isImage = isImage;
        this.tagIds = tagIds;
    }
    
    public long getId(){
        return id;
    }
    
    public long getOwner(){
        return uid;
    }
    
    public boolean isImage(){
        return isImage;
    }
    
    public byte[] getData(){
        return data;
    }
    
    public int[] getTags(){
        return tagIds;
    }
    
}
