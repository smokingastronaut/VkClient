package ru.khannanovayrat.vkclient.newsletter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import ru.khannanovayrat.vkclient.R;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class NewsletterAdapter extends RecyclerView.Adapter<NewsletterAdapter.PostViewHolder> {

    private List<PostEntity> mData;

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsletter_item, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostEntity postEntity = mData.get(position);
        holder.mContentTextView.setText(postEntity.getContent());
        holder.mDateTextView.setText(postEntity.getDate());
        holder.mAvatarImageView.setImageDrawable(postEntity.getAvatar());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(@NonNull List<PostEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void insertData(@NonNull List<PostEntity> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        int insertPosition = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(insertPosition, data.size());
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView mAvatarImageView;
        TextView mContentTextView;
        TextView mDateTextView;

        public PostViewHolder(View itemView) {
            super(itemView);
            mAvatarImageView = itemView.findViewById(R.id.avatar_image_view);
            mContentTextView = itemView.findViewById(R.id.content_text_view);
            mDateTextView = itemView.findViewById(R.id.date_text_view);
        }
    }
}
