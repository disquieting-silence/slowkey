package dsq.slowkey.template;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import dsq.slowkey.R;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;
import dsq.slowkey.desk.KeyTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultTemplateKeyboard extends AbstractTemplateKeyboard implements TemplateKeyboard {

    private KeyGenerator generator = new DefaultKeyGenerator();
    private WindowMetrics metrics = new DefaultWindowMetrics();
    private KeyPositionCache cache = new DefaultKeyPositionCache();

    private List<Keyboard.Key> keys;
    private boolean shifted = false;

    private int totalWidth = 0;
    private int totalHeight = 0;

    private Option<Keyboard.Key> shiftKey = new None<Key>();

    public DefaultTemplateKeyboard(Context context) {
        super(context, R.xml.blank);
    }

    @Override
    public void update(final Window window, final double height, final KeyTemplate template) {
        this.totalWidth = metrics.widthPx(window, 1.0);
        int keyHeight = metrics.heightPx(window, height);
        this.keys = generator.generate(cache, this, totalWidth, keyHeight, template);
        // Ignoring for the time being different heights for different rows.
        final Key lastKey = keys.size() > 0 ? keys.get(keys.size() - 1) : null;
        this.totalHeight = lastKey != null ? lastKey.y + lastKey.height : 0;
        final int minKeyWidth = metrics.widthPx(window, 1.0 / template.maxColumns());
        cache.update(this.keys, template.numRows(), template.maxColumns(), minKeyWidth, keyHeight);
        updateModifiers();
    }

    @Override
    public void portrait() {

    }

    @Override
    public void landscape() {

    }

    private void updateModifiers() {
        for (Key key : keys) {
            if (key.codes.length > 0 && key.codes[0] == Keyboard.KEYCODE_SHIFT) {
                shiftKey = new Some<Key>(key);
            }
        }
    }

    @Override
    public List<Key> getModifierKeys() {
        return shiftKey.isDefined() ? Arrays.asList(shiftKey.getOrDie()) : new ArrayList<Key>();
    }

    @Override
    public List<Keyboard.Key> getKeys() {
        return keys;
    }

    @Override
    public boolean setShifted(final boolean shift) {
        // Very similar code to the original Keyboard, though there isn't really anything else you can do
        boolean changed = shifted != shift;
        if (shiftKey.isDefined()) {
            shiftKey.getOrDie().on = shift;
        }
        shifted = shift;
        return changed;
    }

    @Override
    public boolean isShifted() {
        return shifted;
    }

    @Override
    public int getMinWidth() {
        return totalWidth;
    }

    @Override
    public int getHeight() {
        return totalHeight;
    }

    @Override
    public int[] getNearestKeys(final int x, final int y) {
        final Option<Integer> keyOption = cache.find(x, y);
        return keyOption.isDefined() ? new int[] { keyOption.getOrDie() } : new int[0];
    }
}
