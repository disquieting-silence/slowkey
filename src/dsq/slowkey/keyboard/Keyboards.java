package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.Option;

public interface Keyboards {
    Option<Keyboard> get(KeyboardType type);
    Option<Keyboard> first();
    void setScreen(ScreenMode mode);

    void shrink();
    void grow();
}
