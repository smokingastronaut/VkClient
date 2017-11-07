package ru.khannanovayrat.vkclient.newsletter;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class PostEntity {

    private String mAuthor;
    private String mDate;
    private String mAvatarUrl;
    private String mContent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (mAuthor != null ? !mAuthor.equals(that.mAuthor) : that.mAuthor != null) return false;
        if (mDate != null ? !mDate.equals(that.mDate) : that.mDate != null) return false;
        if (mAvatarUrl != null ? !mAvatarUrl.equals(that.mAvatarUrl) : that.mAvatarUrl != null)
            return false;
        return mContent != null ? mContent.equals(that.mContent) : that.mContent == null;
    }

    @Override
    public int hashCode() {
        int result = mAuthor != null ? mAuthor.hashCode() : 0;
        result = 31 * result + (mDate != null ? mDate.hashCode() : 0);
        result = 31 * result + (mAvatarUrl != null ? mAvatarUrl.hashCode() : 0);
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "mAuthor='" + mAuthor + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mAvatarUrl='" + mAvatarUrl + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
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


}
