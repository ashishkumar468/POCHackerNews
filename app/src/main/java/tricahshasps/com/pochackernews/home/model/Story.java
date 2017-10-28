package tricahshasps.com.pochackernews.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 28/10/17.
 */

public class Story implements Parcelable {
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

    private int level;

    private boolean isFetched = false;
    private boolean isKidAdded;


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

    public String getUrl() {
        return url;
    }

    public List<Story> getKids() {
        List<Story> comments = new ArrayList<>();
        if (kids != null) {
            for (Long kid : kids) {
                Story comment = new Story();
                comment.setLevel(this.level + 1);
                comment.setId(kid);
                comments.add(comment);
            }
        }
        return comments;
    }

    public void setIsKidAdded(boolean isKidAdded) {
        this.isKidAdded = isKidAdded;
    }

    public boolean isKidAdded() {
        return isKidAdded;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.authorName);
        dest.writeLong(this.numberOfDescendants);
        dest.writeList(this.kids);
        dest.writeInt(this.score);
        dest.writeLong(this.time);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeInt(this.level);
        dest.writeByte(this.isFetched ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isKidAdded ? (byte) 1 : (byte) 0);
    }

    public Story() {
    }

    protected Story(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.authorName = in.readString();
        this.numberOfDescendants = in.readLong();
        this.kids = new ArrayList<Long>();
        in.readList(this.kids, Long.class.getClassLoader());
        this.score = in.readInt();
        this.time = in.readLong();
        this.type = in.readString();
        this.url = in.readString();
        this.level = in.readInt();
        this.isFetched = in.readByte() != 0;
        this.isKidAdded = in.readByte() != 0;
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };
}
