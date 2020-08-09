package com.example.lyricsovh.data.remote;

import android.util.Log;

import com.example.lyricsovh.core.CoreCallback;
import com.example.lyricsovh.model.LyricsOvh;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class LyricsOvhAPIClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.lyrics.ovh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    LyricsOvhAPI client = retrofit.create(LyricsOvhAPI.class);

    public void getAction(
            String artist,
            String title,
            final LyricsOvhActionCallback callback
    ) {
         final Call<LyricsOvh> call = client.getAction(
                artist,
                title
        );

        Log.d("anime", call.request().url().toString());

        call.enqueue(new CoreCallback<LyricsOvh>() {
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

    public interface LyricsOvhActionCallback extends BaseCallback<LyricsOvh> {
    }

    public interface BaseCallback<T> {
        void onSuccess(T result);
        void onFailure(Exception exception);
    }

    private interface LyricsOvhAPI {
        @GET("v1/{artist}/{title}")
        Call<LyricsOvh> getAction(
                @Path("artist") String artist,
                @Path("title") String title
        );
    }
}
