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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.khannanovayrat.vkclient.R;
import ru.khannanovayrat.vkclient.auth.LoginActivity;
import ru.khannanovayrat.vkclient.network.ApiAsyncManager;
import ru.khannanovayrat.vkclient.network.feed.VkFeedPost;
import ru.khannanovayrat.vkclient.network.feed.VkNewsFeedResponseWrapper;
import ru.khannanovayrat.vkclient.util.PreferenceUtils;

public class NewsletterActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

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
        mNewsAdapter = new NewsletterAdapter();
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

    private void checkLoginState() {
        if (!VKSdk.isLoggedIn()) {
            redirectToLogin();
        }
    }

    private void redirectToLogin() {
        startActivity(LoginActivity.newIntent(this));
        finish();
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
                    fillAdapter(vkNewsFeedResponseWrapper.getResponse().getPosts(), true);
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
                            fillAdapter(vkNewsFeedResponseWrapper.getResponse().getPosts(), false);
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

    private void fillAdapter(List<VkFeedPost> posts, boolean refresh) {
        if (posts == null) {
            return;
        }
        List<PostEntity> postsList = new ArrayList<>();
        for (VkFeedPost vkWallPost : posts) {
            PostEntity postEntity = new PostEntity();
            String date = getFormattedDateTime(vkWallPost.getDate() * 1000);
            postEntity.setDate(date);
            postEntity.setContent(vkWallPost.getText());
            postsList.add(postEntity);
        }
        if (refresh) {
            mNewsAdapter.setData(postsList);
        } else {
            mNewsAdapter.insertData(postsList);
        }
    }

    private String getFormattedDateTime(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yy HH:mm", Locale.getDefault());
        Date date = new Date(millis);
        return sdf.format(date);
    }

    private void showProgress(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }
}
