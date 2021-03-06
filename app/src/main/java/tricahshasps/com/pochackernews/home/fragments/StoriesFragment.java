package tricahshasps.com.pochackernews.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseFragment;
import tricahshasps.com.pochackernews.home.HomeActivity;
import tricahshasps.com.pochackernews.home.IStoryContract;
import tricahshasps.com.pochackernews.home.adapters.StoriesAdapter;
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.home.presenters.StoriesPresenter;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesFragment extends BaseFragment implements IStoryContract.View {

    @BindView(R.id.rv_stories)
    RecyclerView rvStories;

    @BindView(R.id.pb_stories)
    ProgressBar pbStories;


    private String storyType;

    private StoriesAdapter adapter;

    private StoriesPresenter presenter;

    public static Fragment getInstance(String storyType) {
        StoriesFragment storiesFragment = new StoriesFragment();
        storiesFragment.setStoryType(storyType);
        return storiesFragment;
    }

    private void setStoryType(String storyType) {
        this.storyType = storyType;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_stories, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void init() {
        initPresenter();
        initAdapter();
        initData();
    }

    private void initPresenter() {
        presenter = new StoriesPresenter();
    }

    private void initData() {
        showFetchingState();
        presenter.getStories(storyType);
    }

    private void initAdapter() {
        adapter = new StoriesAdapter();
        rvStories.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setCallback(new StoriesAdapter.callback() {
            @Override
            public void fetchStory(long storyId) {
                presenter.getStory(storyId);
            }
        });
        rvStories.setAdapter(adapter);
    }

    @Override
    public void showStories(List<Story> stories) {
        //No need of pagination over here..firebase anyways gives data in chunks and the remaining part is handled by the recycler view.
        //makes call for only those stories which are currently visible
        adapter.setItems(stories);//Found better to replace rather than iterate and prune to remove the existing ones
        showFetchedState();
    }

    @Override
    public void showStory(Story story) {
        adapter.setStory(story);
    }

    @Override
    public void showFetchingState() {
        pbStories.setVisibility(View.VISIBLE);
        rvStories.setVisibility(View.GONE);
    }

    @Override
    public void showFetchedState() {
        pbStories.setVisibility(View.GONE);
        rvStories.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFailureState(String message) {
        pbStories.setVisibility(View.GONE);
        rvStories.setVisibility(View.GONE);
        ((HomeActivity) getContext()).showMessage(message, R.color.color_error);
    }
}
