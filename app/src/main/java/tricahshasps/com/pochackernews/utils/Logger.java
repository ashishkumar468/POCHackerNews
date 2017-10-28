package tricahshasps.com.pochackernews.utils;

import android.util.Log;

import tricahshasps.com.pochackernews.application.Constants;

/**
 * Created by Ashish on 28/10/17.
 */

public class Logger {
    private static final boolean isLoggerOn = true;
    private static final String TAG = Constants.LOGGER_TAG;

    public static void logError(String message) {
        if (MiscUtils.isLoggerOn())
            Log.e(TAG, message);
    }

    public static void logInfo(String message) {
        if (MiscUtils.isLoggerOn())
            Log.d(TAG, message);
    }
}

