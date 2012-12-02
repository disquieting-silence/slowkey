package dsq.slowkey.api;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import dsq.slowkey.R;
import dsq.slowkey.keyboard.DefaultKeyboards;
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
        final Keyboard binary = new Keyboard(this, R.xml.binary);
        final Keyboard a1 = new Keyboard(this, R.xml.a1);
        final Keyboard a2 = new Keyboard(this, R.xml.a2);
        final Keyboard a3 = new Keyboard(this, R.xml.a3);
        final Keyboard a4 = new Keyboard(this, R.xml.a4);
        final Keyboard b1 = new Keyboard(this, R.xml.b1);
        final Keyboard b2 = new Keyboard(this, R.xml.b2);
        final Keyboard b3 = new Keyboard(this, R.xml.b3);
        final Keyboard b4 = new Keyboard(this, R.xml.b4);
        final Keyboard symbol = new Keyboard(this, R.xml.symbol);
        final Keyboard number = new Keyboard(this, R.xml.number);
        final Keyboard navigation = new Keyboard(this, R.xml.navigation);
        final Keyboard colemak0 = new Keyboard(this, R.xml.colemak0);
        final Keyboard colemak1 = new Keyboard(this, R.xml.colemak1);
        final Keyboard colemak2 = new Keyboard(this, R.xml.colemak2);

        final Keyboard blueprint = new Keyboard(this, R.xml.blueprint);
        this.keyboards = new DefaultKeyboards(a1, a2, a3, a4, b1, b2, b3, b4, blueprint, binary, symbol, number, navigation, colemak0, colemak1, colemak2);
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
