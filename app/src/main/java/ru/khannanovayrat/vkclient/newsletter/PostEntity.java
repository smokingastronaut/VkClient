package ru.khannanovayrat.vkclient.newsletter;

import android.graphics.drawable.Drawable;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class PostEntity {

    private String mAuthor;
    private String mDate;
    private Drawable mAvatar;
    private String mContent;

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public Drawable getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Drawable mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }
}
