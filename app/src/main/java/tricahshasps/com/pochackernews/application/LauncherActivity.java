package tricahshasps.com.pochackernews.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import tricahshasps.com.pochackernews.home.HomeActivity;
import tricahshasps.com.pochackernews.onboarding.LoginActivity;
import tricahshasps.com.pochackernews.utils.SharedPreferenceUtils;

/**
 * Created by Ashish on 29/10/17.
 */

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean userLoginStatus = SharedPreferenceUtils.getInstance().getData(Constants.BUNDLE_KEYS.USER_LOGIN_STATUS, false);
        if (!userLoginStatus) {
            goToActivity(LoginActivity.newIntent(this));
        } else {
            goToActivity(HomeActivity.newIntent(this));
        }
    }

    private void goToActivity(Intent intent) {
        startActivity(intent);
        finish();
    }
}
