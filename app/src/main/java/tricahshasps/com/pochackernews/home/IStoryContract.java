package tricahshasps.com.pochackernews.home;

import java.util.List;

import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */

public interface IStoryContract {
    public interface View {
        void showStories(List<Story> stories);

        void showStory(Story story);

        void showFetchingState();

        void showFetchedState();

        void showFailureState(String message);
    }

    public interface Presenter {
        void getStories(String fragmentTag);

        void getStory(long storyId);
    }
}
