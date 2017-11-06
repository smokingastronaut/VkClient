package ru.khannanovayrat.vkclient.network.feed;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 06.11.2017 }.
 */

public class VkNewsFeedResponseWrapper {

    @SerializedName("response")
    private VkNewsFeedResponse mResponse;

    public VkNewsFeedResponse getResponse() {
        return mResponse;
    }

    public void setResponse(VkNewsFeedResponse response) {
        mResponse = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkNewsFeedResponseWrapper that = (VkNewsFeedResponseWrapper) o;

        return mResponse != null ? mResponse.equals(that.mResponse) : that.mResponse == null;
    }

    @Override
    public int hashCode() {
        return mResponse != null ? mResponse.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VkNewsFeedResponseWrapper{" +
                "mResponse=" + mResponse +
                '}';
    }
}
