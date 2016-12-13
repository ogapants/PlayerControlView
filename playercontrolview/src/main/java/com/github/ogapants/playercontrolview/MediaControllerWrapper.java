package com.github.ogapants.playercontrolview;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

public class MediaControllerWrapper extends MediaController {

    private final PlayerControlView playerControlView;

    public MediaControllerWrapper(Context context) {
        this(new PlayerControlView(context));
    }

    public MediaControllerWrapper(PlayerControlView playerControlView) {
        super(playerControlView.getContext());
        this.playerControlView = playerControlView;
    }

    public PlayerControlView getPlayerControlView() {
        return playerControlView;
    }

    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        playerControlView.setPlayer(player);
    }

    @Override
    public void setAnchorView(View view) {
        ViewGroup parentView;
        View rootView = view;
        while (true) {
            if (rootView instanceof ViewGroup) {
                parentView = (ViewGroup) view;
                break;
            }
            rootView = view.getRootView();
        }
        playerControlView.attach(parentView);
    }

    @Override
    public void show() {
        playerControlView.show();
    }

    @Override
    public void show(int timeout) {
        playerControlView.show(timeout);
    }

    @Override
    public boolean isShowing() {
        return playerControlView.isShowing();
    }

    @Override
    public void hide() {
        if (!playerControlView.alwaysShow) {
            playerControlView.hide();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return playerControlView.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        return playerControlView.onTrackballEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return playerControlView.dispatchKeyEvent(event);
    }

    @Override
    public void setEnabled(boolean enabled) {
        playerControlView.setEnabled(enabled);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return playerControlView.getAccessibilityClassName();
    }

    @Override
    public void setPrevNextListeners(View.OnClickListener next, View.OnClickListener prev) {
        playerControlView.setNextListener(next);
        playerControlView.setPrevListener(prev);
    }
}
