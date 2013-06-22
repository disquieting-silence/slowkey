package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.Option;

public interface Keyboards {
    Option<Keyboard> get(KeyboardType type);
    void adjustHeight(double percent);
    Option<Keyboard> first();
    void setScreen(ScreenMode mode);
}
