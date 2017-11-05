package ru.khannanovayrat.vkclient;


import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class VkClientApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
