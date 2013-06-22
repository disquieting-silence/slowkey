package dsq.slowkey.api;

import android.content.res.Configuration;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import dsq.slowkey.R;
import dsq.slowkey.data.Option;
import dsq.slowkey.keyboard.*;
import dsq.slowkey.view.SlowKeyboardView;

public class KeyboardInput extends InputMethodService implements SlowInputMethodService {
    
    private Keyboards keyboards;
    private SlowKeyboardView view;
    
    private Switcher keyboardSwitcher;

    private KeyboardContexts contexts = new DefaultKeyboardContexts();

    @Override public void onCreate() {
        super.onCreate();
    }

    @Override public void onInitializeInterface() {
        final Window window = this.getWindow().getWindow();
        this.keyboards = new TemplateKeyboards(window, this);
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        final ScreenMode mode = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? ScreenMode.LANDSCAPE : ScreenMode.PORTRAIT;
        keyboardSwitcher.setScreen(mode);
    }

    @Override public View onCreateInputView() {
        view = (SlowKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboardSwitcher = new DefaultSwitcher(view, keyboards);
        setOptKeyboard(keyboards.first());
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
        final int inputType = attribute.inputType;
        final DefaultKeyboardTransform transform = new DefaultKeyboardTransform(inputType);
        final KeyboardListener listener = new KeyboardListener(this, view, keyboardSwitcher, transform);
        final Option<KeyboardType> type = contexts.divine(inputType);
        // FIX: *Cough* Bind...
        if (type.isDefined()) {
            final Option<Keyboard> preferred = keyboards.get(type.getOrDie());
            setOptKeyboard(preferred);
        }
        view.setOnKeyboardActionListener(listener);
        view.closing();
    }

    @Override public void onUpdateSelection(int oldSelStart, int oldSelEnd,
            int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {

        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
                candidatesStart, candidatesEnd);
    }

    private void setOptKeyboard(Option<Keyboard> keyboard) {
        if (keyboard.isDefined()) view.setKeyboard(keyboard.getOrDie());
    }
}
