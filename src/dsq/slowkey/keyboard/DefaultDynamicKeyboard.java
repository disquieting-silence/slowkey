package dsq.slowkey.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;

public class DefaultDynamicKeyboard extends Keyboard implements DynamicKeyboard {

    private int totalHeight;

    public DefaultDynamicKeyboard(final Context context, final int xmlLayoutResId) {
        super(context, xmlLayoutResId);
        totalHeight = super.getHeight();
    }

    public void setDynamicHeight(int totalHeight) {
        this.totalHeight = totalHeight;
    }

    @Override
    // ASSUMPTION: The total height used here in not used internally in Keyboard anywhere else. It is only set, but not
    // read except for this call.
    public int getHeight() {
        return totalHeight;
    }
}
