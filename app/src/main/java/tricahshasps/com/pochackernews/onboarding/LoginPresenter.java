package tricahshasps.com.pochackernews.onboarding;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BasePresenter;
import tricahshasps.com.pochackernews.application.communication.BusProvider;
import tricahshasps.com.pochackernews.application.communication.LoggedInCreatedUserEvent;
import tricahshasps.com.pochackernews.application.communication.events.LoginSignupUserEvent;
import tricahshasps.com.pochackernews.application.model.User;
import tricahshasps.com.pochackernews.utils.MiscUtils;

/**
 * Created by Ashish on 28/10/17.
 */

class LoginPresenter implements BasePresenter<ILoginContract.View>, ILoginContract.Presenter {
    private ILoginContract.View view;
    Bus mBus = BusProvider.getInstance();

    @Override
    public void attachView(ILoginContract.View view) {
        this.view = view;
        mBus.register(this);
    }

    @Override
    public void detachView() {
        this.view = null;
        mBus.unregister(this);
    }

    @Override
    public void login(User user) {
        if (MiscUtils.isConnectedToInternet()) {
            view.showProgress();
            mBus.post(new LoginSignupUserEvent(user));
        } else {
            view.showMessage(view.getContext().getString(R.string.no_internet_connection), R.color.color_warning);
        }
    }

    @Override
    public void createAccount(User user) {
        if (MiscUtils.isConnectedToInternet()) {
            view.showProgress();
            mBus.post(new LoginSignupUserEvent(user));
        } else {
            view.showMessage(view.getContext().getString(R.string.no_internet_connection), R.color.color_warning);
        }
    }

    @Subscribe
    public void onUserLoginSignupResponse(LoggedInCreatedUserEvent event) {
        view.hideProgress();
        if (event.isStatus()) {
            view.showMessage(view.getContext().getString(R.string.login_sucessfull),R.color.color_success);
            view.onLoginSuccessful(event.getUser());
        } else {
            view.showMessage(event.getMessage(), R.color.color_error);
        }
    }
}
