package tricahshasps.com.pochackernews.home.presenters;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import tricahshasps.com.pochackernews.application.ApiConstants;
import tricahshasps.com.pochackernews.application.App;
import tricahshasps.com.pochackernews.application.BasePresenter;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.home.IStoryContract;
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.utils.Logger;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesPresenter implements BasePresenter<IStoryContract.View>, IStoryContract.Presenter {

    private IStoryContract.View view;

    private Firebase firebaseClientRef;

    private Firebase child;

    private List<Story> stories;

    private Story story;

    public StoriesPresenter() {
        firebaseClientRef = App.getFirebaseClientRef();
        stories = new ArrayList<>();
    }

    @Override
    public void attachView(IStoryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getStories(final String tag) {
        switch (tag) {
            case Constants.FragmentsTags.NEW_STORIES:
                child = firebaseClientRef.child(ApiConstants.STORIES.NEW_STORIES);
                break;
            case Constants.FragmentsTags.TOP_STORIES:
                child = firebaseClientRef.child(ApiConstants.STORIES.TOP_STORIES);
                break;
            case Constants.FragmentsTags.BEST_STORIES:
                child = firebaseClientRef.child(ApiConstants.STORIES.BEST_STORIES);
                break;
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                child.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot snapshot : children) {
                            Story story = new Story();
                            story.setId((Long) snapshot.getValue());
                            stories.add(story);
                        }

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (view != null)
                                    view.showStories(stories);
                            }
                        });

                    }

                    @Override
                    public void onCancelled(final FirebaseError firebaseError) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                view.showFailureState(firebaseError.getMessage());
                            }
                        });
                    }
                });
                return null;
            }
        }.execute();

    }

    @Override
    public void getStory(final long storyId) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                firebaseClientRef.child(ApiConstants.STORIES.GET_ITEM + "/" + storyId)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final String storyString = new Gson().toJson(dataSnapshot.getValue());
                                story = new Gson().fromJson(storyString, Story.class);
                                story.setStatus(Constants.ITEM_STATUS.FETCHED);
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (view != null) {
                                            view.showStory(story);
                                            Logger.logError(storyString);
                                        }
                                    }
                                });

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                //Let ui decide what to do with failed data items, i am just gonna let it know
                                story = new Story();
                                story.setId(storyId);
                                story.setStatus(Constants.ITEM_STATUS.FAILED);
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (view != null)
                                            view.showStory(story);
                                        Logger.logError("Failed to fetch" + new Gson().toJson(story));
                                        story = null;
                                    }
                                });

                            }
                        });
                return null;
            }
        }.execute();

    }
}
