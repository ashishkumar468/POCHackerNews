package tricahshasps.com.pochackernews.home;

import android.app.Activity;
import android.content.Intent;

import tricahshasps.com.pochackernews.application.BaseActivity;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.home.model.Comment;
import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoryDetailsActivity extends BaseActivity implements IStoryDetailsContract.StoryView {
    public static Intent newIntent(Activity activity, int id) {
        Intent intent = new Intent(activity, StoryDetailsActivity.class);
        intent.putExtra(Constants.BUNDLE_KEYS.STORY_ID, id);
        return intent;
    }

    @Override
    public void showStory(Story story) {
        // TODO: 28/10/17  
    }

    @Override
    public void showComments(Comment comment) {
        // TODO: 28/10/17
    }
}
