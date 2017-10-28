package tricahshasps.com.pochackernews.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseActivity;
import tricahshasps.com.pochackernews.application.model.User;
import tricahshasps.com.pochackernews.home.HomeActivity;

/**
 * Created by Ashish on 28/10/17.
 */

public class LoginActivity extends BaseActivity implements ILoginContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.til_username)
    TextInputLayout tilUsername;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;

    LoginPresenter presenter;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpToolbar(toolbar, R.string.title_login_activity);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    private void init() {
        initUser();
        initPresenter();
    }

    private void initUser() {
        user = new User();
    }

    private void initPresenter() {
        presenter = new LoginPresenter();
    }

    @Override
    public void login() {
        user.setUsername(etUsername.getText().toString());
        user.setPassword(etPassword.getText().toString());
        presenter.login(user);
    }

    @Override
    public void createAccount() {

    }

    @Override
    public void onLoginSuccessful(User user) {
        gotoActivity(HomeActivity.newIntent(this));
    }

    private void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onLoginFailed(String message) {
        // TODO: 28/10/17
    }

    private boolean validate() {
        tilUsername.setErrorEnabled(false);
        tilPassword.setErrorEnabled(false);
        if (etUsername.getText().length() == 0) {
            tilUsername.setError(getString(R.string.username_required));
        }
        if (etPassword.getText().length() == 0) {
            tilPassword.setError(getString(R.string.password_required));
        }
        return etUsername.getText().length() > 0 && etPassword.length() > 0;
    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClicked() {
        /*if (!validate()) {
            return;
        }

        login();*/
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://hacker-news.firebaseio.com/v0");
        firebase.child("item/15573928").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            snapshot.getKey();
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
        onLoginSuccessful(new User());
    }
}
