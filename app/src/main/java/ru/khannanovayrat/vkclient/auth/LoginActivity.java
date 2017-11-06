package ru.khannanovayrat.vkclient.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import ru.khannanovayrat.vkclient.R;
import ru.khannanovayrat.vkclient.newsletter.NewsletterActivity;
import ru.khannanovayrat.vkclient.util.PreferenceUtils;

public class LoginActivity extends AppCompatActivity implements VKCallback<VKAccessToken> {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, this)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onResult(VKAccessToken res) {
        saveAccessToken(res.accessToken);
        redirectToNewsLetter();
    }

    @Override
    public void onError(VKError error) {
        showErrorMessage();
    }

    private void login() {
        VKSdk.login(this,
                VKScope.WALL,
                VKScope.OFFLINE,
                VKScope.FRIENDS);
    }

    private void saveAccessToken(String token) {
        PreferenceUtils.saveAccessToken(token, this);
    }

    private void redirectToNewsLetter() {
        startActivity(NewsletterActivity.newIntent(this));
        finish();
    }

    private void showErrorMessage() {
        Snackbar.make(getWindow().getDecorView(), R.string.error_login, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.action_retry, v -> login())
                .show();
    }
}
