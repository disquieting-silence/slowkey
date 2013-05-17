package dsq.slowkey.api;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import dsq.slowkey.R;
import dsq.slowkey.keyboard.ColemakKeyboards;
import dsq.slowkey.keyboard.DefaultSwitcher;
import dsq.slowkey.keyboard.Keyboards;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class KeyboardInput extends InputMethodService implements SlowInputMethodService {
    
    private Keyboards keyboards;
    private SlowKeyboardView view;
    
    private Switcher keyboardSwitcher;

    @Override public void onCreate() {
        super.onCreate();
    }

    @Override public void onInitializeInterface() {
        this.keyboards = new ColemakKeyboards(this);
    }

    @Override public View onCreateInputView() {
        view = (SlowKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboardSwitcher = new DefaultSwitcher(view, keyboards);
        view.setKeyboard(keyboards.first());
        return view.view();
    }

    @Override public View onCreateCandidatesView() {
        return null;
    }

    @Override public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
    }

    @Override public void onFinishInput() {
        super.onFinishInput();
        if (view != null) view.closing();
    }

    @Override public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        final KeyboardListener listener = new KeyboardListener(this, view, keyboardSwitcher);
        view.setOnKeyboardActionListener(listener);
        view.closing();
    }

    @Override public void onUpdateSelection(int oldSelStart, int oldSelEnd,
            int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {

        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
                candidatesStart, candidatesEnd);
    }
}
