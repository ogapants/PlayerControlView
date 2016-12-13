package com.github.ogapants.playercontrolview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

public class PausePlayButton extends AppCompatImageButton {

    private Drawable playDrawable;
    private Drawable pauseDrawable;

    public PausePlayButton(Context context) {
        this(context, null);
    }

    public PausePlayButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.imageButtonStyle);
    }

    public PausePlayButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            setImageResource(R.drawable.ic_play_arrow_white_36dp);
            return;
        }
        pauseDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_pause_white_36dp);
        playDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_play_arrow_white_36dp);
        toggleImage(false);
    }

    public Drawable getPlayDrawable() {
        return playDrawable;
    }

    public void setPlayDrawable(Drawable playDrawable) {
        this.playDrawable = playDrawable;
    }

    public Drawable getPauseDrawable() {
        return pauseDrawable;
    }

    public void setPauseDrawable(Drawable pauseDrawable) {
        this.pauseDrawable = pauseDrawable;
    }

    public void toggleImage(boolean isPlaying) {
        if (isPlaying) {
            setImageDrawable(pauseDrawable);
        } else {
            setImageDrawable(playDrawable);
        }
    }
}
