package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.Option;
import dsq.slowkey.view.SlowKeyboardView;

import java.util.List;

public class DefaultSwitcher implements Switcher {

    private final SlowKeyboardView view;
    private final Keyboards keyboards;

    public DefaultSwitcher(final SlowKeyboardView view, final Keyboards keyboards) {
        this.view = view;
        this.keyboards = keyboards;
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
    public void toKeyboard(final KeyboardType type) {
        Option<Keyboard> next = keyboards.get(type);
        if (next.isDefined()) view.setKeyboard(next.getOrDie());
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

    @Override
    public void shrink() {
        keyboards.shrink();
        refresh();
    }

    @Override
    public void grow() {
        keyboards.grow();
        refresh();
    }

    @Override
    public void setScreen(final ScreenMode mode) {
        keyboards.setScreen(mode);
        refresh();
    }

    private void refresh() {
        view.setKeyboard(view.getKeyboard());
    }

}
