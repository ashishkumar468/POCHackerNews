package tricahshasps.com.pochackernews.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseActivity;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.home.adapters.HomeViewPagerAdapter;
import tricahshasps.com.pochackernews.home.utils.HomeViewTab;
import tricahshasps.com.pochackernews.home.fragments.StoriesFragment;

/**
 * Created by Ashish on 28/10/17.
 */

public class HomeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tl_home)
    TabLayout tlHome;

    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private HomeViewPagerAdapter adapter;

    public static Intent newIntent(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setUpToolbar(toolbar, R.string.title_home_activity);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        initViewPager();
    }

    private void initViewPager() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        adapter.setHomeViewTabs(getHomeViewTabs());
        vpHome.setAdapter(adapter);
        vpHome.setOffscreenPageLimit(2);
        tlHome.setupWithViewPager(vpHome);
    }

    private List<HomeViewTab> getHomeViewTabs() {
        //Gives the ability to dynamically update the number of tabs in home screen
        List<HomeViewTab> homeViewTabs = new ArrayList<>();
        HomeViewTab tabTopStories = new HomeViewTab(StoriesFragment.getInstance(Constants.FragmentsTags.TOP_STORIES), getString(R.string.title_top_stories));
        HomeViewTab tabNewStories = new HomeViewTab(StoriesFragment.getInstance(Constants.FragmentsTags.NEW_STORIES), getString(R.string.title_new_stories));
        HomeViewTab tabbestStories = new HomeViewTab(StoriesFragment.getInstance(Constants.FragmentsTags.BEST_STORIES), getString(R.string.title_best_stories));
        homeViewTabs.add(tabTopStories);
        homeViewTabs.add(tabNewStories);
        homeViewTabs.add(tabbestStories);
        return homeViewTabs;
    }
}
