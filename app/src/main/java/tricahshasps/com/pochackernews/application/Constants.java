package tricahshasps.com.pochackernews.application;

import tricahshasps.com.pochackernews.BuildConfig;

/**
 * Created by Ashish on 28/10/17.
 */

public class Constants {
    public static final String PREFERENCES_FILENAME = "pocHackerNews";
    public static final String LOGGER_TAG = BuildConfig.APPLICATION_ID;

    public class FragmentsTags {
        public static final String TOP_STORIES = "TOP_STORIES";
        public static final String NEW_STORIES = "NEW_STORIES";
        public static final String BEST_STORIES = "BEST_STORIES";
    }

    public class ITEM_TYPE {
        public static final int STORY = 1;
        public static final int DEAFULT = 2;
    }
}
