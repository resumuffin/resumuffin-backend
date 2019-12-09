package in.resumuff.core.comments.entities;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long resumeId; 
    private long views;
    private boolean isThreadStarter;
    private OffsetDateTime timestamp;
    private String title;
    private String description;
    private int upvotes;
    private int downvotes;

    public Comment() {
    }

    public Comment(long userId, long resumeId, String title, String description) {
        this.userId = userId;
        this.resumeId = resumeId;
        this.views = 0;
        this.isThreadStarter = true;
        this.timestamp = getTimestamp();
        this.title = title;
        this.description = description;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public Comment(long userId, long resumeId, String description) {
        this.userId = userId;
        this.views = 0;
        this.isThreadStarter = false;
        this.timestamp = getTimestamp();
        this.description = description;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getResumeId() {
        return this.resumeId;
    }

    public void setResumeId(long resumeId) {
        this.resumeId = resumeId;
    }

    public long getViews() {
        return this.views;
    }

    public boolean getIsThreadStarter() {
        return this.isThreadStarter;
    }

    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUpvotes() {
        return this.upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return this.downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
