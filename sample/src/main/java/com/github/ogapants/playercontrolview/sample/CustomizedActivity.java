package com.github.ogapants.playercontrolview.sample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.ogapants.playercontrolview.PlayerControlView;

public class CustomizedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_video_view);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.demovideo);

        final PlayerControlView playerControlView = (PlayerControlView) findViewById(R.id.player_control_view);
//        playerControlView.setFastForwardMs(2_000);
//        playerControlView.setFastRewindMs(1_000);
//        playerControlView.setShowTimeoutMs(10_000);
//        playerControlView.setAlwaysShow(true);
        playerControlView.setOnVisibilityChangedListener(new PlayerControlView.OnVisibilityChangedListener() {
            @Override
            public void onShown(PlayerControlView view) {
                getSupportActionBar().show();
            }

            @Override
            public void onHidden(PlayerControlView view) {
                getSupportActionBar().hide();
            }
        });

        PlayerControlView.ViewHolder viewHolder = playerControlView.getViewHolder();
        viewHolder.pausePlayButton.setPauseDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pause_circle_filled_white_36dp_vector));
        viewHolder.pausePlayButton.setPlayDrawable(ContextCompat.getDrawable(this, R.drawable.ic_play_circle_filled_white_36dp_vector));
        viewHolder.controlsBackground.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        viewHolder.currentTimeText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        viewHolder.totalTimeText.setTextSize(18);
        playerControlView.setNextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomizedActivity.this, "onClick Next", Toast.LENGTH_SHORT).show();
            }
        });
        playerControlView.setPrevListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomizedActivity.this, "onClick Prev", Toast.LENGTH_SHORT).show();
            }
        });

        videoView.setMediaController(playerControlView.getMediaControllerWrapper());
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                playerControlView.show();
            }
        });
    }
}
