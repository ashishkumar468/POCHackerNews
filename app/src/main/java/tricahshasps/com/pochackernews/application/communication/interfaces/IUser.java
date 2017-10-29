package tricahshasps.com.pochackernews.application.communication.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import tricahshasps.com.pochackernews.application.ApiConstants;
import tricahshasps.com.pochackernews.application.communication.RestResponse;
import tricahshasps.com.pochackernews.application.model.User;

/**
 * Created by Ashish on 29/10/17.
 */

public interface IUser {
    @POST(ApiConstants.REST_API.LOGIN_SIGNUP)
    void registerUser(@Body User user, Callback<RestResponse<Void>> callback);
}
