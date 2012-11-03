package dsq.slowkey;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

public class KeyboardListener implements KeyboardView.OnKeyboardActionListener {

    private final InputMethodService context;

    public KeyboardListener(final InputMethodService context) {
        this.context = context;
    }

    @Override
    public void onPress(final int i) {
    }

    @Override
    public void onRelease(final int i) {
    }

    @Override
    public void onKey(final int primaryCode, final int[] keyCodes) {
        final InputConnection conn = context.getCurrentInputConnection();
        conn.commitText(String.valueOf((char) primaryCode), 1);
    }

    @Override
    public void onText(final CharSequence charSequence) {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeUp() {
    }


    private void keyDownUp(int keyEventCode) {
        final InputConnection conn = context.getCurrentInputConnection();
        conn.sendKeyEvent(key(KeyEvent.ACTION_DOWN, keyEventCode));
        conn.sendKeyEvent(key(KeyEvent.ACTION_UP, keyEventCode));
    }

    private KeyEvent key(final int action, final int keyEventCode) {
        return new KeyEvent(action, keyEventCode);
    }

    /**
     * Reused from android SoftKeyboard.
     */
//    private void sendKey(int keyCode) {
//        if (keyCode >= '0' && keyCode <= '9') {
//            keyDownUp(keyCode - '0' + KeyEvent.KEYCODE_0);
//        } else {
//            final InputConnection conn = context.getCurrentInputConnection();
//            conn.commitText(String.valueOf((char) keyCode), 1);
//        }
//    }
}
