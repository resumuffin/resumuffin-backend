package resu.muffin.users_and_comments.users.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean CAN_POST_COMMENTS;
    private boolean CAN_DELETE_COMMENTS;
    private boolean CAN_RATE_COMMENTS;
    private boolean CAN_UPLOAD_RESUME;
    private boolean CAN_START_NEW_THREAD;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, boolean CAN_POST_COMMENTS, boolean CAN_DELETE_COMMENTS, boolean CAN_RATE_COMMENTS, boolean CAN_UPLOAD_RESUME) {
        this.name = name;
        this.CAN_POST_COMMENTS = CAN_POST_COMMENTS;
        this.CAN_DELETE_COMMENTS = CAN_DELETE_COMMENTS;
        this.CAN_RATE_COMMENTS = CAN_RATE_COMMENTS;
        this.CAN_UPLOAD_RESUME = CAN_UPLOAD_RESUME;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCAN_POST_COMMENTS() {
        return this.CAN_POST_COMMENTS;
    }

    public void setCAN_POST_COMMENTS(boolean CAN_POST_COMMENTS) {
        this.CAN_POST_COMMENTS = CAN_POST_COMMENTS;
    }

    public boolean getCAN_DELETE_COMMENTS() {
        return this.CAN_DELETE_COMMENTS;
    }

    public void setCAN_DELETE_COMMENTS(boolean CAN_DELETE_COMMENTS) {
        this.CAN_DELETE_COMMENTS = CAN_DELETE_COMMENTS;
    }

    public boolean getCAN_RATE_COMMENTS() {
        return this.CAN_RATE_COMMENTS;
    }

    public void setCAN_RATE_COMMENTS(boolean CAN_RATE_COMMENTS) {
        this.CAN_RATE_COMMENTS = CAN_RATE_COMMENTS;
    }

    public boolean getCAN_UPLOAD_RESUME() {
        return this.CAN_UPLOAD_RESUME;
    }

    public void setCAN_UPLOAD_RESUME(boolean CAN_UPLOAD_RESUME) {
        this.CAN_UPLOAD_RESUME = CAN_UPLOAD_RESUME;
    }

    public boolean getCAN_START_NEW_THEAD() {
        return this.CAN_START_NEW_THREAD;
    }

    public void setCAN_START_NEW_THREAD(boolean CAN_START_NEW_THREAD) {
        this.CAN_START_NEW_THREAD = CAN_START_NEW_THREAD;
    }
}