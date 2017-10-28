package tricahshasps.com.pochackernews.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tricahshasps.com.pochackernews.R;
import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {
    List<Story> stories;

    public StoriesAdapter() {
        this.stories = new ArrayList<>();
    }

    public void setItems(List<Story> items) {
        this.stories = items;
        notifyDataSetChanged();
    }

    public void addItems(List<Story> objects) {
        this.stories.addAll(0, objects);
        notifyItemRangeInserted(0, objects.size());
    }

    @Override
    public StoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_stories, parent, false);
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

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_number_of_comments)
        TextView tvNumberOfComments;

        @BindView(R.id.tv_number_of_upvotes)
        TextView tvNumberOfUpVotes;

        public StoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(int position) {
            Story story = stories.get(position);
            tvTitle.setText(story.getTitle());
            tvNumberOfComments.setText(String.valueOf(story.getNumberOfComments()));
            tvNumberOfUpVotes.setText(String.valueOf(story.getNumberOfUpvotes()));
        }
    }
}
