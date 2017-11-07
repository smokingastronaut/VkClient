package ru.khannanovayrat.vkclient.network;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public class VkGroupProfile extends VkProfile {

    @SerializedName("name")
    private String mName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkGroupProfile that = (VkGroupProfile) o;

        return mName != null ? mName.equals(that.mName) : that.mName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkGroupProfile{" +
                "mName='" + mName + '\'' +
                '}';
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
