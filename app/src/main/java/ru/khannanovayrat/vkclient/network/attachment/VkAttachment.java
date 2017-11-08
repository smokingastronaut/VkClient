package ru.khannanovayrat.vkclient.network.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public class VkAttachment {

    @SerializedName("type")
    private Attachment mType;

    public Attachment getType() {
        return mType;
    }

    public void setType(Attachment type) {
        mType = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkAttachment that = (VkAttachment) o;

        return mType == that.mType;
    }

    @Override
    public int hashCode() {
        return mType != null ? mType.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VkAttachment{" +
                "mType=" + mType +
                '}';
    }
}
