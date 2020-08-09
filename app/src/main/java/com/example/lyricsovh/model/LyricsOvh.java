package com.example.lyricsovh.model;

import com.google.gson.annotations.SerializedName;

public class LyricsOvh {

    @SerializedName("lyrics")
    private String lyrics;

    public LyricsOvh(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
