package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;

public interface Keyboards {
    Keyboard get(KeyboardType type);

    Keyboard first();
}
