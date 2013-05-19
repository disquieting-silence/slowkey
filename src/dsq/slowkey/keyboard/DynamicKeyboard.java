package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;

import java.util.List;

public interface DynamicKeyboard {
    void setDynamicHeight(int totalHeight);
    List<Keyboard.Key> getKeys();
}
