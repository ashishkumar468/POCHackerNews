package tricahshasps.com.pochackernews.home.model;

/**
 * Created by Ashish on 28/10/17.
 */

public class Story {
    private int id;
    private String title;
    private int numberOfComments;
    private int numberOfUpvotes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public int getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    public void setNumberOfUpvotes(int numberOfUpvotes) {
        this.numberOfUpvotes = numberOfUpvotes;
    }
}
