package tricahshasps.com.pochackernews.home;

import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */

public interface ICommentsContract {
    interface View {
        void showComment(Story comment);
    }

    interface Presenter {
        void fetchComment(long CommentId);
    }
}
