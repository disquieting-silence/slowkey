package dsq.slowkey.api;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import dsq.slowkey.R;
import dsq.slowkey.view.SlowKeyboardView;

public class KeyboardInput extends InputMethodService implements SlowInputMethodService {
    
    private Keyboard keyboard;
    private SlowKeyboardView view;

    @Override public void onCreate() {
        super.onCreate();
    }

    @Override public void onInitializeInterface() {
        keyboard = new Keyboard(this, R.xml.binary);
    }

    @Override public View onCreateInputView() {
        view = (SlowKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        view.setKeyboard(keyboard);
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
        view.setKeyboard(keyboard);
        final KeyboardListener listener = new KeyboardListener(this, view);
        view.setOnKeyboardActionListener(listener);
        view.closing();
    }

    @Override public void onUpdateSelection(int oldSelStart, int oldSelEnd,
            int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {

        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
                candidatesStart, candidatesEnd);
    }

}
