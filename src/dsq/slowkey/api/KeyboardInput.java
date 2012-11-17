package dsq.slowkey.api;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import dsq.slowkey.R;
import dsq.slowkey.keyboard.DefaultSwitcher;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

public class KeyboardInput extends InputMethodService implements SlowInputMethodService {
    
    private Keyboard binaryKeyboard;
    private Keyboard blueprintKeyboard;
    private Keyboard a1Keyboard;
    private SlowKeyboardView view;
    
    private Switcher keyboardSwitcher;

    @Override public void onCreate() {
        super.onCreate();
    }

    @Override public void onInitializeInterface() {
        binaryKeyboard = new Keyboard(this, R.xml.binary);
        a1Keyboard = new Keyboard(this, R.xml.a1);
        blueprintKeyboard = new Keyboard(this, R.xml.blueprint);
    }

    @Override public View onCreateInputView() {
        view = (SlowKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboardSwitcher = new DefaultSwitcher(view, binaryKeyboard, a1Keyboard, blueprintKeyboard);
        view.setKeyboard(a1Keyboard);
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
        view.setKeyboard(binaryKeyboard);
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
