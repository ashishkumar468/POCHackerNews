package tricahshasps.com.pochackernews.application.communication;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tricahshasps.com.pochackernews.utils.Logger;

/**
 * Created by Ashish on 29/10/17.
 */


public class RestCallback<T> implements Callback<T> {
    @Override
    public void success(T t, Response response) {
        Logger.logError("Succesfull response from api " + response.getUrl());
        Logger.logError("response body" + response.toString());
    }

    @Override
    public void failure(RetrofitError error) {
        Logger.logError("something happened in an api call..." + error.getUrl() + "\nerror details " + error.getMessage());
        int status = getHttpStatus(error);
        if (status == 401 || status == 422 || status >= 500) {
        }
    }

    private int getHttpStatus(RetrofitError error) {
        if (error.getKind() == RetrofitError.Kind.NETWORK) {
            return 503;
        }
        return error.getResponse() != null ? error.getResponse().getStatus() : -1;
    }

    private String constructErrorMessage(RetrofitError error) {
        return "Url:" + error.getUrl() + "\n"
                + "Status Code:" + getHttpStatus(error) + "\n"
                + "Message:" + error.getMessage();
    }
}
