package ru.khannanovayrat.vkclient.newsletter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.khannanovayrat.vkclient.R;
import ru.khannanovayrat.vkclient.network.attachment.Attachment;
import ru.khannanovayrat.vkclient.network.attachment.VkAttachment;
import ru.khannanovayrat.vkclient.network.attachment.VkPhotoAttachment;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public class ImagePreviewAdapter extends RecyclerView.Adapter<ImagePreviewAdapter.ViewHolder> {

    private List<VkPhotoAttachment> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_preview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        boolean firstSkipped = false;
        for (VkAttachment attachment : mData) {
            if (attachment.getType() == Attachment.PHOTO) {
                //first photo already loaded as a main image
                if (!firstSkipped) {
                    firstSkipped = true;
                    continue;
                }
                VkPhotoAttachment photoAttachment = mData.get(position);
                Picasso.with(holder.mImageView.getContext()).load(photoAttachment.getPhoto().getPhoto75()).into(holder.mImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<VkPhotoAttachment> data) {
        mData = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView;
        }
    }
}
