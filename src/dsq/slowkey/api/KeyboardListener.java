package dsq.slowkey.api;

import android.inputmethodservice.KeyboardView;
import dsq.slowkey.action.DefaultSpecialKeys;
import dsq.slowkey.action.KeyAction;
import dsq.slowkey.action.SpecialKeys;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class KeyboardListener extends NoopKeyboardListener implements KeyboardView.OnKeyboardActionListener {

    private final SlowInputMethodService service;
    private final SlowKeyboardView view;
    private final Switcher keyboardSwitcher;

    private final SpecialKeys keys = new DefaultSpecialKeys();

    public KeyboardListener(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher) {
        this.service = service;
        this.view = view;
        this.keyboardSwitcher = keyboardSwitcher;
    }

    @Override
    public void onKey(final int primaryCode, final int[] keyCodes) {
        final KeyAction keyAction = keys.interpret(primaryCode);
        keyAction.run(service, view, keyboardSwitcher);
    }

    @Override
    public void swipeDown() {
        service.requestHideSelf(0);
        view.closing();
    }
}
