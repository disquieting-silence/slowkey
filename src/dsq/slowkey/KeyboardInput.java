package dsq.slowkey;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import java.util.ArrayList;
import java.util.List;

public class KeyboardInput extends InputMethodService {
    
    private Keyboard keyboard;
    private KeyboardView view;
    private KeyboardView.OnKeyboardActionListener listener = new KeyboardListener(this);
    
    @Override public void onCreate() {
        super.onCreate();
    }

    @Override public void onInitializeInterface() {
        keyboard = new Keyboard(this, R.xml.binary);
    }

    @Override public View onCreateInputView() {
        view = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        view.setKeyboard(keyboard);

        return view;
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
        view.setOnKeyboardActionListener(listener);
        view.closing();
    }

    /**
     * Deal with the editor reporting movement of its cursor.
     */
    @Override public void onUpdateSelection(int oldSelStart, int oldSelEnd,
            int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {

        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
                candidatesStart, candidatesEnd);
    }

    /**
     * This tells us about completions that the editor has determined based
     * on the current text in it.  We want to use this in fullscreen mode
     * to show the completions ourself, since the editor can not be seen
     * in that situation.
     */
    @Override public void onDisplayCompletions(CompletionInfo[] completions) {

    }

    public void onKey(int primaryCode, int[] keyCodes) {
        Log.v("slowkey", "onkey: " + primaryCode + "");

    }

    public void onText(CharSequence text) {
        Log.v("slowkey", "text: " + text);
    }

    /**
     * Use this to monitor key events being delivered to the application.
     * We get first crack at them, and can either resume them or let them
     * continue to the app.
     */
    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("slowkey", "Keydown: " + keyCode);
        return super.onKeyDown(keyCode, event);
    }

}
