package ru.khannanovayrat.vkclient.network.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public class VkPhotoAttachment extends VkAttachment {

    @SerializedName("photo")
    private VkPhoto mPhoto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkPhotoAttachment that = (VkPhotoAttachment) o;

        return mPhoto != null ? mPhoto.equals(that.mPhoto) : that.mPhoto == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mPhoto != null ? mPhoto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkPhotoAttachment{" +
                "mPhoto='" + mPhoto + '\'' +
                '}';
    }

    public VkPhoto getPhoto() {
        return mPhoto;
    }

    public void setPhoto(VkPhoto photo) {
        mPhoto = photo;
    }
}
