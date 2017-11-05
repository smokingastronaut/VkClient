package ru.khannanovayrat.vkclient;


import android.app.Application;
import android.support.annotation.Nullable;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import ru.khannanovayrat.vkclient.util.PreferenceUtils;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public class VkClientApplication extends Application {

    private VKAccessTokenTracker mTokenTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        initTokenTracker();
    }

    private void initTokenTracker() {
        mTokenTracker = new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
                if (newToken != null) {
                    PreferenceUtils.saveAccessToken(newToken.accessToken, VkClientApplication.this);
                }
            }
        };
    }
}
