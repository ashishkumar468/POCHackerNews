package tricahshasps.com.pochackernews.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import tricahshasps.com.pochackernews.home.ICommentsContract;
import tricahshasps.com.pochackernews.home.adapters.CommentsAdapter;
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.home.presenters.CommentsPresenter;

/**
 * Created by Ashish on 28/10/17.
 */

public class
CommentsFragment extends BaseFragment implements ICommentsContract.View {

    @BindView(R.id.rv_comments)
    RecyclerView rvComments;

    private CommentsAdapter adapter;

    private CommentsPresenter presenter;

    private List<Story> comments;

    private static CommentsFragment instance;

    public static CommentsFragment getInstance(List<Story> comments) {
        instance = new CommentsFragment();
        instance.setComments(comments);
        return instance;
    }

    public void setComments(List<Story> comments) {
        this.comments = comments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_comments, container, false);
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

    private void init() {
        initPresenter();
        initAdapter();
    }

    private void initPresenter() {
        presenter = new CommentsPresenter();
    }

    private void initAdapter() {
        adapter = new CommentsAdapter();
        adapter.setComments(comments);
        adapter.setCallback(new CommentsAdapter.callback() {
            @Override
            public void fetchComment(long commentId) {
                presenter.fetchComment(commentId);
            }
        });
        rvComments.setLayoutManager(new LinearLayoutManager(getContext()));
        rvComments.setAdapter(adapter);
    }

    @Override
    public void showComment(Story comment) {
        comment.setFetched(true);
        adapter.setCommentData(comment);
    }

}
