package tricahshasps.com.pochackernews.home.presenters;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import tricahshasps.com.pochackernews.application.ApiConstants;
import tricahshasps.com.pochackernews.application.App;
import tricahshasps.com.pochackernews.application.BasePresenter;
import tricahshasps.com.pochackernews.home.ICommentsContract;
import tricahshasps.com.pochackernews.home.model.Story;

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
    public void fetchComment(long CommentId) {
        firebaseClientRef.child(ApiConstants.STORIES.GET_ITEM + "/" + CommentId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String commentString = new Gson().toJson(dataSnapshot.getValue());
                        tricahshasps.com.pochackernews.utils.Logger.logError(commentString);
                        Story value = new Gson().fromJson(commentString, Story.class);
                        if (view != null) {
                            view.showComment(value);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
    }
}
