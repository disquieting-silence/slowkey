package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.Option;

public interface Keyboards {
    Option<Keyboard> get(KeyboardType type);

    Option<Keyboard> first();
}
