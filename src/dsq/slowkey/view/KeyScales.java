package dsq.slowkey.view;

import android.view.Window;
import dsq.slowkey.keyboard.DynamicKeyboard;

public interface KeyScales {
    void scale(final Window window, final DynamicKeyboard keyboard, final double percent);
}
