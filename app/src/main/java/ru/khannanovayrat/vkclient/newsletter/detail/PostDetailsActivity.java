package ru.khannanovayrat.vkclient.newsletter.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.khannanovayrat.vkclient.R;
import ru.khannanovayrat.vkclient.network.ApiAsyncManager;
import ru.khannanovayrat.vkclient.network.attachment.Attachment;
import ru.khannanovayrat.vkclient.network.attachment.VkAttachment;
import ru.khannanovayrat.vkclient.network.attachment.VkPhotoAttachment;
import ru.khannanovayrat.vkclient.network.wall.response.VkWallPost;
import ru.khannanovayrat.vkclient.network.wall.response.VkWallPostResponseWrapper;
import ru.khannanovayrat.vkclient.newsletter.ImagePreviewAdapter;
import ru.khannanovayrat.vkclient.util.PostUtils;

import static ru.khannanovayrat.vkclient.util.PostUtils.getPhotoAttachments;
import static ru.khannanovayrat.vkclient.util.PostUtils.selectNonNullUrl;

public class PostDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POST_ID = "extra_post_id";

    private String mPostId;
    private ApiAsyncManager mApiAsyncManager;

    private ImageView mAvatarImageView;
    private TextView mContentTextView;
    private TextView mDateTextView;
    private TextView mNameTextView;
    private ImageView mMainImage;
    private RecyclerView mImagesRecyclerView;
    private CheckBox mLikeCheckBox;

    public static Intent newIntent(Context context, String postId) {
        return new Intent(context, PostDetailsActivity.class).putExtra(EXTRA_POST_ID, postId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        mAvatarImageView = findViewById(R.id.avatar_image_view);
        mContentTextView = findViewById(R.id.content_text_view);
        mDateTextView = findViewById(R.id.date_text_view);
        mNameTextView = findViewById(R.id.name_text_view);
        mMainImage = findViewById(R.id.main_image_view);
        mImagesRecyclerView = findViewById(R.id.images_recycler_view);
        mLikeCheckBox = findViewById(R.id.like_check_box);
        mApiAsyncManager = new ApiAsyncManager(getApplicationContext());
        if (getIntent().getExtras() != null) {
            mPostId = getIntent().getExtras().getString(EXTRA_POST_ID);
        }
        loadPost();
    }

    private void loadPost() {
        if (mPostId != null) {
            mApiAsyncManager
                    .getPostsById(mPostId)
                    .subscribe(new Observer<VkWallPostResponseWrapper>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(VkWallPostResponseWrapper vkWallPostResponseWrapper) {
                            if (vkWallPostResponseWrapper != null && vkWallPostResponseWrapper.getResponse() != null) {
                                fillViews(vkWallPostResponseWrapper.getResponse().getPosts().get(0));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private void fillViews(VkWallPost vkWallPost) {
        mContentTextView.setText(vkWallPost.getText());
        long dateMillis = (long) vkWallPost.getDate() * 1000;
        mDateTextView.setText(PostUtils.getFormattedDateTime(dateMillis));
        if (vkWallPost.getLikes() != null) {
            mLikeCheckBox.setChecked(vkWallPost.getLikes().isUserLikes());
            mLikeCheckBox.setText(String.valueOf(vkWallPost.getLikes().getCount()));
        }
        if (vkWallPost.getAttachments() != null && vkWallPost.getAttachments().size() > 0) {
            mMainImage.setVisibility(View.VISIBLE);
            if (vkWallPost.getAttachments().size() > 1) {
                mImagesRecyclerView.setVisibility(View.VISIBLE);
            } else {
                mImagesRecyclerView.setVisibility(View.GONE);
            }
            loadMainImage(getPhotoAttachments(vkWallPost.getAttachments()));
            mImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            ImagePreviewAdapter imagePreviewAdapter = new ImagePreviewAdapter();
            imagePreviewAdapter.setData(getPhotoAttachments(vkWallPost.getAttachments()));
            mImagesRecyclerView.setAdapter(imagePreviewAdapter);
        } else {
            mMainImage.setVisibility(View.GONE);
            mImagesRecyclerView.setVisibility(View.GONE);
        }
    }

    private void loadMainImage(List<VkPhotoAttachment> attachments) {
        for (VkAttachment attachment : attachments) {
            if (attachment.getType() == Attachment.PHOTO) {
                VkPhotoAttachment photoAttachment = ((VkPhotoAttachment) attachment);
                String url = selectNonNullUrl(photoAttachment.getPhoto());
                if (url != null) {
                    Picasso.with(this).load(url).into(mMainImage);
                }
                break;
            }
        }
    }


}
