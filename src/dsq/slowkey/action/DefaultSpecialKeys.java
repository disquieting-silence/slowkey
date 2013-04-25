package dsq.slowkey.action;

import android.inputmethodservice.Keyboard;
import android.view.KeyEvent;
import dsq.slowkey.keyboard.KeyboardType;

import java.util.HashMap;
import java.util.Map;

import static dsq.slowkey.keyboard.KeyboardType.*;

public class DefaultSpecialKeys implements SpecialKeys {

    private final Map<Integer, KeyAction> mapping = new HashMap<Integer, KeyAction>();

    public DefaultSpecialKeys() {
        mapping.put(Keyboard.KEYCODE_DELETE, new KeycodeKeyAction(KeyEvent.KEYCODE_DEL));
        mapping.put(SpecialKeyCodes.LEFT, new KeycodeKeyAction(KeyEvent.KEYCODE_DPAD_LEFT));
        mapping.put(SpecialKeyCodes.RIGHT, new KeycodeKeyAction(KeyEvent.KEYCODE_DPAD_RIGHT));
        mapping.put(SpecialKeyCodes.UP, new KeycodeKeyAction(KeyEvent.KEYCODE_DPAD_UP));
        mapping.put(SpecialKeyCodes.DOWN, new KeycodeKeyAction(KeyEvent.KEYCODE_DPAD_DOWN));
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
        mapping.put(SpecialKeyCodes.TO_NUMBER, new ShowKeyboardKeyAction(NUMBER));
        mapping.put(SpecialKeyCodes.TO_NAV, new ShowKeyboardKeyAction(NAVIGATION));
        mapping.put(SpecialKeyCodes.TO_COLEMAK0, new ShowKeyboardKeyAction(COLEMAK0));
        mapping.put(SpecialKeyCodes.TO_COLEMAK1, new ShowKeyboardKeyAction(COLEMAK1));
        mapping.put(SpecialKeyCodes.TO_COLEMAK2, new ShowKeyboardKeyAction(COLEMAK2));
        mapping.put(SpecialKeyCodes.TO_COLEMAK_ALPHA, new ShowKeyboardKeyAction(COLEMAK_ALPHA));
        mapping.put(SpecialKeyCodes.TO_MEGA_SYMBOLS, new ShowKeyboardKeyAction(MEGA_SYMBOLS));
    }

    @Override
    public KeyAction interpret(final int code) {
        final KeyAction specialAction = mapping.get(code);
        return specialAction != null ? specialAction : new CharKeyAction(code);
    }
}
