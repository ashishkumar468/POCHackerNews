package tricahshasps.com.pochackernews.onboarding;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseActivity;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.application.model.User;
import tricahshasps.com.pochackernews.home.HomeActivity;
import tricahshasps.com.pochackernews.utils.SharedPreferenceUtils;

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

    private ProgressDialog progressDialog;

    public static Intent newIntent(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        return intent;
    }

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
        initProgressDialog();
        initUser();
        initPresenter();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
    }

    private void initUser() {
        user = new User();
    }

    private void initPresenter() {
        presenter = new LoginPresenter();
    }

    @Override
    public void login() {
        if (!validate()) {
            return;
        }
        user.setAcct(etUsername.getText().toString());
        user.setPw(etPassword.getText().toString());
        user.setCreating(null);
        presenter.login(user);
    }

    @Override
    public void showProgress() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }

    @Override
    public void createAccount() {
        if (!validate()) {
            return;
        }
        user.setAcct(etUsername.getText().toString());
        user.setPw(etPassword.getText().toString());
        user.setCreating("t");//This is what hacker news guys accept to create account

        presenter.createAccount(user);
    }

    @Override
    public void onLoginSuccessful(User user) {
        SharedPreferenceUtils.getInstance().saveData(Constants.BUNDLE_KEYS.USER_LOGIN_STATUS, true);
        hideProgress();
        gotoActivity(HomeActivity.newIntent(this));
        finish();
    }


    private void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void showMessage(String message, int colourResourceId) {
        super.showMessage(message, colourResourceId);
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
        login();
    }

    @OnClick(R.id.btn_register)
    public void onRegisterButtonClicked() {
        createAccount();
    }

    @Override
    public Context getContext() {
        return this;
    }


}
