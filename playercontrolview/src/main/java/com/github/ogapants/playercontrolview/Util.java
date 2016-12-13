package com.github.ogapants.playercontrolview;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;

class Util {

    static String formatTime(final int timeMs) {
        if (timeMs < 1000) {
            return "00:00";
        }
        String result = "";
        final int totalSec = timeMs / 1000;
        final int hours = totalSec / 3600;
        final int minutes = (totalSec / 60) % 60;
        final int seconds = totalSec % 60;

        if (hours > 0) {
            result += hours + ":";
        }
        if (minutes >= 10) {
            result += minutes + ":";
        } else {
            result += "0" + minutes + ":";
        }
        if (seconds >= 10) {
            result += seconds;
        } else {
            result += "0" + seconds;
        }
        return result;
    }

    static StateListDrawable createStateListDrawable(Drawable drawable, @ColorInt int drawableColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();

        int[] defaultStateSet = {-android.R.attr.state_pressed, -android.R.attr.state_focused, android.R.attr.state_enabled};
        stateListDrawable.addState(defaultStateSet, drawable);

        int[] focusedStateSet = {-android.R.attr.state_pressed, android.R.attr.state_focused};
        Drawable focusedDrawable = darkenDrawable(drawable, drawableColor, 0.7f);
        stateListDrawable.addState(focusedStateSet, focusedDrawable);

        int[] pressedStateSet = {android.R.attr.state_pressed};
        Drawable pressedDrawable = darkenDrawable(drawable, drawableColor, 0.6f);
        stateListDrawable.addState(pressedStateSet, pressedDrawable);

        int[] disableStateSet = {-android.R.attr.state_enabled};
        Drawable disableDrawable = darkenDrawable(drawable, drawableColor, 0.4f);
        stateListDrawable.addState(disableStateSet, disableDrawable);

        return stateListDrawable;
    }

    private static Drawable darkenDrawable(Drawable drawable, @ColorInt int drawableColor, float factor) {
        int color = darkenColor(drawableColor, factor);
        Drawable d = drawable.getConstantState().newDrawable().mutate();
        d.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        return d;
    }

    @ColorInt
    private static int darkenColor(@ColorInt int color, float factor) {
        if (factor < 0 || factor > 1) {
            return color;
        }
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha,
                Math.max((int) (red * factor), 0),
                Math.max((int) (green * factor), 0),
                Math.max((int) (blue * factor), 0));
    }
}
