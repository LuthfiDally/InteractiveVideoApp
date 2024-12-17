package com.example.interactivevideoapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button startButton, backButton, nextButton, stopButton, continueButton;
    private ImageView backgroundImage;
    private int currentVideoIndex = 0;
    private int[] videoResIds = {R.raw.video1, R.raw.video2, R.raw.video3, R.raw.video4, R.raw.video5, R.raw.video6, R.raw.video7, R.raw.video8 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        videoView = findViewById(R.id.videoView);
        startButton = findViewById(R.id.startButton);
        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);
        stopButton = findViewById(R.id.stopButton);
        continueButton = findViewById(R.id.continueButton);
        backgroundImage = findViewById(R.id.backgroundImage);

        // Set up button click listeners
        startButton.setOnClickListener(v -> startVideo());
        backButton.setOnClickListener(v -> playPreviousVideo());
        nextButton.setOnClickListener(v -> playNextVideo());
        stopButton.setOnClickListener(v -> stopVideo());
        continueButton.setOnClickListener(v -> continueVideo());
    }

    private void startVideo() {
        // Hide background image and start button
        startButton.setVisibility(View.GONE);
        backgroundImage.setVisibility(View.GONE);

        // Show video view and controls
        videoView.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.VISIBLE);
        continueButton.setVisibility(View.GONE); // Initially hide Continue button
        playVideo(currentVideoIndex);
    }

    private void playVideo(int videoIndex) {
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResIds[videoIndex]);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    private void playNextVideo() {
        if (currentVideoIndex < videoResIds.length - 1) {
            currentVideoIndex++;
            playVideo(currentVideoIndex);
        }
    }

    private void playPreviousVideo() {
        if (currentVideoIndex > 0) {
            currentVideoIndex--;
            playVideo(currentVideoIndex);
        }
    }

    private void stopVideo() {
        if (videoView.isPlaying()) {
            videoView.pause(); // Pause the video
            stopButton.setVisibility(View.GONE); // Hide Stop button
            continueButton.setVisibility(View.VISIBLE); // Show Continue button
        }
    }

    private void continueVideo() {
        videoView.start(); // Resume the video
        continueButton.setVisibility(View.GONE); // Hide Continue button
        stopButton.setVisibility(View.VISIBLE); // Show Stop button
    }
}
