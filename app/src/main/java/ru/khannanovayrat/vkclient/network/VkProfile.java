package ru.khannanovayrat.vkclient.network;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public class VkProfile {

    @SerializedName("id")
    private int mId;
    @SerializedName("photo_100")
    private String mAvatarUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkProfile vkProfile = (VkProfile) o;

        if (mId != vkProfile.mId) return false;
        return mAvatarUrl != null ? mAvatarUrl.equals(vkProfile.mAvatarUrl) : vkProfile.mAvatarUrl == null;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mAvatarUrl != null ? mAvatarUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkProfile{" +
                "mId=" + mId +
                ", mAvatarUrl='" + mAvatarUrl + '\'' +
                '}';
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }
}
