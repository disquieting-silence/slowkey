package dsq.slowkey.action;

import android.util.Log;
import android.view.inputmethod.InputConnection;
import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class TextKeyAction implements KeyAction {
    private final String value;

    public TextKeyAction(final String value) {
        this.value = value;
    }

    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher) {
        final InputConnection conn = service.getCurrentInputConnection();
        conn.commitText(value, 1);
        if (keyboardSwitcher.isShifted()) {
            Log.v("slowkey", "shifted");
            keyboardSwitcher.toggleShifted();
        } else {
            Log.v("slowkey", "ushifted");
        }
    }
}
