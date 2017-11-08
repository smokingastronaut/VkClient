package ru.khannanovayrat.vkclient.network.attachment;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public enum Attachment {

    @SerializedName("photo")
    PHOTO,
    @SerializedName("audio")
    AUDIO,
    @SerializedName("video")
    VIDEO,
    @SerializedName("doc")
    DOC,
    @SerializedName("link")
    LINK,
    @SerializedName("poll")
    POLL
}
