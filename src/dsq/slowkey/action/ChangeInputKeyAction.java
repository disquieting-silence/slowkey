package dsq.slowkey.action;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class ChangeInputKeyAction implements KeyAction {
    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view, final Switcher keyboardSwitcher) {
        final InputMethodManager manager = (InputMethodManager)view.view().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) manager.showInputMethodPicker();
    }
}
