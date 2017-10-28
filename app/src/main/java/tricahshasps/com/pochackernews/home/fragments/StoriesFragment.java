package tricahshasps.com.pochackernews.home.fragments;

import android.support.v4.app.Fragment;

import tricahshasps.com.pochackernews.application.BaseFragment;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesFragment extends BaseFragment {


    private String storyType;

    public static Fragment getInstance(String storyType) {
        StoriesFragment storiesFragment = new StoriesFragment();
        storiesFragment.setStoryType(storyType);
        return storiesFragment;
    }

    private void setStoryType(String storyType) {
        this.storyType = storyType;
    }

}
