package tricahshasps.com.pochackernews.application.communication;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import tricahshasps.com.pochackernews.application.ApiConstants;
import tricahshasps.com.pochackernews.application.communication.interfaces.IUser;
import tricahshasps.com.pochackernews.application.model.User;
import tricahshasps.com.pochackernews.utils.MiscUtils;

/**
 * Created by Ashish on 29/10/17.
 */

class RestClient implements IUser {

    private static final String URL = ApiConstants.HACKER_NEWS_BASE_URL;

    private static RestClient mRestClient;
    private static RestAdapter mRestAdapter;
    Gson gson = new Gson();

    private RestClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(MiscUtils.isProduction() ? RestAdapter.LogLevel.NONE : RestAdapter.LogLevel.FULL)
                .build();
    }

    public static RestClient getClient() {
        if (mRestClient == null)
            mRestClient = new RestClient();
        return mRestClient;
    }

    @Override
    public void registerUser(User user, Callback<RestResponse<Void>> callback) {
        IUser service = mRestAdapter.create(IUser.class);
        service.registerUser(user, callback);
    }
}
