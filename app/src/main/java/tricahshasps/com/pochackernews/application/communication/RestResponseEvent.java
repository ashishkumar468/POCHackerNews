package tricahshasps.com.pochackernews.application.communication;

/**
 * Created by Ashish on 29/10/17.
 */


public class RestResponseEvent {
    private String message;
    private boolean status;


    public RestResponseEvent(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}

