package ru.khannanovayrat.vkclient.network;

import com.google.gson.annotations.SerializedName;

/**
 * @author Khannanov Ayrat { 07.11.2017 }.
 */

public class VkUserProfile extends VkProfile {

    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("last_name")
    private String mLastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VkUserProfile that = (VkUserProfile) o;

        if (mFirstName != null ? !mFirstName.equals(that.mFirstName) : that.mFirstName != null)
            return false;
        return mLastName != null ? mLastName.equals(that.mLastName) : that.mLastName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mFirstName != null ? mFirstName.hashCode() : 0);
        result = 31 * result + (mLastName != null ? mLastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VkUserProfile{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }
}
