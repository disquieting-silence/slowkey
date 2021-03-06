package dsq.slowkey.template;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.desk.KeyTemplate;

import java.util.List;

public interface KeyGenerator {
    List<Keyboard.Key> generate(final KeyPositionCache cache, final Keyboard keyboard, final int totalWidth, final int keyHeight, final KeyTemplate template);
}
