package tricahshasps.com.pochackernews.utils;

/**
 * Created by Ashish on 28/10/17.
 */

import android.app.Activity;
import android.content.SharedPreferences;

import tricahshasps.com.pochackernews.application.App;
import tricahshasps.com.pochackernews.application.Constants;


public class SharedPreferenceUtils {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SharedPreferenceUtils instance;


    private static void initializeSharedPref() {
        instance = new SharedPreferenceUtils();
        instance.sharedPreferences = App.getAppContext().getSharedPreferences(
                Constants.PREFERENCES_FILENAME, Activity.MODE_PRIVATE);
        instance.editor = instance.sharedPreferences
                .edit();
    }

    public static SharedPreferenceUtils getInstance() {
        if (instance == null) {
            initializeSharedPref();
        }
        return instance;
    }

    private SharedPreferenceUtils() {
        // TODO Auto-generated constructor stub
    }

    public synchronized boolean saveData(String key, String value) {
        Logger.logInfo("saving " + key + " = " + value);
        editor.putString(key, value);
        return editor.commit();
    }

    public synchronized boolean saveData(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public synchronized boolean saveData(String key, long value) {
        editor.putLong(key, value);
        return editor.commit();
    }


    public synchronized boolean saveData(String key, float value) {
        editor.putFloat(key, value);
        return editor.commit();
    }


    public synchronized boolean saveData(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    public synchronized boolean removeData(String key) {
        editor.remove(key);
        return editor.commit();
    }

    public synchronized Boolean getData(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public synchronized String getData(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public synchronized float getData(String key, float defaultValue) {

        return sharedPreferences.getFloat(key, defaultValue);
    }

    public synchronized int getData(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public synchronized long getData(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public synchronized void deleteAllData() {
        instance = null;
        editor.clear();
        editor.commit();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

}