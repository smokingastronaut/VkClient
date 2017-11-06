package ru.khannanovayrat.vkclient.network.wall.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */
public class VkWallPostResponse {

    @SerializedName("")
    private int mCount;
    @SerializedName("items")
    private List<VkWallPost> mPosts;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public List<VkWallPost> getPosts() {
        return mPosts;
    }

    public void setPosts(List<VkWallPost> posts) {
        mPosts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkWallPostResponse that = (VkWallPostResponse) o;

        if (mCount != that.mCount) return false;
        return mPosts != null ? mPosts.equals(that.mPosts) : that.mPosts == null;
    }

    @Override
    public int hashCode() {
        int result = mCount;
        result = 31 * result + (mPosts != null ? mPosts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkWallPostResponse{" +
                "mCount=" + mCount +
                ", mPosts=" + mPosts +
                '}';
    }
}
