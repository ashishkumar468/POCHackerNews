package tricahshasps.com.pochackernews.onboarding;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseActivity;

/**
 * Created by Ashish on 28/10/17.
 */

public class LoginActivty extends BaseActivity implements ILoginContract.LoginView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void login() {

    }

    @Override
    public void createAccoung() {

    }
}
