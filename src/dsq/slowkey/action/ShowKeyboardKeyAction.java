package dsq.slowkey.action;

import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.keyboard.KeyboardType;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class ShowKeyboardKeyAction implements KeyAction {
    
    private final KeyboardType type;

    public ShowKeyboardKeyAction(final KeyboardType type) {
        this.type = type;
    }

    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher) {
        keyboardSwitcher.toKeyboard(type);
    }
}
