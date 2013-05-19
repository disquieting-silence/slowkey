package dsq.slowkey.view;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import dsq.slowkey.api.KeyboardInput;
import dsq.slowkey.api.KeyboardListener;
import dsq.slowkey.keyboard.DynamicKeyboard;

public class DefaultSlowKeyboardView extends KeyboardView implements SlowKeyboardView {

    public static final double SCALE_STEP = 0.01;
    public static final double SCALE_MIN = 0.07;
    public static final double SCALE_MAX = 0.12;
    private double percent = 0.11;
    private final KeyScales scaler = new DefaultKeyScales();

    public DefaultSlowKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultSlowKeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View view() {
        return this;
    }

    @Override
    public void setKeyboard(final Keyboard keyboard) {
        scaleKeyboard(keyboard, percent);
        super.setKeyboard(keyboard);
    }

    @Override
    public void shrink() {
        adjustHeight(percent - SCALE_STEP);
    }

    @Override
    public void grow() {
        adjustHeight(percent + SCALE_STEP);
    }

    private void adjustHeight(final double newValue) {
        final double next = Math.max(SCALE_MIN, Math.min(newValue, SCALE_MAX));
        if (Math.abs(next - percent) > 0.0001) {
            final Keyboard keyboard = getKeyboard();
            scaleKeyboard(keyboard, next);
            super.setKeyboard(getKeyboard());
        }
    }

    private void scaleKeyboard(final Keyboard keyboard, final double next) {
        if (keyboard instanceof DynamicKeyboard) {
            final Window window = ((KeyboardInput) getContext()).getWindow().getWindow();
            scaler.scale(window, (DynamicKeyboard)keyboard, next);
            percent = next;
        }
    }

    @Override
    public void setOnKeyboardActionListener(final KeyboardListener listener) {
        super.setOnKeyboardActionListener(listener);
    }

    @Override
    public void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
