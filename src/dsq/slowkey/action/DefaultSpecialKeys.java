package dsq.slowkey.action;

import android.inputmethodservice.Keyboard;

import java.util.HashMap;
import java.util.Map;

public class DefaultSpecialKeys implements SpecialKeys {

    private final Map<Integer, KeyAction> mapping = new HashMap<Integer, KeyAction>();

    public DefaultSpecialKeys() {
        mapping.put(Keyboard.KEYCODE_DELETE, new BackspaceKeyAction());
        mapping.put(Keyboard.KEYCODE_CANCEL, new CancelKeyAction());
        mapping.put(SpecialKeyCodes.TO_LOL, new ShowLolKeyAction());
        mapping.put(SpecialKeyCodes.TO_BINARY, new ShowBinaryKeyAction());
        mapping.put(SpecialKeyCodes.HTH, new TextKeyAction("hth"));
        mapping.put(SpecialKeyCodes.LOL, new TextKeyAction("lol"));
        mapping.put(Keyboard.KEYCODE_SHIFT, new ShiftKeyAction());
    }

    @Override
    public KeyAction interpret(final int code) {
        final KeyAction specialAction = mapping.get(code);
        return specialAction != null ? specialAction : new CharKeyAction(code);
    }
}
