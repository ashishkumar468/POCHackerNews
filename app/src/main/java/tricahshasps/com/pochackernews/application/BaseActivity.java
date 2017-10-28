package tricahshasps.com.pochackernews.application;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tricahshasps.com.pochackernews.R;

/**
 * Created by Ashish on 28/10/17.
 */


/**
 * This activity serves as the base class of all the activities in the app. Contains some basic utility methods .
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {
    Toolbar toolbar;

    @Override
    public void showMessage(String message, int colourResourceId) {
        snack(toolbar, message, colourResourceId);
    }


    public void snack(View view, String message, int notificationType) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, notificationType));
        snackbar.show();
    }

    public void setUpToolbar(Toolbar toolbar, int titleResourceId) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(titleResourceId));
        this.toolbar = toolbar;
    }
}

