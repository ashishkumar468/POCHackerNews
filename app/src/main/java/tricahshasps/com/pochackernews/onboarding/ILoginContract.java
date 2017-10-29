package tricahshasps.com.pochackernews.onboarding;

import android.content.Context;

import tricahshasps.com.pochackernews.application.MvpView;
import tricahshasps.com.pochackernews.application.model.User;

/**
 * Created by Ashish on 28/10/17.
 */

interface ILoginContract {
    interface View extends MvpView {
        void login();

        void createAccount();

        void onLoginSuccessful(User user);

        void showMessage(String message, int colourResourceId);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void login(User user);

        void createAccount(User user);
    }
}
