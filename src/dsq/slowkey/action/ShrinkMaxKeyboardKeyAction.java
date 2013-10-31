package dsq.slowkey.action;

import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class ShrinkMaxKeyboardKeyAction implements KeyAction {
    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher) {
        keyboardSwitcher.shrinkMax();
    }
}
