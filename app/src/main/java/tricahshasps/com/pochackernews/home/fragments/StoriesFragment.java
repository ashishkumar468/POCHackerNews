package tricahshasps.com.pochackernews.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.BaseFragment;
import tricahshasps.com.pochackernews.home.adapters.StoriesAdapter;
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.utils.MockUtils;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesFragment extends BaseFragment {

    @BindView(R.id.rv_stories)
    RecyclerView rvStories;


    private String storyType;

    private StoriesAdapter adapter;


    List<Story> stories;
    List<Object> baseList;

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

    private void init() {
        initAdapter();
        initData();
    }

    private void initData() {
        List<Story> stories = MockUtils.getStories();
        adapter.setItems(stories);
    }

    private void initAdapter() {
        adapter = new StoriesAdapter();
        rvStories.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStories.setAdapter(adapter);
    }
}
