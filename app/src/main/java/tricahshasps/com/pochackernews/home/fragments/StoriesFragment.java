package tricahshasps.com.pochackernews.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseFragment;
import tricahshasps.com.pochackernews.home.IStoryContract;
import tricahshasps.com.pochackernews.home.adapters.StoriesAdapter;
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.home.presenters.StoriesPresenter;
import tricahshasps.com.pochackernews.utils.MockUtils;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesFragment extends BaseFragment implements IStoryContract.View {

    @BindView(R.id.rv_stories)
    RecyclerView rvStories;


    private String storyType;

    private StoriesAdapter adapter;


    List<Story> stories;

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
        List<Story> stories = MockUtils.getStories();
        adapter.setItems(stories);

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
        adapter.setItems(stories);
    }

    @Override
    public void showStory(Story story) {
        story.setFetched(true);
        adapter.setStory(story);
    }
}
