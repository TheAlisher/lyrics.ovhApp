package com.example.lyricsovh.data;

import com.example.lyricsovh.data.remote.LyricsOvhAPIClient;
import com.example.lyricsovh.model.LyricsOvh;

public class LyricsOvhRepository {

    private LyricsOvhAPIClient lyricsOvhAPIClient;

    public LyricsOvhRepository(LyricsOvhAPIClient lyricsOvhAPIClient) {
        this.lyricsOvhAPIClient = lyricsOvhAPIClient;
    }

    public void getAction(
            String artist,
            String title,
            final LyricsOvhAPIClient.LyricsOvhActionCallback callback
    ) {
        lyricsOvhAPIClient.getAction(
                artist,
                title,
                new LyricsOvhAPIClient.LyricsOvhActionCallback() {
                    @Override
                    public void onSuccess(LyricsOvh result) {
                        callback.onSuccess(result);
                    }

                    @Override
                    public void onFailure(Exception exception) {
                        callback.onFailure(exception);
                    }
                });
    }
}
