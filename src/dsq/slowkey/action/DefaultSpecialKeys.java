package dsq.slowkey.action;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.keyboard.KeyboardType;

import java.util.HashMap;
import java.util.Map;

import static dsq.slowkey.keyboard.KeyboardType.*;

public class DefaultSpecialKeys implements SpecialKeys {

    private final Map<Integer, KeyAction> mapping = new HashMap<Integer, KeyAction>();

    public DefaultSpecialKeys() {
        mapping.put(Keyboard.KEYCODE_DELETE, new BackspaceKeyAction());
        mapping.put(Keyboard.KEYCODE_CANCEL, new CancelKeyAction());
        mapping.put(Keyboard.KEYCODE_SHIFT, new ShiftKeyAction());
        mapping.put(SpecialKeyCodes.TO_A1, new ShowKeyboardKeyAction(A1));
        mapping.put(SpecialKeyCodes.TO_A2, new ShowKeyboardKeyAction(A2));
        mapping.put(SpecialKeyCodes.TO_A3, new ShowKeyboardKeyAction(A3));
        mapping.put(SpecialKeyCodes.TO_A4, new ShowKeyboardKeyAction(A4));
        mapping.put(SpecialKeyCodes.TO_B1, new ShowKeyboardKeyAction(B1));
        mapping.put(SpecialKeyCodes.TO_B2, new ShowKeyboardKeyAction(B2));
        mapping.put(SpecialKeyCodes.TO_B3, new ShowKeyboardKeyAction(B3));
        mapping.put(SpecialKeyCodes.TO_B4, new ShowKeyboardKeyAction(B4));
        mapping.put(SpecialKeyCodes.TO_BINARY, new ShowKeyboardKeyAction(BINARY));
        mapping.put(SpecialKeyCodes.TO_BLUEPRINT, new ShowKeyboardKeyAction(BLUEPRINT));
        mapping.put(SpecialKeyCodes.TO_SYMBOL, new ShowKeyboardKeyAction(SYMBOL));
    }

    @Override
    public KeyAction interpret(final int code) {
        final KeyAction specialAction = mapping.get(code);
        return specialAction != null ? specialAction : new CharKeyAction(code);
    }
}
