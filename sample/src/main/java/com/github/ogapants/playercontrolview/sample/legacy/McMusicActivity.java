package com.github.ogapants.playercontrolview.sample.legacy;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;

import com.github.ogapants.playercontrolview.sample.R;


public class McMusicActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    private MediaPlayer mediaPlayer;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(findViewById(android.R.id.content));
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaController.show();
            }
        });
        findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaController.hide();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Uri parse = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test_cbr);
        mediaPlayer = MediaPlayer.create(this, parse);
        mediaController.setMediaPlayer(this);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        mediaController.show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        mediaController.hide();
        super.onStop();
    }

    @Override
    public void start() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    public int getDuration() {
        return mediaPlayer != null ? mediaPlayer.getDuration() : 0;
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer != null ? mediaPlayer.getCurrentPosition() : 0;
    }

    @Override
    public void seekTo(int pos) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(pos);
        }
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}