package tricahshasps.com.pochackernews.onboarding;

import tricahshasps.com.pochackernews.application.BasePresenter;
import tricahshasps.com.pochackernews.application.model.User;

/**
 * Created by Ashish on 28/10/17.
 */

class LoginPresenter implements BasePresenter<ILoginContract.View>, ILoginContract.Presenter {
    private ILoginContract.View view;

    @Override
    public void attachView(ILoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void login(User user) {
        // TODO: 28/10/17  
    }

    @Override
    public void createAccount(User user) {
        // TODO: 28/10/17  
    }
}
