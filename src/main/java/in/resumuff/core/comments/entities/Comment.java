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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long userId;
    private long resumeId;
    private long parentId;
    private long threadId;
    private long views;
    private OffsetDateTime timestamp;
    private String subject;
    private String content;
    private int upvotes;
    private int downvotes;

    public Comment() {
    }

    public Comment(long userId, long resumeId, String subject, String content) {
        this.userId = userId;
        this.parentId = -1;
        this.resumeId = resumeId;
        this.views = 0;
        this.timestamp = getTimestamp();
        this.subject = subject;
        this.content = content;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public Comment(long userId, long parentId, long threadId, String content) {
        this.userId = userId;
        this.parentId = parentId;
        this.threadId = threadId;
        this.views = 0;
        this.timestamp = getTimestamp();
        this.content = content;
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

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getTheeadId() {
        return this.threadId;
    }

    public long getViews() {
        return this.views;
    }

    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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
