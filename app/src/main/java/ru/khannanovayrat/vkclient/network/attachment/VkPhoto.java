package ru.khannanovayrat.vkclient.network.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public class VkPhoto {

    @SerializedName("photo_75")
    private String mPhoto75;
    @SerializedName("photo_1280")
    private String mPhoto1280;
    @SerializedName("photo_807")
    private String mPhoto807;
    @SerializedName("photo_604")
    private String mPhoto604;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkPhoto vkPhoto = (VkPhoto) o;

        if (mPhoto75 != null ? !mPhoto75.equals(vkPhoto.mPhoto75) : vkPhoto.mPhoto75 != null)
            return false;
        if (mPhoto1280 != null ? !mPhoto1280.equals(vkPhoto.mPhoto1280) : vkPhoto.mPhoto1280 != null)
            return false;
        if (mPhoto807 != null ? !mPhoto807.equals(vkPhoto.mPhoto807) : vkPhoto.mPhoto807 != null)
            return false;
        return mPhoto604 != null ? mPhoto604.equals(vkPhoto.mPhoto604) : vkPhoto.mPhoto604 == null;
    }

    @Override
    public int hashCode() {
        int result = mPhoto75 != null ? mPhoto75.hashCode() : 0;
        result = 31 * result + (mPhoto1280 != null ? mPhoto1280.hashCode() : 0);
        result = 31 * result + (mPhoto807 != null ? mPhoto807.hashCode() : 0);
        result = 31 * result + (mPhoto604 != null ? mPhoto604.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkPhoto{" +
                "mPhoto75='" + mPhoto75 + '\'' +
                ", mPhoto1280='" + mPhoto1280 + '\'' +
                ", mPhoto807='" + mPhoto807 + '\'' +
                ", mPhoto604l='" + mPhoto604 + '\'' +
                '}';
    }

    public String getPhoto75() {
        return mPhoto75;
    }

    public void setPhoto75(String photo75) {
        mPhoto75 = photo75;
    }

    public String getPhoto1280() {
        return mPhoto1280;
    }

    public void setPhoto1280(String photo1280) {
        mPhoto1280 = photo1280;
    }

    public String getPhoto807() {
        return mPhoto807;
    }

    public void setPhoto807(String photo807) {
        mPhoto807 = photo807;
    }

    public String getPhoto604() {
        return mPhoto604;
    }

    public void setPhoto604(String photo604) {
        mPhoto604 = photo604;
    }
}
