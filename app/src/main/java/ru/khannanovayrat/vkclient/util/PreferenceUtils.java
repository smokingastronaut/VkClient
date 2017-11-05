package ru.khannanovayrat.vkclient.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Khannanov Ayrat { 05.11.2017 }.
 */

public final class PreferenceUtils {

    private final static String SESSION_PREF = "session_preferences";

    private final static String TOKEN = "token";

    public static String getAccessToken(Context context) {
        return getTokenPreferences(context).getString(TOKEN, null);
    }

    public static void saveAccessToken(String token, Context context) {
        getTokenPreferences(context)
                .edit()
                .putString(TOKEN, token)
                .apply();
    }

    public static void clearAccessToken(Context context) {
        getTokenPreferences(context)
                .edit()
                .clear()
                .apply();
    }

    private static SharedPreferences getTokenPreferences(Context context) {
        return context.getSharedPreferences(SESSION_PREF, Context.MODE_PRIVATE);
    }
}
