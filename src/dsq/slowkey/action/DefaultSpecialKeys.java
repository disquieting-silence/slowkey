package dsq.slowkey.action;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.Some;

public class DefaultSpecialKeys implements SpecialKeys {
    
    private final KeyAction cancelAction = new CancelKeyAction();
    private final KeyAction backspaceAction = new BackspaceKeyAction();
    
    @Override
    public KeyAction interpret(final int code) {
        if (code == Keyboard.KEYCODE_DELETE) {
            return backspaceAction;
        } else if (code == Keyboard.KEYCODE_CANCEL) {
            return cancelAction;
        } else {
            return new TextKeyAction(code);
        }
    }
}
