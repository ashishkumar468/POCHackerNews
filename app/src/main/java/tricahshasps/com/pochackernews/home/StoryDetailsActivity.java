package tricahshasps.com.pochackernews.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseActivity;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.home.fragments.CommentsFragment;
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

    @BindView(R.id.fl_container_comments)
    FrameLayout flContainerComments;

    @BindView(R.id.bottom_sheet)
    View botomSheet;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private Story story;


    private BottomSheetBehavior<View> bottomSheetBeahviour;

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
        initBottomSheetBehaviour();
    }

    private void initBottomSheetBehaviour() {
        bottomSheetBeahviour = BottomSheetBehavior.from(botomSheet);
        bottomSheetBeahviour.setPeekHeight(30);
    }

    private void showCompleteData() {
        showStory(story);
        prepareCommentsView();
    }

    private void prepareCommentsView() {
        CommentsFragment commentsFragment = CommentsFragment.getInstance(story.getKids());
        getSupportFragmentManager().beginTransaction()
                .add(flContainerComments.getId(), commentsFragment).commit();
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
        wvStory.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.VISIBLE);
                wvStory.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
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
