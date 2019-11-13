package resu.muffin.users_and_comments.comments.entities;

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
    private long parentId;
    private String content;
    private short upVotes;
    private short downVotes;

    public Comment() {
    }

    public Comment(long id, long userId, long parentId, String content, short upVotes, short downVotes) {
        this.id = id;
        this.userId = userId;
        this.parentId = parentId;
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
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

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUpVotes() {
        return this.upVotes;
    }

    public void setUpVotes(short upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return this.downVotes;
    }

    public void setDownVotes(short downVotes) {
        this.downVotes = downVotes;
    }
}
