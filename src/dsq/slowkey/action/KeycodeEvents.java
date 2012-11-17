package dsq.slowkey.action;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import dsq.slowkey.api.SlowInputMethodService;

public class KeycodeEvents {
    public static void keyDownUp(final SlowInputMethodService service, int keyEventCode) {
        final InputConnection conn = service.getCurrentInputConnection();
        if (conn != null) {
            conn.sendKeyEvent(key(KeyEvent.ACTION_DOWN, keyEventCode));
            conn.sendKeyEvent(key(KeyEvent.ACTION_UP, keyEventCode));
        }
    }

    private static KeyEvent key(final int action, final int keyEventCode) {
        return new KeyEvent(action, keyEventCode);
    }
}
