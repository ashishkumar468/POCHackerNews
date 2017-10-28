package tricahshasps.com.pochackernews.home;


import tricahshasps.com.pochackernews.home.model.Comment;
import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */

interface IStoryDetailsContract {
     interface StoryView {
         void showStory(Story story);
         void showComments(Comment comment);
    }
}
