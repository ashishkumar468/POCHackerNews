package tricahshasps.com.pochackernews.application;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ashish on 28/10/17.
 */


/**
 * This activity serves as the base class of all the activities in the app. Contains some basic utility methods .
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {
    @Override
    public void showMessage(String message) {
        // TODO: 28/10/17
    }


    public void snack(View view, String message, int notificationType, int duration) {
        Snackbar snackbar = Snackbar.make(view, message, duration)
                .setAction("Action", null);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, notificationType));
        snackbar.show();
    }
}

