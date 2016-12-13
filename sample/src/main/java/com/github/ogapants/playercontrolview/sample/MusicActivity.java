package com.github.ogapants.playercontrolview.sample;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.ogapants.playercontrolview.MediaPlayerControlImpl;
import com.github.ogapants.playercontrolview.PlayerControlView;

public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private PlayerControlView playerControlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        playerControlView = new PlayerControlView(this);
        playerControlView.attach(this);
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerControlView.show();
            }
        });
        findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerControlView.hide();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Uri parse = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test_cbr);
        mediaPlayer = MediaPlayer.create(this, parse);
        playerControlView.setPlayer(new MediaPlayerControlImpl(mediaPlayer));
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                playerControlView.show();
            }
        });
    }

    @Override
    protected void onStop() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        playerControlView.hide();
        super.onStop();
    }
}