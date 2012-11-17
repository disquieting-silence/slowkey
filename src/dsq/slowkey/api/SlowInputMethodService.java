package dsq.slowkey.api;

import android.view.inputmethod.InputConnection;

public interface SlowInputMethodService {
    InputConnection getCurrentInputConnection();
    void requestHideSelf(int flags);
}
