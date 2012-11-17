package dsq.slowkey.special;

import dsq.slowkey.data.Option;
import dsq.slowkey.special.KeyAction;

public interface SpecialKeys {
    Option<KeyAction> interpret(int keycode);
}
