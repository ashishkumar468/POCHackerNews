package tricahshasps.com.pochackernews.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.application.Constants;
import tricahshasps.com.pochackernews.home.HomeActivity;
import tricahshasps.com.pochackernews.home.StoryDetailsActivity;
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.utils.DateUtils;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {
    List<Story> stories;
    private Context context;
    private callback callback;

    public StoriesAdapter() {
        this.stories = new ArrayList<>();
    }

    public void setItems(List<Story> items) {
        this.stories = items;
        notifyDataSetChanged();
    }

    public void setCallback(StoriesAdapter.callback callback) {
        this.callback = callback;
    }

    public void addItems(List<Story> objects) {
        this.stories.addAll(0, objects);
        notifyItemRangeInserted(0, objects.size());
    }

    public void setStory(Story storyWithData) {
        int i = 0;
        for (Story story : stories) {
            if (story.getId() == storyWithData.getId()) {
                stories.set(i, storyWithData);
                notifyItemChanged(i);
                break;
            }
            i++;
        }
    }

    @Override
    public StoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_stories, parent, false);
        this.context = parent.getContext();
        StoriesViewHolder holder = new StoriesViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(StoriesViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class StoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_container_story)
        LinearLayout llContainerStory;

        @BindView(R.id.tv_text)
        TextView tvTitle;

        @BindView(R.id.tv_number_of_comments)
        TextView tvNumberOfComments;

        @BindView(R.id.tv_number_of_upvotes)
        TextView tvNumberOfUpVotes;

        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;

        @BindView(R.id.tv_timestamp)
        TextView tvTimestamp;

        public StoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(int position) {
            final Story story = stories.get(position);
            switch (story.getStatus()) {
                case Constants.ITEM_STATUS.FETCHING:
                    llContainerStory.setBackgroundColor(context.getResources().getColor(R.color.color_fetching));
                    //When Fetching..means we have to fetch data
                    callback.fetchStory(stories.get(position).getId());
                    break;
                case Constants.ITEM_STATUS.FETCHED:
                    llContainerStory.setBackgroundColor(context.getResources().getColor(R.color.color_fetched));
                    tvTitle.setText(story.getTitle());
                    tvNumberOfComments.setText(context.getString(R.string.number_of_comments, story.getNumberOfComments()));
                    tvNumberOfUpVotes.setText(context.getString(R.string.number_of_upvotes, story.getNumberOfUpvotes()));
                    tvTimestamp.setText(DateUtils.getHumanReadableDate(story.getTime()));
                    tvAuthorName.setText(context.getString(R.string.author_name, story.getAuthorName()));

                    break;
                case Constants.ITEM_STATUS.FAILED:
                    llContainerStory.setBackgroundColor(context.getResources().getColor(R.color.color_failed));
                    //When failed..lets retry..but yeah let user know that it failed
                    callback.fetchStory(stories.get(position).getId());
                    break;
            }
            llContainerStory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //As there is nothing left to fetch more from this, lets pass the story object itself
                    if (story.getStatus() == Constants.ITEM_STATUS.FETCHED) {//Let user click only when the story is fetched
                        context.startActivity(StoryDetailsActivity.newIntent(((HomeActivity) context), story));
                    }
                }
            });
        }
    }

    public interface callback {
        void fetchStory(long storyId);
    }
}
