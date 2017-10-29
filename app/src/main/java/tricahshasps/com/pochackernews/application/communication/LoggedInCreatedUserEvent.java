package tricahshasps.com.pochackernews.application.communication;

import tricahshasps.com.pochackernews.application.model.User;

/**
 * Created by Ashish on 29/10/17.
 */

public class LoggedInCreatedUserEvent extends RestResponseEvent {
    private final User data;

    public LoggedInCreatedUserEvent(boolean status, String message, User data) {
        super(message, status);
        this.data = data;
    }

    public User getUser() {
        return data;
    }
}
