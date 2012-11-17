package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.view.SlowKeyboardView;

import java.util.List;

public class DefaultSwitcher implements Switcher {

    private final SlowKeyboardView view;
    private final Keyboard binaryKeyboard;
    private final Keyboard a1Keyboard;
    private final Keyboard blueprintKeyboard;
    
    public DefaultSwitcher(final SlowKeyboardView view, final Keyboard binaryKeyboard, final Keyboard a1Keyboard, final Keyboard blueprintKeyboard) {
        this.view = view;
        this.binaryKeyboard = binaryKeyboard;
        this.a1Keyboard = a1Keyboard;
        this.blueprintKeyboard = blueprintKeyboard;
    }

    @Override
    public void next() {
        final Keyboard current = view.getKeyboard();
        final Keyboard now = (current == binaryKeyboard) ? a1Keyboard : binaryKeyboard;
        view.setKeyboard(now);
    }

    @Override
    public void prev() {
        final Keyboard current = view.getKeyboard();
        final Keyboard now = (current == binaryKeyboard) ? a1Keyboard : binaryKeyboard;
        view.setKeyboard(now);
    }

    @Override
    public void toA1() {
        view.setKeyboard(a1Keyboard);
    }

    @Override
    public void toBinary() {
        view.setKeyboard(binaryKeyboard);
    }

    @Override
    public void toBlueprint() {
        view.setKeyboard(blueprintKeyboard);
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
