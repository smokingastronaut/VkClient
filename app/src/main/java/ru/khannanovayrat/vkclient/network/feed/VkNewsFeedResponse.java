package ru.khannanovayrat.vkclient.network.feed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.khannanovayrat.vkclient.network.VkGroupProfile;
import ru.khannanovayrat.vkclient.network.VkUserProfile;

/**
 * @author Khannanov Ayrat { 06.11.2017 }.
 */

public class VkNewsFeedResponse {

    @SerializedName("items")
    private List<VkFeedPost> mPosts;
    @SerializedName("profiles")
    private List<VkUserProfile> mProfiles;
    @SerializedName("groups")
    private List<VkGroupProfile> mGroupProfiles;
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

    public List<VkUserProfile> getProfiles() {
        return mProfiles;
    }

    public void setProfiles(List<VkUserProfile> profiles) {
        mProfiles = profiles;
    }

    public List<VkGroupProfile> getGroupProfiles() {
        return mGroupProfiles;
    }

    public void setGroupProfiles(List<VkGroupProfile> groupProfiles) {
        mGroupProfiles = groupProfiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkNewsFeedResponse that = (VkNewsFeedResponse) o;

        if (mPosts != null ? !mPosts.equals(that.mPosts) : that.mPosts != null) return false;
        if (mProfiles != null ? !mProfiles.equals(that.mProfiles) : that.mProfiles != null)
            return false;
        if (mGroupProfiles != null ? !mGroupProfiles.equals(that.mGroupProfiles) : that.mGroupProfiles != null)
            return false;
        return mNextPostId != null ? mNextPostId.equals(that.mNextPostId) : that.mNextPostId == null;
    }

    @Override
    public int hashCode() {
        int result = mPosts != null ? mPosts.hashCode() : 0;
        result = 31 * result + (mProfiles != null ? mProfiles.hashCode() : 0);
        result = 31 * result + (mGroupProfiles != null ? mGroupProfiles.hashCode() : 0);
        result = 31 * result + (mNextPostId != null ? mNextPostId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkNewsFeedResponse{" +
                "mPosts=" + mPosts +
                ", mProfiles=" + mProfiles +
                ", mGroupProfiles=" + mGroupProfiles +
                ", mNextPostId='" + mNextPostId + '\'' +
                '}';
    }
}
