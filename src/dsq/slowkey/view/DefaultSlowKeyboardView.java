package dsq.slowkey.view;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;
import dsq.slowkey.api.KeyboardListener;

public class DefaultSlowKeyboardView extends KeyboardView implements SlowKeyboardView {
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
    public void setOnKeyboardActionListener(final KeyboardListener listener) {
        super.setOnKeyboardActionListener(listener);
    }

    @Override
    public void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
