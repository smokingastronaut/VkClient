package ru.khannanovayrat.vkclient.network.wall.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 06.11.2017 }.
 */

public class VkWallPostResponseWrapper {

    @SerializedName("response")
    private VkWallPostResponse mResponse;

    public VkWallPostResponse getResponse() {
        return mResponse;
    }

    public void setResponse(VkWallPostResponse mResponse) {
        this.mResponse = mResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkWallPostResponseWrapper that = (VkWallPostResponseWrapper) o;

        return mResponse != null ? mResponse.equals(that.mResponse) : that.mResponse == null;
    }

    @Override
    public int hashCode() {
        return mResponse != null ? mResponse.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VkWallPostResponseWrapper{" +
                "mResponse=" + mResponse +
                '}';
    }
}
