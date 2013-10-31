package dsq.slowkey.action;

import android.inputmethodservice.Keyboard;
import android.view.KeyEvent;

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
        mapping.put(Keyboard.KEYCODE_DONE, new KeycodeKeyAction(KeyEvent.KEYCODE_ENTER));
        mapping.put(SpecialKeyCodes.CHANGE_INPUT, new ChangeInputKeyAction());
        mapping.put(SpecialKeyCodes.TO_SYMBOL, new ShowKeyboardKeyAction(SYMBOL));
        mapping.put(SpecialKeyCodes.TO_NUMBER, new ShowKeyboardKeyAction(NUMBER));
        mapping.put(SpecialKeyCodes.TO_LETTER, new ShowKeyboardKeyAction(LETTER));
        mapping.put(SpecialKeyCodes.TO_COLEMAK_TOP, new ShowKeyboardKeyAction(TOP_ROW));
        mapping.put(SpecialKeyCodes.TO_COLEMAK_MIDDLE, new ShowKeyboardKeyAction(MIDDLE_ROW));
        mapping.put(SpecialKeyCodes.TO_COLEMAK_BOTTOM, new ShowKeyboardKeyAction(BOTTOM_ROW));
        mapping.put(SpecialKeyCodes.TO_KEYBOARD_LIST, new ShowKeyboardKeyAction(KEYBOARD_LIST));
        mapping.put(SpecialKeyCodes.KEYBOARD_GROW, new GrowKeyboardKeyAction());
        mapping.put(SpecialKeyCodes.KEYBOARD_SHRINK, new ShrinkKeyboardKeyAction());
    }

    @Override
    public KeyAction interpret(final int code) {
        final KeyAction specialAction = mapping.get(code);
        return specialAction != null ? specialAction : new CharKeyAction(code);
    }
}
