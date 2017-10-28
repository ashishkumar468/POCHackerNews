package tricahshasps.com.pochackernews.onboarding;

import tricahshasps.com.pochackernews.application.model.User;

/**
 * Created by Ashish on 28/10/17.
 */

interface ILoginContract {
    interface View {
        void login();

        void createAccount();

        void onLoginSuccessful(User user);

        void onLoginFailed(String message);
    }

    interface Presenter {
        void login(User user);
        void createAccount(User user);
    }
}
