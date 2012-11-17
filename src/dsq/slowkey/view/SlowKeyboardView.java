package dsq.slowkey.view;

import android.inputmethodservice.Keyboard;
import android.view.View;
import dsq.slowkey.api.KeyboardListener;

public interface SlowKeyboardView  {
    public void closing();

    void setKeyboard(Keyboard keyboard);

    View view();

    void setOnKeyboardActionListener(KeyboardListener listener);
}
