package tricahshasps.com.pochackernews.home.adapters;

import android.content.Context;
import android.os.Handler;
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
import tricahshasps.com.pochackernews.home.model.Story;
import tricahshasps.com.pochackernews.utils.DateUtils;
import tricahshasps.com.pochackernews.utils.Logger;

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
                break;
            }
            i++;
        }
        notifyItemChanged(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_container_comment)
        LinearLayout llContainerComment;

        @BindView(R.id.tv_title)
        TextView tvTitle;

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

        public void init(final int position) {
            final Story comment = comments.get(position);
            llContainerComment.setPadding(Constants.DEFAULT_PADDING_FOR_COMMENTS * comment.getLevel(), 0, 0, 0);

            switch (comment.getStatus()) {
                case Constants.ITEM_STATUS.FETCHING:
                    if (comment.getLevel() > 0) {
                        Logger.logError("fetching for comment " + comment.getId());
                    }
                    llContainerComment.setBackgroundColor(context.getResources().getColor(R.color.color_fetching));
                    //When Fetching..means we have to fetch data
                    callback.fetchComment(comment.getId());
                    break;
                case Constants.ITEM_STATUS.FETCHED:
                    llContainerComment.setBackgroundColor(context.getResources().getColor(R.color.color_fetched));
                    //When comment is fetched..show its data
                    tvTitle.setText(comment.getTitle());
                    tvNumberOfUpVotes.setText(context.getString(R.string.number_of_upvotes, comment.getNumberOfUpvotes()));
                    tvTimestamp.setText(DateUtils.getHumanReadableDate(comment.getTime()));
                    tvAuthorName.setText(context.getString(R.string.author_name, comment.getAuthorName()));
                    //To dynamically add comments to the recycler view..if the comments have kids as well and that goes on recursively
                    if (!comment.isKidAdded() && comment.getKids().size() > 0) {
                        comment.setIsKidAdded(true);
                        Handler handler = new Handler();
                        final Runnable r = new Runnable() {
                            public void run() {
                                comments.addAll(position + 1, comment.getKids());
                                //Setting comments level so in the model class so as to form a branch kind of thing
                                Logger.logError("Adding kids of level " + comment.getKids().get(0).getLevel());
                                notifyItemRangeInserted(position + 1, comment.getKids().size() );
                            }
                        };
                        handler.post(r);
                    }
                    break;
                case Constants.ITEM_STATUS.FAILED:
                    llContainerComment.setBackgroundColor(context.getResources().getColor(R.color.color_failed));
                    //When failed..lets retry..but yeah let user know that it failed..or user is seeing stale data
                    callback.fetchComment(comment.getId());
                    break;
            }
        }
    }

    public interface callback {
        void fetchComment(long commentId);
    }
}
