package tricahshasps.com.pochackernews.application.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 28/10/17.
 */

public class User {
    @SerializedName("acct")
    private String username;

    @SerializedName("pw")
    private String password;

    @SerializedName("creating")
    private String creating;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setCreating(String creating) {
        this.creating = creating;
    }

    public void setPw(String pw) {
        this.password = pw;
    }

    public void setAcct(String acct) {
        this.username = acct;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCreating() {
        return creating;
    }
}
