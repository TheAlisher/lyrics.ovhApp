package com.example.lyricsovh;

import android.app.Application;

import com.example.lyricsovh.data.AppPreferences;
import com.example.lyricsovh.data.LyricsOvhRepository;
import com.example.lyricsovh.data.remote.LyricsOvhAPIClient;

public class App extends Application {

    //Preference
    public static AppPreferences preferences;
    //Repository
    public static LyricsOvhRepository lyricsOvhRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = new AppPreferences(this);
        LyricsOvhAPIClient lyricsOvhAPIClient = new LyricsOvhAPIClient();
        lyricsOvhRepository = new LyricsOvhRepository(lyricsOvhAPIClient);
    }
}
