package tricahshasps.com.pochackernews.utils;

import java.util.ArrayList;
import java.util.List;

import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */

public class MockUtils {

    public static List<Story> getStories() {
        List<Story> stories = new ArrayList<>();
        Story story1 = new Story();
        story1.setId(1);
        story1.setNumberOfUpvotes(5);
        story1.setTitle("Title 1");

        Story story2 = new Story();
        story2.setId(1);
        story2.setNumberOfUpvotes(6);
        story2.setTitle("Title 2");

        Story story3 = new Story();
        story3.setId(1);
        story3.setNumberOfUpvotes(5);
        story3.setTitle("Title 3");

        Story story4 = new Story();
        story4.setId(1);
        story4.setNumberOfUpvotes(5);
        story4.setTitle("Title 4");

        stories.add(story1);
        stories.add(story2);
        stories.add(story3);
        stories.add(story4);

        return stories;
    }
}
