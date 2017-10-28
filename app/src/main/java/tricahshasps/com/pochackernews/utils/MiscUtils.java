package tricahshasps.com.pochackernews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import tricahshasps.com.pochackernews.BuildConfig;
import tricahshasps.com.pochackernews.application.App;

/**
 * Created by Ashish on 28/10/17.
 */

public class MiscUtils {
    public static boolean isConnectedToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    public static boolean isLoggerOn() {
        return !isProduction();
    }

    public static boolean isProduction() {
        if (BuildConfig.BUILD_TYPE.equals(BuildConfig.DEBUG))
            return true;
        return false;
    }


}
