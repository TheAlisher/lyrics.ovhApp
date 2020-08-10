package com.example.lyricsovh.presentation_ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lyricsovh.App;
import com.example.lyricsovh.R;
import com.example.lyricsovh.data.remote.LyricsOvhAPIClient;
import com.example.lyricsovh.model.LyricsOvh;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private TextView textHelper;
    private TextInputLayout inputLayoutArtist;
    private EditText editArtist;
    private TextInputLayout inputLayoutTitle;
    private EditText editTitle;
    private Button buttonGetLyrics;
    private ProgressBar progressBarLoadingLyrics;
    private TextView textLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setGradientStatusBar(this);
        setContentView(R.layout.activity_main);

        initializationViews();
        if (App.preferences.isFirstLaunch()) {
            textHelper.setVisibility(View.VISIBLE);
        }
    }

    private void setGradientStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = getResources().getDrawable(R.drawable.background_gradient_red_sunset);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    private void initializationViews() {
        textHelper = findViewById(R.id.text_main_helper);
        inputLayoutArtist = findViewById(R.id.inputLayout_main_artist);
        editArtist = findViewById(R.id.edit_main_artist);
        inputLayoutTitle = findViewById(R.id.inputLayout_main_title);
        editTitle = findViewById(R.id.edit_main_title);
        buttonGetLyrics = findViewById(R.id.button_main_get_lyrics);
        progressBarLoadingLyrics = findViewById(R.id.progressBar_main_loading_lyrics);
        textLyrics = findViewById(R.id.text_main_lyrics);
    }

    public void mainGetLyricsClick(View view) {
        if (editArtist.getText().toString().isEmpty() || editTitle.getText().toString().isEmpty()) {
            if (editArtist.getText().toString().isEmpty()) {
                inputLayoutArtist.setError("Напишите имя исполнителя");
            }
            if (editTitle.getText().toString().isEmpty()) {
                inputLayoutTitle.setError("Напишите название песни");
            }
        } else {
            inputLayoutArtist.setErrorEnabled(false);
            inputLayoutTitle.setErrorEnabled(false);
            hideKeyboard();
            clearAllFocus();
            progressBarLoadingLyrics.setVisibility(View.VISIBLE);
            textLyrics.setText("");
            lyricsovhGetAction();
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager IMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            IMM.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void clearAllFocus() {
        editTitle.clearFocus();
        editArtist.clearFocus();
    }

    private void lyricsovhGetAction() {
        App.lyricsOvhRepository.getAction(
                editArtist.getText().toString(),
                editTitle.getText().toString(),
                new LyricsOvhAPIClient.LyricsOvhActionCallback() {
                    @Override
                    public void onSuccess(LyricsOvh result) {
                        lyricsovhGetActionOnSuccess(result);
                        Log.d("anime", result.toString());
                    }

                    @Override
                    public void onFailure(Exception exception) {

                        lyricsovhGetActionOnFailure(exception);
                        Log.e("anime", exception.getMessage());
                    }
                }
        );
    }

    private void lyricsovhGetActionOnSuccess(LyricsOvh result) {
        textHelperGONE();
        progressBarLoadingLyrics.setVisibility(View.INVISIBLE);
        textLyrics.setText(result.getLyrics());
    }

    private void textHelperGONE() {
        textHelper.setVisibility(View.GONE);
        App.preferences.setLaunched();
    }

    private void lyricsovhGetActionOnFailure(Exception exception) {
        progressBarLoadingLyrics.setVisibility(View.INVISIBLE);
        textLyrics.setText("Не найдено");
    }
}