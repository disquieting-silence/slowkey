package dsq.slowkey.action;

import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class KeycodeKeyAction implements KeyAction {
    private final int value;

    public KeycodeKeyAction(final int value) {
        this.value = value;
    }

    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher) {
        KeycodeEvents.keyDownUp(service, value);
    }
}
