package tricahshasps.com.pochackernews.application.communication.events;

import tricahshasps.com.pochackernews.application.model.User;

/**
 * Created by Ashish on 29/10/17.
 */

public class LoginSignupUserEvent {

    private final User user;

    public LoginSignupUserEvent(User user){
        this.user=user;
    }

    public User getUser() {
        return user;
    }
}
