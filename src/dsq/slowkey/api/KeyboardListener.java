package dsq.slowkey.api;

import android.inputmethodservice.KeyboardView;
import dsq.slowkey.action.*;
import dsq.slowkey.keyboard.KeyboardTransform;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class KeyboardListener extends NoopKeyboardListener implements KeyboardView.OnKeyboardActionListener {

    private final SlowInputMethodService service;
    private final SlowKeyboardView view;
    private final Switcher keyboardSwitcher;
    private final KeyboardTransform transform;

    private final SpecialKeys keys = new DefaultSpecialKeys();

    public KeyboardListener(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher, final KeyboardTransform transform) {
        this.service = service;
        this.view = view;
        this.keyboardSwitcher = keyboardSwitcher;
        this.transform = transform;
    }

    @Override
    public void onKey(final int rawCode, final int[] keyCodes) {
        final int primaryCode = transform.transform(rawCode);
        final KeyAction keyAction = keys.interpret(primaryCode);
        keyAction.run(service, view, keyboardSwitcher);
    }

    @Override
    public void onText(final CharSequence text) {
        final TextKeyAction keyAction = new TextKeyAction(text.toString());
        keyAction.run(service, view, keyboardSwitcher);
    }
}
