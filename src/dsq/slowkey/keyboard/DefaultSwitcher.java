package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.view.SlowKeyboardView;

public class DefaultSwitcher implements Switcher {

    private final SlowKeyboardView view;
    private final Keyboard binaryKeyboard;
    private final Keyboard lolKeyboard;
    
    public DefaultSwitcher(final SlowKeyboardView view, final Keyboard binaryKeyboard, final Keyboard lolKeyboard) {
        this.view = view;
        this.binaryKeyboard = binaryKeyboard;
        this.lolKeyboard = lolKeyboard;
    }

    @Override
    public void next() {
        final Keyboard current = view.getKeyboard();
        final Keyboard now = (current == binaryKeyboard) ? lolKeyboard : binaryKeyboard;
        view.setKeyboard(now);
    }

    @Override
    public void prev() {
        final Keyboard current = view.getKeyboard();
        final Keyboard now = (current == binaryKeyboard) ? lolKeyboard : binaryKeyboard;
        view.setKeyboard(now);
    }

    @Override
    public void toLol() {
        view.setKeyboard(lolKeyboard);
    }

    @Override
    public void toBinary() {
        view.setKeyboard(binaryKeyboard);
    }
}
