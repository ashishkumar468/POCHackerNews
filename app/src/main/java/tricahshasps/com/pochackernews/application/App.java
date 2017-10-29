package tricahshasps.com.pochackernews.application;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.otto.Bus;

import tricahshasps.com.pochackernews.application.communication.BusProvider;
import tricahshasps.com.pochackernews.application.communication.EventManager;

/**
 * Created by Ashish on 28/10/17.
 */

public class App extends Application {
    /**
     *
     */
    private RefWatcher refWatcher;
    private static Context context;

    private static Firebase firebaseClientRef;

    private EventManager mManager;
    private Bus mBus = BusProvider.getInstance();

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
        initCommunication();
        initFirebaseClient();
    }

    private void initCommunication() {
        mManager = new EventManager(mBus);
        mBus.register(mManager);
        mBus.register(this);
    }

    private void initFirebaseClient() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        firebaseClientRef = new Firebase(ApiConstants.FIREBASE_BASE_URL);
        firebaseClientRef.keepSynced(true);
    }

    public static Firebase getFirebaseClientRef() {
        return firebaseClientRef;
    }
}
