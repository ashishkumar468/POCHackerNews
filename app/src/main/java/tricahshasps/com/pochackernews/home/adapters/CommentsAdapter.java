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
import tricahshasps.com.pochackernews.home.model.Story;

/**
 * Created by Ashish on 28/10/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    List<Story> comments;

    private Context context;
    private callback callback;

    public CommentsAdapter() {
        this.comments = new ArrayList<>();
    }

    public void setCallback(callback callback) {
        this.callback = callback;
    }

    public void setComments(List<Story> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_comment, parent, false);
        this.context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setCommentData(Story commentData) {
        int i = 0;
        for (Story comment : comments) {
            if (comment.getId() == commentData.getId()) {
                comments.set(i, commentData);
                notifyItemChanged(i);
                i++;
                break;

            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_container_comment)
        LinearLayout llContainerComment;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_number_of_comments)
        TextView tvNumberOfComments;

        @BindView(R.id.tv_number_of_upvotes)
        TextView tvNumberOfUpVotes;

        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;

        @BindView(R.id.tv_timestamp)
        TextView tvTimestamp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(int position) {
            Story comment = comments.get(position);
            if (comment.isFetched()) {
                tvTitle.setText(comment.getTitle());
                tvNumberOfComments.setText(context.getString(R.string.number_of_comments, comment.getNumberOfComments()));
                tvNumberOfUpVotes.setText(context.getString(R.string.number_of_upvotes, comment.getNumberOfUpvotes()));
                // TODO: 28/10/17 Change timestamp to human readable date
                tvTimestamp.setText(comment.getTime() + "");
                tvAuthorName.setText(context.getString(R.string.author_name, comment.getAuthorName()));
            } else {
                callback.fetchComment(comment.getId());
            }
        }
    }

    public interface callback {
        void fetchComment(long commentId);
    }
}
