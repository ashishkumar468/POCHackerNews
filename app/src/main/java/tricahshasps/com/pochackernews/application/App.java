package tricahshasps.com.pochackernews.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Ashish on 28/10/17.
 */

public class App extends Application {
    /**
     *
     */

    private RefWatcher refWatcher;
    private static Context context;

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }


    public static Context getAppContext() {
        return App.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            //  should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);

        App.context = getApplicationContext();
        init();
    }

    private void init() {
        // TODO: 28/10/17
    }

}
