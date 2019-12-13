package in.resumuff.core.users.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private short id;
    private String name;
    
    private boolean canPostComments;
    private boolean canDeleteComments;
    private boolean canRateComments;
    private boolean canUploadResume;
    private boolean canStartNewThread;

    public Role() {
        this.canPostComments = false;
        this.canDeleteComments = false;
        this.canRateComments = false;
        this.canUploadResume = false;
        this.canStartNewThread = false;
    }

    public Role(String name) {
        this.name = name;
        this.canPostComments = false;
        this.canDeleteComments = false;
        this.canRateComments = false;
        this.canUploadResume = false;
        this.canStartNewThread = false;
    }

    public Role(String name, boolean canPostComments, boolean canDeleteComments, boolean canRateComments, boolean canUploadResume, boolean canStartNewThread) {
        this.name = name;
        this.canPostComments = canPostComments;
        this.canDeleteComments = canDeleteComments;
        this.canRateComments = canRateComments;
        this.canUploadResume = canUploadResume;
        this.canStartNewThread = canStartNewThread;
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

    public boolean getCanPostComments() {
        return this.canPostComments;
    }

    public void setCanPostComments(boolean canPostComments) {
        this.canPostComments = canPostComments;
    }

    public boolean getCanDeleteComments() {
        return this.canDeleteComments;
    }

    public void setCanDeleteComments(boolean canDeleteComments) {
        this.canDeleteComments = canDeleteComments;
    }

    public boolean getCanRateComments() {
        return this.canRateComments;
    }

    public void setCanRateComments(boolean canRateComments) {
        this.canRateComments = canRateComments;
    }

    public boolean getCanUploadResume() {
        return this.canUploadResume;
    }

    public void setCanUploadResume(boolean canUploadResume) {
        this.canUploadResume = canUploadResume;
    }

    public boolean getCanStartNewThread() {
        return this.canStartNewThread;
    }

    public void setCanStartNewThread(boolean canStartNewThread) {
        this.canStartNewThread = canStartNewThread;
    }
    
}