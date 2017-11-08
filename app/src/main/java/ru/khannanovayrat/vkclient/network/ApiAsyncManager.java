package ru.khannanovayrat.vkclient.network;

import android.content.Context;

import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.khannanovayrat.vkclient.network.attachment.VkAttachment;
import ru.khannanovayrat.vkclient.network.attachment.VkAttachmentDeserializer;
import ru.khannanovayrat.vkclient.network.feed.VkNewsFeedResponseWrapper;
import ru.khannanovayrat.vkclient.network.wall.VkWallPostResponseDeserializer;
import ru.khannanovayrat.vkclient.network.wall.response.VkWallPostResponse;
import ru.khannanovayrat.vkclient.network.wall.response.VkWallPostResponseWrapper;
import ru.khannanovayrat.vkclient.util.PreferenceUtils;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class ApiAsyncManager {

    private static final String URL = "https://api.vk.com/method/";

    private VkApi mVkApi;
    private Context mContext;

    public ApiAsyncManager(Context context) {
        mContext = context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        GsonConverterFactory converterFactory = GsonConverterFactory.create(
                new GsonBuilder()
                        .registerTypeAdapter(VkWallPostResponse.class, new VkWallPostResponseDeserializer())
                        .registerTypeAdapter(VkAttachment.class, new VkAttachmentDeserializer())
                        .create());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        mVkApi = retrofit.create(VkApi.class);
    }

    public Observable<VkWallPostResponseWrapper> getWall() {
        return mVkApi
                .getWall(100, getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<VkNewsFeedResponseWrapper> getFeed() {
        return mVkApi
                .getNewsFeed(getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<VkNewsFeedResponseWrapper> getFeedFrom(String postId) {
        return mVkApi
                .getNewsFeedFrom(postId, getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<VkWallPostResponseWrapper> getPostsById(String... postsId) {
        return mVkApi
                .getPostsById(getToken(), postsId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<VkUserProfile> getUserById(String userId) {
        return mVkApi
                .getUserById(getToken(), userId, "photo_100")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    private String getToken() {
        return PreferenceUtils.getAccessToken(mContext);
    }
}
