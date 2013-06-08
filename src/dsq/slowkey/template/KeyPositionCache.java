package dsq.slowkey.template;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.Option;

import java.util.List;

public interface KeyPositionCache {
    Option<Integer> find(final int x, final int y);
    void update(final List<Keyboard.Key> keys, int width, int height);
}
