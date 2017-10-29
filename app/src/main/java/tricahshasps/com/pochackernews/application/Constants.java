package tricahshasps.com.pochackernews.application;

import tricahshasps.com.pochackernews.BuildConfig;

/**
 * Created by Ashish on 28/10/17.
 */

public class Constants {
    public static final String PREFERENCES_FILENAME = "pocHackerNews";
    public static final String LOGGER_TAG = BuildConfig.APPLICATION_ID;
    public static final int DEFAULT_PADDING_FOR_COMMENTS = 20;
    public static final int DEFAULT_BOTTOM_SHEET_PEEK_HEIGHT = 30;

    public class FragmentsTags {
        public static final String TOP_STORIES = "TOP_STORIES";
        public static final String NEW_STORIES = "NEW_STORIES";
        public static final String BEST_STORIES = "BEST_STORIES";
    }

    public class ITEM_TYPE {
        public static final int STORY = 1;
        public static final int DEAFULT = 2;
    }

    public class BUNDLE_KEYS {
        public static final String STORY_ID = "storyId";
        public static final String STORY = "story";
        public static final String STORY_BUNDLE = "storyBundle";
    }

    public class ITEM_STATUS {
        public static final int FETCHING = 0;
        public static final int FETCHED = 1;
        public static final int FAILED = 2;
    }
}
