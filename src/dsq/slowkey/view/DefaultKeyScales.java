package dsq.slowkey.view;

import android.inputmethodservice.Keyboard;
import android.util.DisplayMetrics;
import android.view.Window;
import dsq.slowkey.keyboard.DynamicKeyboard;

import java.util.List;

public class DefaultKeyScales implements KeyScales {
    @Override
    public void scale(final Window window, final DynamicKeyboard keyboard, final double percent) {
        final int height = pixels(window, percent);
        final List<Keyboard.Key> keys = keyboard.getKeys();
        if (keys.size() > 0) {
            mutate(keys, height);
            final Keyboard.Key last = keys.get(keys.size() - 1);
            final int totalHeight = last.y + height + 1;
            keyboard.setDynamicHeight(totalHeight);
        }
    }

    private int pixels(final Window window, final double percent) {
        final DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int)(percent * metrics.heightPixels);
    }

    private void mutate(final List<Keyboard.Key> keys, final int height) {
        Keyboard.Key prev = null;
        int menuHeight = 0;
        int row = 0;
        for (Keyboard.Key key : keys) {
            if (prev != null && prev.x > key.x) row++;
            if (row > 0) {
                key.height = height;
//                key.codes = new int [ ] { 97 };
                key.y = menuHeight + (row - 1) * (height + 1);
            } else {
                menuHeight = Math.max(menuHeight, key.height);
            }


            prev = key;
        }
    }
}
