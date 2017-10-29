package tricahshasps.com.pochackernews.application;

/**
 * Created by Ashish on 28/10/17.
 */

public class ApiConstants {
    public static final String FIREBASE_BASE_URL = "https://hacker-news.firebaseio.com/v0";
    public static final String HACKER_NEWS_BASE_URL = "https://news.ycombinator.com";
    public static final long CONNECTION_TIMEOUT = 10;

    public class STORIES {
        public static final String TOP_STORIES = "/topstories";
        public static final String NEW_STORIES = "/newstories";
        public static final String BEST_STORIES = "/beststories";
        public static final String GET_ITEM = "/item";
    }

    public class REST_API {
        public static final String LOGIN_SIGNUP = "/login";
        public static final String LOGIN_URL_EXTENSION = "/login?go_to=news";
    }
}
