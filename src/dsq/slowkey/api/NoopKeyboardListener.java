package dsq.slowkey.api;

import android.inputmethodservice.KeyboardView;

// Not sure if I'm happy with the inheritance that this causes.
public class NoopKeyboardListener implements KeyboardView.OnKeyboardActionListener {
    @Override
    public void onPress(final int i) {
    }

    @Override
    public void onRelease(final int i) {
    }

    @Override
    public void onKey(final int i, final int[] ints) {
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
}
