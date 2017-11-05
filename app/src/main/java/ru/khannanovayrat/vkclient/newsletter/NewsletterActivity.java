package ru.khannanovayrat.vkclient.newsletter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vk.sdk.VKSdk;

import ru.khannanovayrat.vkclient.R;
import ru.khannanovayrat.vkclient.auth.LoginActivity;
import ru.khannanovayrat.vkclient.util.PreferenceUtils;

public class NewsletterActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, NewsletterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLoginState();
        setContentView(R.layout.activity_newsletter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newsletter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                VKSdk.logout();
                PreferenceUtils.clearAccessToken(this);
                redirectToLogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void checkLoginState() {
        if (!VKSdk.isLoggedIn()) {
            redirectToLogin();
        }
    }

    private void redirectToLogin() {
        startActivity(LoginActivity.newIntent(this));
        finish();
    }
}
