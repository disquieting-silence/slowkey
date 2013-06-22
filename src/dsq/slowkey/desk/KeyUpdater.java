package dsq.slowkey.desk;

import android.inputmethodservice.Keyboard;

public interface KeyUpdater {
    void mutate(Keyboard.Key key, KeyData data);
}
