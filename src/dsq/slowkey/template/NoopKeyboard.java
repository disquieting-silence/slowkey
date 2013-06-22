package dsq.slowkey.template;

import android.content.Context;
import android.inputmethodservice.Keyboard;

import java.util.ArrayList;
import java.util.List;

public abstract class NoopKeyboard extends Keyboard {
    public NoopKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    @Override
    public List<Key> getKeys() {
        return new ArrayList<Key>();
    }

    @Override
    protected int getHorizontalGap() { return 0; }

    @Override
    protected void setHorizontalGap(final int gap) { }

    @Override
    protected int getVerticalGap() { return 0; }

    @Override
    protected void setVerticalGap(final int gap) { }

    @Override
    protected int getKeyHeight() { return 0; }

    @Override
    protected void setKeyHeight(final int height) { }

    @Override
    protected int getKeyWidth() { return 0; }

    @Override
    protected void setKeyWidth(final int width) { }

    @Override
    public List<Key> getModifierKeys() { return new ArrayList<Key>(); }

    @Override
    public int getHeight() { return 0; }

    @Override
    public int getMinWidth() { return 0; }

    @Override
    public boolean setShifted(final boolean shiftState) { return shiftState; }

    @Override
    public boolean isShifted() { return false; }

    @Override
    public int getShiftKeyIndex() { return -1; }

    @Override
    public int[] getNearestKeys(final int x, final int y) {
        return new int[0];
    }
}
