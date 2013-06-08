package dsq.slowkey.desk;

import dsq.slowkey.data.Option;

public class KeyData {
    public KeyData(final Option<Integer> code, final Option<String> text, final Option<Long> image) {
        this.code = code;
        this.text = text;
        this.image = image;
    }

    public final Option<Integer> code;
    public final Option<String> text;
    public final Option<Long> image;
}
