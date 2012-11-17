package dsq.slowkey.action;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.view.SlowKeyboardView;

import java.util.HashMap;
import java.util.Map;

public class DefaultSpecialKeys implements SpecialKeys {

    private final Map<Integer, KeyAction> mapping = new HashMap<Integer, KeyAction>();

    public DefaultSpecialKeys() {
        mapping.put(Keyboard.KEYCODE_DELETE, new BackspaceKeyAction());
        mapping.put(Keyboard.KEYCODE_CANCEL, new CancelKeyAction());
        mapping.put(SpecialKeyCodes.TESTER, new TextKeyAction('y'));
    }

    @Override
    public KeyAction interpret(final int code) {
        final KeyAction specialAction = mapping.get(code);
        return specialAction != null ? specialAction : new TextKeyAction(code);
    }
}
