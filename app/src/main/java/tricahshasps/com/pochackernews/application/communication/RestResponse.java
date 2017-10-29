package tricahshasps.com.pochackernews.application.communication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 29/10/17.
 */

public class RestResponse<T> {

    @SerializedName("responseData")
    private T data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public T getData() {
        return data;
    }


}