package dsq.slowkey.api;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import dsq.slowkey.R;
import dsq.slowkey.keyboard.DefaultSwitcher;
import dsq.slowkey.keyboard.Switcher;
import dsq.slowkey.view.SlowKeyboardView;

import java.util.Arrays;

public class KeyboardInput extends InputMethodService implements SlowInputMethodService {
    
    private Keyboard binaryKeyboard;
    private Keyboard lolKeyboard;
    private Keyboard blueprintKeyboard;
    private SlowKeyboardView view;
    
    private Switcher keyboardSwitcher;

    @Override public void onCreate() {
        super.onCreate();
    }

    @Override public void onInitializeInterface() {
        binaryKeyboard = new Keyboard(this, R.xml.binary);
        lolKeyboard = new Keyboard(this, R.xml.lol);
        blueprintKeyboard = new Keyboard(this, R.xml.blueprint);
    }

    @Override public View onCreateInputView() {
        view = (SlowKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboardSwitcher = new DefaultSwitcher(view, binaryKeyboard, lolKeyboard, blueprintKeyboard);
        view.setKeyboard(binaryKeyboard);
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
