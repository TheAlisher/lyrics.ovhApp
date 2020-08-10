package com.example.lyricsovh.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private static final String PREF_IS_FIRST = "is_first";

    private SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(
                "app_preferences",
                Context.MODE_PRIVATE
        );
    }

    public boolean isFirstLaunch() {
        return preferences.getBoolean(PREF_IS_FIRST, true);
    }

    public void setLaunched() {
        preferences.edit().putBoolean(PREF_IS_FIRST, false).apply();
    }
}
