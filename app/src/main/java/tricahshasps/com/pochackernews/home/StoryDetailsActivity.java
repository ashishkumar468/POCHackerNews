package tricahshasps.com.pochackernews.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseActivity;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.home.model.Comment;
import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */


/**
 * Activity has two main jobs, display the story in a web view, and show comments corresponding to that story
 */
public class StoryDetailsActivity extends BaseActivity implements IStoryDetailsContract.StoryView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.wv_story)
    WebView wvStory;

    private Story story;

    public static Intent newIntent(Activity activity, Story story) {
        Intent intent = new Intent(activity, StoryDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEYS.STORY, story);
        intent.putExtra(Constants.BUNDLE_KEYS.STORY_BUNDLE, bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        setUpToolbar(toolbar, R.string.title_story_detail_activity); // TODO: 28/10/17 Change title to story detail
        init();
    }

    private void init() {
        initPresenter();
        initWebView();
        initData();
        showCompleteData();
    }

    private void showCompleteData() {
        showStory(story);
        prepareCommentsView();
    }

    private void prepareCommentsView() {

    }

    private void initData() {
        Bundle bundleExtra = getIntent().getBundleExtra(Constants.BUNDLE_KEYS.STORY_BUNDLE);
        story = (Story) bundleExtra.get(Constants.BUNDLE_KEYS.STORY);
        setToolbarTitle();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(story.getTitle());
    }

    private void initWebView() {
        WebSettings webSettings = wvStory.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void initPresenter() {

    }


    @Override
    public void showStory(Story story) {
        wvStory.loadUrl(story.getUrl());
    }

    @Override
    public void showComments(Comment comment) {
        // TODO: 28/10/17
    }
}
