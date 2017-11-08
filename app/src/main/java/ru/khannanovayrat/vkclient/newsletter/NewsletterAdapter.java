package ru.khannanovayrat.vkclient.newsletter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import ru.khannanovayrat.vkclient.R;
import ru.khannanovayrat.vkclient.network.attachment.Attachment;
import ru.khannanovayrat.vkclient.network.attachment.VkAttachment;
import ru.khannanovayrat.vkclient.network.attachment.VkPhotoAttachment;

import static ru.khannanovayrat.vkclient.util.PostUtils.selectNonNullUrl;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class NewsletterAdapter extends RecyclerView.Adapter<NewsletterAdapter.PostViewHolder> {

    private List<PostEntity> mData;
    private OnNewsItemClickListener mOnClickListener;

    public NewsletterAdapter(OnNewsItemClickListener clickListener) {
        mOnClickListener = clickListener;
    }

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
        holder.mNameTextView.setText(postEntity.getAuthor());
        Picasso.with(holder.mAvatarImageView.getContext()).load(postEntity.getAvatarUrl()).into(holder.mAvatarImageView);
        if (postEntity.getLikes() != null) {
            holder.mLikeCheckBox.setChecked(postEntity.getLikes().isUserLikes());
            holder.mLikeCheckBox.setText(String.valueOf(postEntity.getLikes().getCount()));
        }
        if (postEntity.getAttachments() != null && postEntity.getAttachments().size() > 0) {
            holder.mMainImage.setVisibility(View.VISIBLE);
            if (postEntity.getAttachments().size() > 1) {
                holder.mImagesRecyclerView.setVisibility(View.VISIBLE);
            } else {
                holder.mImagesRecyclerView.setVisibility(View.GONE);
            }
            loadMainImage(holder.mAvatarImageView.getContext(), postEntity.getAttachments(), holder);
            holder.mImagesRecyclerView.setLayoutManager(new LinearLayoutManager(holder.mAvatarImageView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            ImagePreviewAdapter imagePreviewAdapter = new ImagePreviewAdapter();
            imagePreviewAdapter.setData(postEntity.getAttachments());
            holder.mImagesRecyclerView.setAdapter(imagePreviewAdapter);
        } else {
            holder.mMainImage.setVisibility(View.GONE);
            holder.mImagesRecyclerView.setVisibility(View.GONE);
        }
        holder.mRoot.setOnClickListener(v -> mOnClickListener.onClick(postEntity));
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

    private void loadMainImage(Context context, List<VkPhotoAttachment> attachments, PostViewHolder holder) {
        for (VkAttachment attachment : attachments) {
            if (attachment.getType() == Attachment.PHOTO) {
                VkPhotoAttachment photoAttachment = ((VkPhotoAttachment) attachment);
                String url = selectNonNullUrl(photoAttachment.getPhoto());
                if (url != null) {
                    Picasso.with(context).load(url).into(holder.mMainImage);
                }
                break;
            }
        }
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        View mRoot;
        ImageView mAvatarImageView;
        TextView mContentTextView;
        TextView mDateTextView;
        TextView mNameTextView;
        ImageView mMainImage;
        RecyclerView mImagesRecyclerView;
        CheckBox mLikeCheckBox;

        public PostViewHolder(View itemView) {
            super(itemView);
            mRoot = itemView;
            mAvatarImageView = itemView.findViewById(R.id.avatar_image_view);
            mContentTextView = itemView.findViewById(R.id.content_text_view);
            mDateTextView = itemView.findViewById(R.id.date_text_view);
            mNameTextView = itemView.findViewById(R.id.name_text_view);
            mMainImage = itemView.findViewById(R.id.main_image_view);
            mImagesRecyclerView = itemView.findViewById(R.id.images_recycler_view);
            mLikeCheckBox = itemView.findViewById(R.id.like_check_box);
        }
    }

    public interface OnNewsItemClickListener {
        void onClick(PostEntity postEntity);
    }
}
