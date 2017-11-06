package ru.khannanovayrat.vkclient.network.feed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Khannanov Ayrat { 06.11.2017 }.
 */

public class VkNewsFeedResponse {

    @SerializedName("items")
    private List<VkFeedPost> mPosts;
    @SerializedName("next_from")
    private String mNextPostId;

    public List<VkFeedPost> getPosts() {
        return mPosts;
    }

    public void setPosts(List<VkFeedPost> posts) {
        mPosts = posts;
    }

    public String getNextPostId() {
        return mNextPostId;
    }

    public void setNextPostId(String nextPostId) {
        mNextPostId = nextPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkNewsFeedResponse that = (VkNewsFeedResponse) o;

        if (mPosts != null ? !mPosts.equals(that.mPosts) : that.mPosts != null) return false;
        return mNextPostId != null ? mNextPostId.equals(that.mNextPostId) : that.mNextPostId == null;
    }

    @Override
    public int hashCode() {
        int result = mPosts != null ? mPosts.hashCode() : 0;
        result = 31 * result + (mNextPostId != null ? mNextPostId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkNewsFeedResponse{" +
                "mPosts=" + mPosts +
                ", mNextPostId=" + mNextPostId +
                '}';
    }
}
