package dsq.slowkey.action;

import android.view.inputmethod.InputConnection;
import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class CharKeyAction implements KeyAction {
    private final int code;

    public CharKeyAction(final int code) {
        this.code = code;
    }

    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher) {
        final InputConnection conn = service.getCurrentInputConnection();
        final String character = String.valueOf((char) code);
        final String value = keyboardSwitcher.isShifted() ? character.toUpperCase() : character;
        conn.commitText(value, 1);
    }
}
