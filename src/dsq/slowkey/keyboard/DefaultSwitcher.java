package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;
import android.util.Log;
import dsq.slowkey.view.SlowKeyboardView;

import java.util.List;

public class DefaultSwitcher implements Switcher {

    private final SlowKeyboardView view;
    private final Keyboard binaryKeyboard;
    private final Keyboard lolKeyboard;
    private final Keyboard blueprintKeyboard;
    
    public DefaultSwitcher(final SlowKeyboardView view, final Keyboard binaryKeyboard, final Keyboard lolKeyboard, final Keyboard blueprintKeyboard) {
        this.view = view;
        this.binaryKeyboard = binaryKeyboard;
        this.lolKeyboard = lolKeyboard;
        this.blueprintKeyboard = blueprintKeyboard;
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
        view.setKeyboard(blueprintKeyboard);
    }

    @Override
    public void toBinary() {
        view.setKeyboard(binaryKeyboard);
    }
    
    private String conform(final CharSequence s, final boolean state) {
        final String label = s.toString();
        boolean isLetter = label.length() == 1;
        if (isLetter && Character.isLowerCase(label.charAt(0)) && state) {
            return label.toUpperCase();
        } else if (isLetter && !Character.isLowerCase(label.charAt(0)) && !state) {
            return label.toLowerCase();
        } else {
            return label;
        }
    }

    @Override
    public void toggleShifted() {
        final Keyboard current = view.getKeyboard();
        current.setShifted(!current.isShifted());
        final boolean state = current.isShifted();
        final List<Keyboard.Key> allKeys = current.getKeys();
        for (Keyboard.Key k : allKeys) {
            k.label = k.label != null ? conform(k.label, state) : k.label;
        }
        view.invalidateAllKeys();
    }

    @Override
    public boolean isShifted() {
        final Keyboard current = view.getKeyboard();
        return current.isShifted();
    }
}
