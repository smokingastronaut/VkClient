package ru.khannanovayrat.vkclient.network.feed;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 06.11.2017 }.
 */

public class VkFeedPost {

    @SerializedName("date")
    private int mDate;
    @SerializedName("source_id")
    private int mSourceId;
    @SerializedName("text")
    private String mText;

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        mDate = date;
    }

    public int getSourceId() {
        return mSourceId;
    }

    public void setSourceId(int sourceId) {
        mSourceId = sourceId;
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

        VkFeedPost that = (VkFeedPost) o;

        if (mDate != that.mDate) return false;
        if (mSourceId != that.mSourceId) return false;
        return mText != null ? mText.equals(that.mText) : that.mText == null;
    }

    @Override
    public int hashCode() {
        int result = mDate;
        result = 31 * result + mSourceId;
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkFeedPost{" +
                "mDate=" + mDate +
                ", mSourceId=" + mSourceId +
                ", mText='" + mText + '\'' +
                '}';
    }
}
