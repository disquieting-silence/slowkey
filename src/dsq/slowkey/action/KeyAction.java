package dsq.slowkey.action;

import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public interface KeyAction {
    void run(SlowInputMethodService service, SlowKeyboardView view, final Switcher keyboardSwitcher);
}
