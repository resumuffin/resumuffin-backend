package resu.muffin.users_and_comments.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private boolean isAdmin; // enum
    private String username;
    private String password;
    private String displayName;
    

    public User() {}

    public User(long id, boolean isAdmin, String username, String password, String displayName) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
