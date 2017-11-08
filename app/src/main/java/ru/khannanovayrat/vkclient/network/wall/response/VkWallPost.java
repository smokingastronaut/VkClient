package ru.khannanovayrat.vkclient.network.wall.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.khannanovayrat.vkclient.network.VkLikes;
import ru.khannanovayrat.vkclient.network.attachment.VkAttachment;

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
    @SerializedName("attachments")
    private List<VkAttachment> mAttachments;
    @SerializedName("likes")
    private VkLikes mLikes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkWallPost that = (VkWallPost) o;

        if (mFromId != that.mFromId) return false;
        if (mDate != that.mDate) return false;
        if (mText != null ? !mText.equals(that.mText) : that.mText != null) return false;
        if (mAttachments != null ? !mAttachments.equals(that.mAttachments) : that.mAttachments != null)
            return false;
        return mLikes != null ? mLikes.equals(that.mLikes) : that.mLikes == null;
    }

    @Override
    public int hashCode() {
        int result = mFromId;
        result = 31 * result + mDate;
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        result = 31 * result + (mAttachments != null ? mAttachments.hashCode() : 0);
        result = 31 * result + (mLikes != null ? mLikes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkWallPost{" +
                "mFromId=" + mFromId +
                ", mDate=" + mDate +
                ", mText='" + mText + '\'' +
                ", mAttachments=" + mAttachments +
                ", mLikes=" + mLikes +
                '}';
    }

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

    public List<VkAttachment> getAttachments() {
        return mAttachments;
    }

    public void setAttachments(List<VkAttachment> attachments) {
        mAttachments = attachments;
    }

    public VkLikes getLikes() {
        return mLikes;
    }

    public void setLikes(VkLikes likes) {
        mLikes = likes;
    }
}
