package com.github.ogapants.playercontrolview.sample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.github.ogapants.playercontrolview.PlayerControlView;
import com.google.android.exoplayer.AspectRatioFrameLayout;
import com.google.android.exoplayer.util.PlayerControl;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ExoPlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String URL = "http://html5demos.com/assets/dizzy.mp4";

    private PlayerControlView playerControlView;

    private SurfaceView surfaceView;
    private AspectRatioFrameLayout videoFrame;
    private SampleExoPlayer sampleExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            Toast.makeText(ExoPlayerActivity.this, "ExoPlayer does not support ICS", Toast.LENGTH_SHORT).show();
            finish();
        }

        videoFrame = (AspectRatioFrameLayout) findViewById(R.id.video_frame);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(this);

        playerControlView = new PlayerControlView(this);
        playerControlView.attach(this);
        sampleExoPlayer = new SampleExoPlayer();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (playerControlView.isShowing()) {
                playerControlView.hide();
            } else {
                playerControlView.show();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sampleExoPlayer.start(this, URL, surfaceView.getHolder().getSurface(), new SampleExoPlayer.VideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(int width, int height, float pixelWidthHeightRatio) {
                videoFrame.setAspectRatio(height == 0 ? 1 : (width * pixelWidthHeightRatio) / height);
            }
        });
        playerControlView.setPlayer(new PlayerControl(sampleExoPlayer.getExoPlayer()));
    }

    @Override
    protected void onStop() {
        sampleExoPlayer.stop();
        super.onStop();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        sampleExoPlayer.surfaceCreated();
        playerControlView.show();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //do nothing
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        sampleExoPlayer.surfaceDestroyed();
    }
}
