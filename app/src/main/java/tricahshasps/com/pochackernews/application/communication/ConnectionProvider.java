package tricahshasps.com.pochackernews.application.communication;

import android.text.TextUtils;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

import tricahshasps.com.pochackernews.application.ApiConstants;
import tricahshasps.com.pochackernews.application.model.User;

/**
 * Created by Ashish on 29/10/17.
 */


public class ConnectionProvider {

    public static final String BASE_URL = ApiConstants.HACKER_NEWS_BASE_URL;

    public static final String LOGIN_BASE_URL = ApiConstants.REST_API.LOGIN_SIGNUP;

    public static final String USER_AGENT = System.getProperty("http.agent");
    public static final int TIMEOUT_MILLIS = 40 * 1000;
    private static final String LOGIN_URL_EXTENSION = ApiConstants.REST_API.LOGIN_URL_EXTENSION;

    private static Connection defaultConnection(String baseUrlExtension) {
        Connection conn = Jsoup.connect(BASE_URL + baseUrlExtension)
                .timeout(TIMEOUT_MILLIS)
                .userAgent(USER_AGENT);
        conn.header("Accept-Encoding", "gzip");

        return conn;
    }

    private static Connection authorisedConnection(String baseUrlExtension, String userCookie) {
        return defaultConnection(baseUrlExtension).cookie("user", userCookie);
    }

    private Connection connection(String baseUrlExtension) {
        return defaultConnection(baseUrlExtension);
    }


    public Connection loginConnection(User user) {
        Connection login = connection(LOGIN_BASE_URL);
        login.data("go_to", "news")
                .data("acct", user.getUsername())
                .data("pw", user.getPassword());

        if (user.getCreating() != null) {
            login.data("creating", user.getCreating());
        }
        return login.header("Origin", ConnectionProvider.BASE_URL)
                .followRedirects(true)
                .referrer(ConnectionProvider.BASE_URL + ConnectionProvider.LOGIN_URL_EXTENSION)
                .method(Connection.Method.POST);

    }
}

