package ru.khannanovayrat.vkclient.newsletter;

import java.util.List;

import ru.khannanovayrat.vkclient.network.VkLikes;
import ru.khannanovayrat.vkclient.network.attachment.VkPhotoAttachment;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class PostEntity {

    private String mAuthor;
    private String mDate;
    private String mAvatarUrl;
    private String mContent;
    private List<VkPhotoAttachment> mVkAttachments;
    private VkLikes mLikes;
    private String mPostId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (mAuthor != null ? !mAuthor.equals(that.mAuthor) : that.mAuthor != null) return false;
        if (mDate != null ? !mDate.equals(that.mDate) : that.mDate != null) return false;
        if (mAvatarUrl != null ? !mAvatarUrl.equals(that.mAvatarUrl) : that.mAvatarUrl != null)
            return false;
        if (mContent != null ? !mContent.equals(that.mContent) : that.mContent != null)
            return false;
        if (mVkAttachments != null ? !mVkAttachments.equals(that.mVkAttachments) : that.mVkAttachments != null)
            return false;
        if (mLikes != null ? !mLikes.equals(that.mLikes) : that.mLikes != null) return false;
        return mPostId != null ? mPostId.equals(that.mPostId) : that.mPostId == null;
    }

    @Override
    public int hashCode() {
        int result = mAuthor != null ? mAuthor.hashCode() : 0;
        result = 31 * result + (mDate != null ? mDate.hashCode() : 0);
        result = 31 * result + (mAvatarUrl != null ? mAvatarUrl.hashCode() : 0);
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        result = 31 * result + (mVkAttachments != null ? mVkAttachments.hashCode() : 0);
        result = 31 * result + (mLikes != null ? mLikes.hashCode() : 0);
        result = 31 * result + (mPostId != null ? mPostId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "mAuthor='" + mAuthor + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mAvatarUrl='" + mAvatarUrl + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mVkAttachments=" + mVkAttachments +
                ", mLikes=" + mLikes +
                ", mPostId='" + mPostId + '\'' +
                '}';
    }

    public VkLikes getLikes() {
        return mLikes;
    }

    public void setLikes(VkLikes likes) {
        mLikes = likes;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public List<VkPhotoAttachment> getAttachments() {
        return mVkAttachments;
    }

    public void setAttachments(List<VkPhotoAttachment> attachments) {
        mVkAttachments = attachments;
    }

    public String getPostId() {
        return mPostId;
    }

    public void setPostId(String postId) {
        mPostId = postId;
    }
}
