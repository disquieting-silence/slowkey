package dsq.slowkey.keyboard;

import android.util.Log;
import android.view.inputmethod.EditorInfo;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;

public class DefaultKeyboardContexts implements KeyboardContexts {

    @Override
    public Option<KeyboardType> divine(final int type) {
        int main = type & EditorInfo.TYPE_MASK_CLASS;
        int variation = type & EditorInfo.TYPE_MASK_VARIATION;

        // number or datetime => numbers
        // phone => numbers
        // text => variation is password .. turn off predictions

        // FIX: Can I get around having this collection of if statements?
        if (main == EditorInfo.TYPE_CLASS_PHONE) return new Some<KeyboardType>(KeyboardType.NUMBER);
        else return new None<KeyboardType>();

    }

}

