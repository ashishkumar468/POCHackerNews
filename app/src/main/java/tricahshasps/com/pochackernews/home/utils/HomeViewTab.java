package tricahshasps.com.pochackernews.home.utils;

import android.support.v4.app.Fragment;

/**
 * Created by Ashish on 28/10/17.
 */

public class HomeViewTab {
    private Fragment fragment;
    private String title;

    public HomeViewTab(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }
}
