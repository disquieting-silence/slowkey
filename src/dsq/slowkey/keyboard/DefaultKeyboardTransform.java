package dsq.slowkey.keyboard;

import android.view.inputmethod.EditorInfo;

public class DefaultKeyboardTransform implements KeyboardTransform {
    private static final int KEYCODE_ENTER = 10;
    private static final int KEYCODE_DONE = -4;
    private final int flags;

    public DefaultKeyboardTransform(final int type) {
        this.flags = type & EditorInfo.TYPE_MASK_FLAGS;
    }

    public int transform(final int code) {
        final int multi = flags & EditorInfo.TYPE_TEXT_FLAG_IME_MULTI_LINE;
        return  multi == 0 && code == KEYCODE_ENTER ? KEYCODE_DONE : code;
    }
}