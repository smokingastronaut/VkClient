package ru.khannanovayrat.vkclient.network;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.khannanovayrat.vkclient.network.feed.VkNewsFeedResponseWrapper;
import ru.khannanovayrat.vkclient.network.wall.response.VkWallPostResponseWrapper;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public interface VkApi {

    @GET("wall.get?PARAMETERS")
    Observable<VkWallPostResponseWrapper> getWall(
            @Query("count") int count,
            @Query("access_token") String token);

    @GET("newsfeed.get?PARAMETERS&v=5.69")
    Observable<VkNewsFeedResponseWrapper> getNewsFeedFrom(@Query("start_from") String postId, @Query("access_token") String token);

    @GET("newsfeed.get?PARAMETERS&v=5.69")
    Observable<VkNewsFeedResponseWrapper> getNewsFeed(@Query("access_token") String token);

    @GET("wall.getById?PARAMETERS&v=5.69")
    Observable<VkWallPostResponseWrapper> getPostsById(@Query("access_token") String token, @Query("posts") String... postId);

    @GET("users.get?PARAMETERS&v=5.69")
    Observable<VkUserProfile> getUserById(@Query("access_token") String token,
                                          @Query("user_ids") String userId,
                                          @Query("fields") String... additionalFields);
}
