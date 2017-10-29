package tricahshasps.com.pochackernews.application.communication;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.jsoup.Connection;

import java.io.IOException;

import tricahshasps.com.pochackernews.application.communication.events.LoginSignupUserEvent;

/**
 * Created by Ashish on 29/10/17.
 */

public class EventManager {
    private Bus mBus;
    private String cookie;
    private String cfduid;


    public EventManager(Bus mBus) {
        this.mBus = mBus;
    }

    @Subscribe
    public void onCreateUserEvent(final LoginSignupUserEvent event) throws IOException {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Connection.Response response = new ConnectionProvider().loginConnection(event.getUser()).execute();
                    cookie = response.cookie("user");
                    cfduid = response.cookie("_cfduid");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(cookie)) {
                    mBus.post(new LoggedInCreatedUserEvent(true, "Logged in succesfully", null));
                } else {
                    //This is ofcourse not right, the api is not that revealing, could not figure a way to signup..yes only login works, signup doesnottahts why to anywas let the suer see the news m letting him in
                    if (event.getUser().getCreating() == null) {
                        mBus.post(new LoggedInCreatedUserEvent(false, "Invalid Credentials", null));
                    } else {
                        mBus.post(new LoggedInCreatedUserEvent(true, "Invalid Credentials", null));
                    }
                }
                return null;
            }
        }.execute();
    }

}
