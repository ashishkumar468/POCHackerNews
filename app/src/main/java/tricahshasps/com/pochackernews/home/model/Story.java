package tricahshasps.com.pochackernews.home.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 28/10/17.
 */

public class Story {
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title = "";

    @SerializedName("by")
    private String authorName;
    @SerializedName("descendants")
    private long numberOfDescendants;
    @SerializedName("kids")
    private List<Long> kids;
    @SerializedName("score")
    private int score;
    @SerializedName("time")
    private long time;
    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;

    private boolean isFetched = false;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfComments() {
        if (kids == null || kids.size() == 0) {
            return 0;
        }
        return kids.size();
    }

    public int getNumberOfUpvotes() {
        return score;
    }

    public void setNumberOfUpvotes(int numberOfUpvotes) {
        this.score = score;
    }

    public boolean isFetched() {
        return isFetched;
    }

    public void setFetched(boolean fetched) {
        isFetched = fetched;
    }

    public long getTime() {
        return time;
    }

    public String getAuthorName() {
        return authorName;
    }
}
