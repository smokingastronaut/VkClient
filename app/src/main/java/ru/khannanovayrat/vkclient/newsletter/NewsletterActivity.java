package ru.khannanovayrat.vkclient.newsletter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vk.sdk.VKSdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.khannanovayrat.vkclient.R;
import ru.khannanovayrat.vkclient.auth.LoginActivity;
import ru.khannanovayrat.vkclient.network.ApiAsyncManager;
import ru.khannanovayrat.vkclient.network.VkGroupProfile;
import ru.khannanovayrat.vkclient.network.VkProfile;
import ru.khannanovayrat.vkclient.network.VkUserProfile;
import ru.khannanovayrat.vkclient.network.feed.VkFeedPost;
import ru.khannanovayrat.vkclient.network.feed.VkNewsFeedResponse;
import ru.khannanovayrat.vkclient.network.feed.VkNewsFeedResponseWrapper;
import ru.khannanovayrat.vkclient.newsletter.detail.PostDetailsActivity;
import ru.khannanovayrat.vkclient.util.PostUtils;
import ru.khannanovayrat.vkclient.util.PreferenceUtils;

import static ru.khannanovayrat.vkclient.util.PostUtils.getPhotoAttachments;

public class NewsletterActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NewsletterAdapter.OnNewsItemClickListener {

    private RecyclerView mNewsRecyclerView;
    private NewsletterAdapter mNewsAdapter;
    private ApiAsyncManager mAsyncManager;
    private String mNextPostId;
    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsLoading;

    public static Intent newIntent(Context context) {
        return new Intent(context, NewsletterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLoginState();
        setContentView(R.layout.activity_newsletter);
        mAsyncManager = new ApiAsyncManager(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mNewsRecyclerView = findViewById(R.id.news_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mNewsRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNewsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int totalItemsCount = mLinearLayoutManager.getItemCount();
                    int visibleChildCount = mLinearLayoutManager.getChildCount();
                    int scrolledCount = mLinearLayoutManager.findFirstVisibleItemPosition();
                    if (visibleChildCount + scrolledCount == totalItemsCount - 2 && !mIsLoading) {
                        loadFeedFromPost(mNextPostId);
                    }
                }
            }
        });
        mNewsAdapter = new NewsletterAdapter(this);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        loadFeed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newsletter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                VKSdk.logout();
                PreferenceUtils.clearAccessToken(this);
                redirectToLogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        loadFeed();
    }

    @Override
    public void onClick(PostEntity postEntity) {
        startDetailActivity(postEntity.getPostId());
    }

    private void checkLoginState() {
        if (!VKSdk.isLoggedIn()) {
            redirectToLogin();
        }
    }

    private void redirectToLogin() {
        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    private void startDetailActivity(String postId) {
        startActivity(PostDetailsActivity.newIntent(this, postId));
    }

    private void loadFeed() {
        mIsLoading = true;
        mAsyncManager.getFeed().subscribe(new Observer<VkNewsFeedResponseWrapper>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(VkNewsFeedResponseWrapper vkNewsFeedResponseWrapper) {
                if (vkNewsFeedResponseWrapper != null && vkNewsFeedResponseWrapper.getResponse() != null) {
                    mNextPostId = vkNewsFeedResponseWrapper.getResponse().getNextPostId();
                    fillAdapter(vkNewsFeedResponseWrapper.getResponse(), true);
                }
            }

            @Override
            public void onError(Throwable e) {
                mIsLoading = false;
                showProgress(false);
            }

            @Override
            public void onComplete() {
                mIsLoading = false;
                showProgress(false);
            }
        });
    }

    private void loadFeedFromPost(String postId) {
        mIsLoading = true;
        mAsyncManager
                .getFeedFrom(postId)
                .subscribe(new Observer<VkNewsFeedResponseWrapper>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VkNewsFeedResponseWrapper vkNewsFeedResponseWrapper) {
                        if (vkNewsFeedResponseWrapper != null && vkNewsFeedResponseWrapper.getResponse() != null) {
                            mNextPostId = vkNewsFeedResponseWrapper.getResponse().getNextPostId();
                            fillAdapter(vkNewsFeedResponseWrapper.getResponse(), false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIsLoading = false;
                    }

                    @Override
                    public void onComplete() {
                        mIsLoading = false;
                    }
                });
    }

    private void fillAdapter(VkNewsFeedResponse response, boolean refresh) {
        if (response.getPosts() == null) {
            return;
        }
        List<PostEntity> postsList = new ArrayList<>();
        for (VkFeedPost vkFeedPost : response.getPosts()) {
            PostEntity postEntity = new PostEntity();
            postEntity.setPostId(getPostId(vkFeedPost));
            postEntity.setAttachments(getPhotoAttachments(vkFeedPost.getAttachments()));
            postEntity.setLikes(vkFeedPost.getLikes());
            long dateMillis = (long) vkFeedPost.getDate() * 1000;
            String date = PostUtils.getFormattedDateTime(dateMillis);
            postEntity.setDate(date);
            postEntity.setContent(vkFeedPost.getText());
            postsList.add(postEntity);
            if (vkFeedPost.getSourceId() < 0) {
                VkGroupProfile groupProfile = (VkGroupProfile) findProfile(vkFeedPost.getSourceId() * (-1), response.getGroupProfiles());
                if (groupProfile != null) {
                    postEntity.setAvatarUrl(groupProfile.getAvatarUrl());
                    postEntity.setAuthor(groupProfile.getName());
                }
            } else {
                VkUserProfile userProfile = (VkUserProfile) findProfile(vkFeedPost.getSourceId(), response.getProfiles());
                if (userProfile != null) {
                    postEntity.setAvatarUrl(userProfile.getAvatarUrl());
                    postEntity.setAuthor(String.format(Locale.getDefault(), "%s %s", userProfile.getFirstName(), userProfile.getLastName()));
                }
            }
        }
        if (refresh) {
            mNewsAdapter.setData(postsList);
        } else {
            mNewsAdapter.insertData(postsList);
        }
    }

    private VkProfile findProfile(int id, List<? extends VkProfile> profiles) {
        for (VkProfile profile : profiles) {
            if (profile.getId() == id) {
                return profile;
            }
        }
        return null;
    }


    private String getPostId(VkFeedPost feedPost) {
        return String.format("%s_%s", feedPost.getSourceId(), feedPost.getPostId());
    }


    private void showProgress(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }
}
