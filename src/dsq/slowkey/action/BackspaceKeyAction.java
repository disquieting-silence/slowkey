package dsq.slowkey.action;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.view.SlowKeyboardView;

public class BackspaceKeyAction implements KeyAction {
    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view) {
        keyDownUp(service, KeyEvent.KEYCODE_DEL);
    }

    private void keyDownUp(final SlowInputMethodService service, int keyEventCode) {
        final InputConnection conn = service.getCurrentInputConnection();
        conn.sendKeyEvent(key(KeyEvent.ACTION_DOWN, keyEventCode));
        conn.sendKeyEvent(key(KeyEvent.ACTION_UP, keyEventCode));
    }

    private KeyEvent key(final int action, final int keyEventCode) {
        return new KeyEvent(action, keyEventCode);
    }
}
