package dsq.slowkey.template;

import android.inputmethodservice.Keyboard;

import java.util.List;

public interface PublicKeyboard {
    List<Keyboard.Key> getKeys();
    boolean setShifted(boolean shift);
    boolean isShifted();
    int getMinWidth();
    int getHeight();
    int[] getNearestKeys(int x, int y);
}
