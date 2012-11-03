package dsq.slowkey;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SlowKeyboardView extends KeyboardView {
    public SlowKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlowKeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    public boolean onTouchEvent(final MotionEvent me) {
//        return false;
//    }
}
