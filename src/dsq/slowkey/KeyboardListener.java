package dsq.slowkey;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

public class KeyboardListener implements KeyboardView.OnKeyboardActionListener {

    private final InputMethodService service;
    private final KeyboardView view;

    public KeyboardListener(final InputMethodService service, final KeyboardView view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void onPress(final int i) {
    }

    @Override
    public void onRelease(final int i) {
    }

    @Override
    public void onKey(final int primaryCode, final int[] keyCodes) {
        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            keyDownUp(KeyEvent.KEYCODE_DEL);
        } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {
            service.requestHideSelf(0);
            view.closing();
        } else {
            final InputConnection conn = service.getCurrentInputConnection();
            conn.commitText(String.valueOf((char) primaryCode), 1);
        }
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
        final InputConnection conn = service.getCurrentInputConnection();
        conn.sendKeyEvent(key(KeyEvent.ACTION_DOWN, keyEventCode));
        conn.sendKeyEvent(key(KeyEvent.ACTION_UP, keyEventCode));
    }

    private KeyEvent key(final int action, final int keyEventCode) {
        return new KeyEvent(action, keyEventCode);
    }
}
