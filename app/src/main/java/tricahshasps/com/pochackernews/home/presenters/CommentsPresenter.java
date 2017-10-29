package tricahshasps.com.pochackernews.home.presenters;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import tricahshasps.com.pochackernews.application.ApiConstants;
import tricahshasps.com.pochackernews.application.App;
import tricahshasps.com.pochackernews.application.BasePresenter;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.home.ICommentsContract;
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.utils.Logger;

/**
 * Created by Ashish on 28/10/17.
 */

public class CommentsPresenter implements BasePresenter<ICommentsContract.View>, ICommentsContract.Presenter {
    private final Firebase firebaseClientRef;
    private ICommentsContract.View view;

    public CommentsPresenter() {
        firebaseClientRef = App.getFirebaseClientRef();
    }

    @Override
    public void attachView(ICommentsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void fetchComment(final long commentId) {
        Logger.logError("fetching for comment " + commentId);
        firebaseClientRef.child(ApiConstants.STORIES.GET_ITEM + "/" + commentId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String commentString = new Gson().toJson(dataSnapshot.getValue());
                        tricahshasps.com.pochackernews.utils.Logger.logError(commentString);
                        Story value = new Gson().fromJson(commentString, Story.class);
                        value.setStatus(Constants.ITEM_STATUS.FETCHED);
                        if (view != null) {
                            view.showComment(value);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Story comment = new Story();
                        comment.setId(commentId);
                        comment.setStatus(Constants.ITEM_STATUS.FAILED);
                        view.showComment(comment);
                        comment = null;
                        Logger.logError("Could not fetch for comment id " + commentId);
                    }
                });
    }
}
