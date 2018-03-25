package  megha.myprofiler.data;

/**
 * Created by Megha Chauhan on 16-Dec-17.
 */

public class User {
    private String email;
    private String userId;

    public User(){}

    public User(String email, String userId) {
        this.email = email;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
