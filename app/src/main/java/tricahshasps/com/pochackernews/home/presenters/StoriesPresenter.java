package tricahshasps.com.pochackernews.home.presenters;

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

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesPresenter implements BasePresenter<IStoryContract.View>, IStoryContract.Presenter {

    private IStoryContract.View view;

    private Firebase firebaseClientRef;

    private Firebase child;

    private List<Story> stories;

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
    public void getStories(String tag) {
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
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot snapshot : children) {
                    Story story = new Story();
                    story.setId((Long) snapshot.getValue());
                    stories.add(story);
                }
                if (view != null)
                    view.showStories(stories);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });
    }

    @Override
    public void getStory(long storyId) {
        firebaseClientRef.child(ApiConstants.STORIES.GET_ITEM + "/" + storyId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String storyString = new Gson().toJson(dataSnapshot.getValue());
                        Story value = new Gson().fromJson(storyString, Story.class);
                        if (view != null) {
                            view.showStory(value);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
    }
}
