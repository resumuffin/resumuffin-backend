package resu.muffin.users_and_comments.users.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    private String name;
    private boolean POST_COMMENTS;
    private boolean DELETE_COMMENTS;
    private boolean RATE_COMMENTS;
    private boolean UPLOAD_RESUME;
    private boolean START_NEW_THREAD;

    public Role() {
        this.POST_COMMENTS = false;
        this.DELETE_COMMENTS = false;
        this.RATE_COMMENTS = false;
        this.UPLOAD_RESUME = false;
        this.START_NEW_THREAD = false;
    }

    public Role(String name) {
        this.name = name;
        this.POST_COMMENTS = false;
        this.DELETE_COMMENTS = false;
        this.RATE_COMMENTS = false;
        this.UPLOAD_RESUME = false;
        this.START_NEW_THREAD = false;
    }

    public Role(String name, boolean POST_COMMENTS, boolean DELETE_COMMENTS, boolean RATE_COMMENTS, boolean UPLOAD_RESUME, boolean START_NEW_THREAD) {
        this.name = name;
        this.POST_COMMENTS = POST_COMMENTS;
        this.DELETE_COMMENTS = DELETE_COMMENTS;
        this.RATE_COMMENTS = RATE_COMMENTS;
        this.UPLOAD_RESUME = UPLOAD_RESUME;
        this.START_NEW_THREAD = START_NEW_THREAD;
    }

    public short getId() {
        return this.id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getPOST_COMMENTS() {
        return this.POST_COMMENTS;
    }

    public void setPOST_COMMENTS(boolean POST_COMMENTS) {
        this.POST_COMMENTS = POST_COMMENTS;
    }

    public boolean getDELETE_COMMENTS() {
        return this.DELETE_COMMENTS;
    }

    public void setDELETE_COMMENTS(boolean DELETE_COMMENTS) {
        this.DELETE_COMMENTS = DELETE_COMMENTS;
    }

    public boolean getRATE_COMMENTS() {
        return this.RATE_COMMENTS;
    }

    public void setRATE_COMMENTS(boolean RATE_COMMENTS) {
        this.RATE_COMMENTS = RATE_COMMENTS;
    }

    public boolean getUPLOAD_RESUME() {
        return this.UPLOAD_RESUME;
    }

    public void setUPLOAD_RESUME(boolean UPLOAD_RESUME) {
        this.UPLOAD_RESUME = UPLOAD_RESUME;
    }

    public boolean getSTART_NEW_THEAD() {
        return this.START_NEW_THREAD;
    }

    public void setSTART_NEW_THREAD(boolean START_NEW_THREAD) {
        this.START_NEW_THREAD = START_NEW_THREAD;
    }
}