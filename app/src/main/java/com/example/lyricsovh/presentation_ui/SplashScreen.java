package com.example.lyricsovh.presentation_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lyricsovh.presentation_ui.main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.start(this);
    }
}