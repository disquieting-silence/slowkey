package dsq.slowkey.view;

import android.inputmethodservice.Keyboard;
import android.view.View;
import dsq.slowkey.api.KeyboardListener;

public interface SlowKeyboardView  {
    public void closing();

    void setKeyboard(Keyboard keyboard);
    Keyboard getKeyboard();

    View view();
    void shrink();
    void grow();

    void setOnKeyboardActionListener(KeyboardListener listener);

    void invalidateAllKeys();
}
