package ru.khannanovayrat.vkclient.network;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 08.11.2017 }.
 */

public class VkLikes {

    @SerializedName("count")
    private int mCount;
    @SerializedName("user_likes")
    private int mUserLikes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkLikes vkLikes = (VkLikes) o;

        if (mCount != vkLikes.mCount) return false;
        return mUserLikes == vkLikes.mUserLikes;
    }

    @Override
    public int hashCode() {
        int result = mCount;
        result = 31 * result + mUserLikes;
        return result;
    }

    @Override
    public String toString() {
        return "VkLikes{" +
                "mCount=" + mCount +
                ", mUserLikes=" + mUserLikes +
                '}';
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public boolean isUserLikes() {
        return mUserLikes > 0;
    }

    public void setUserLikes(int userLikes) {
        mUserLikes = userLikes;
    }
}
