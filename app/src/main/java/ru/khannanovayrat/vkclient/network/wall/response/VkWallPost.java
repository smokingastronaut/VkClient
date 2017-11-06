package ru.khannanovayrat.vkclient.network.wall.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 06.11.2017 }.
 */

public class VkWallPost {

    @SerializedName("from_id")
    private int mFromId;
    @SerializedName("date")
    private int mDate;
    @SerializedName("text")
    private String mText;

    public int getFromId() {
        return mFromId;
    }

    public void setFromId(int fromId) {
        mFromId = fromId;
    }

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        mDate = date;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkWallPost that = (VkWallPost) o;

        if (mFromId != that.mFromId) return false;
        if (mDate != that.mDate) return false;
        return mText != null ? mText.equals(that.mText) : that.mText == null;
    }

    @Override
    public int hashCode() {
        int result = mFromId;
        result = 31 * result + mDate;
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkWallPost{" +
                "mFromId=" + mFromId +
                ", mDate=" + mDate +
                ", mText='" + mText + '\'' +
                '}';
    }
}
