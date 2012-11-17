package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;

public interface Switcher {
    void next();
    void prev();
    void toA1();
    void toBinary();
    void toBlueprint();
    void toggleShifted();
    boolean isShifted();
}
