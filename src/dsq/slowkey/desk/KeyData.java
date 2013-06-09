package dsq.slowkey.desk;

import android.graphics.drawable.Drawable;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;

public class KeyData {
    public KeyData(final String text) {
        this(new None<Integer>(), new Some<String>(text), new None<Drawable>(), new Some<String>(text));
    }

    public KeyData(final int code, final String label) {
        this(new Some<Integer>(code), new None<String>(), new None<Drawable>(), new Some<String>(label));
    }

    public KeyData(final int code, final String label, final boolean isRepeatable) {
        this(new Some<Integer>(code), new None<String>(), new None<Drawable>(), new Some<String>(label), 1, false, false, isRepeatable);
    }

    public KeyData(final int code, final Drawable icon) {
        this(new Some<Integer>(code), new None<String>(), new Some<Drawable>(icon), new None<String>());
    }

    public KeyData(final char character) {
        this(new Some<Integer>((int) character), new Some<String>(String.valueOf(character)), new None<Drawable>(), new Some<String>(String.valueOf(character)));
    }

    public KeyData(final Option<Integer> code, final Option<String> text, final Option<Drawable> image, final Option<String> label) {
        this(code, text, image, label, 1);
    }

    public KeyData(final Option<Integer> code, final Option<String> text, final Option<Drawable> image, final Option<String> label, final int colspan) {
        this(code, text, image, label, colspan, false, false, false);
    }

    public KeyData(final Option<Integer> code, final Option<String> text, final Option<Drawable> image, final Option<String> label,
      final int colspan, final boolean isModifier, boolean isSticky, boolean isRepeatable) {
        this.code = code;
        this.text = text;
        this.image = image;
        this.label = label;
        this.rowspan = 1;
        this.colspan = colspan;
        this.isModifier = isModifier;
        this.isSticky = isSticky;
        this.isRepeatable = isRepeatable;
    }

    public final Option<Integer> code;
    public final Option<String> text;
    public final Option<Drawable> image;
    public final Option<String> label;
    public final int colspan;
    public final int rowspan;
    public final boolean isModifier;
    public final boolean isSticky;
    public final boolean isRepeatable;
}
