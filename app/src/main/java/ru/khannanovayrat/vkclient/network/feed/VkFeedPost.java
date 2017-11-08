package ru.khannanovayrat.vkclient.network.feed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.khannanovayrat.vkclient.network.VkLikes;
import ru.khannanovayrat.vkclient.network.attachment.VkAttachment;

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
    @SerializedName("attachments")
    private List<VkAttachment> mAttachments;
    @SerializedName("likes")
    private VkLikes mLikes;
    @SerializedName("post_id")
    private int mPostId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkFeedPost that = (VkFeedPost) o;

        if (mDate != that.mDate) return false;
        if (mSourceId != that.mSourceId) return false;
        if (mPostId != that.mPostId) return false;
        if (mText != null ? !mText.equals(that.mText) : that.mText != null) return false;
        if (mAttachments != null ? !mAttachments.equals(that.mAttachments) : that.mAttachments != null)
            return false;
        return mLikes != null ? mLikes.equals(that.mLikes) : that.mLikes == null;
    }

    @Override
    public int hashCode() {
        int result = mDate;
        result = 31 * result + mSourceId;
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        result = 31 * result + (mAttachments != null ? mAttachments.hashCode() : 0);
        result = 31 * result + (mLikes != null ? mLikes.hashCode() : 0);
        result = 31 * result + mPostId;
        return result;
    }

    @Override
    public String toString() {
        return "VkFeedPost{" +
                "mDate=" + mDate +
                ", mSourceId=" + mSourceId +
                ", mText='" + mText + '\'' +
                ", mAttachments=" + mAttachments +
                ", mLikes=" + mLikes +
                ", mPostId=" + mPostId +
                '}';
    }

    public VkLikes getLikes() {
        return mLikes;
    }

    public void setLikes(VkLikes likes) {
        mLikes = likes;
    }

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

    public List<VkAttachment> getAttachments() {
        return mAttachments;
    }

    public void setAttachments(List<VkAttachment> attachments) {
        mAttachments = attachments;
    }

    public int getPostId() {
        return mPostId;
    }

    public void setPostId(int postId) {
        mPostId = postId;
    }
}
