package dsq.slowkey.template;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.DefaultKeyUpdater;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;
import dsq.slowkey.desk.KeyUpdater;

import java.util.ArrayList;
import java.util.List;

public class DefaultKeyGenerator implements KeyGenerator {
    private final KeyUpdater updater = new DefaultKeyUpdater();

    @Override
    public List<Keyboard.Key> generate(final KeyPositionCache cache, final Keyboard keyboard, final int totalWidth, final int keyHeight, final KeyTemplate template) {
        final List<Keyboard.Key> result = new ArrayList<Keyboard.Key>();
        int x = 0;
        int y = 0;
        for (int i = 0; i < template.numRows(); i++) {
            x = 0;
            Keyboard.Row row = new Keyboard.Row(keyboard);
            final int numColumns = template.numColumns(i);
            final int keyWidth = totalWidth / numColumns;
            final int rowHeight = (int)(keyHeight * template.getRowScale(i));
            for (int j = 0; j < numColumns; j++) {
                final Option<KeyData> dataOption = template.get(i, j);
                if (dataOption.isDefined()) {
                    final KeyData data = dataOption.getOrDie();
                    Keyboard.Key key = nu(cache, result.size(), row, data, x, y, keyWidth, rowHeight);
                    result.add(key);
                    // FIX: So very, very evil --- but so is this whole method atm.
                    int step = data.colspan - 1;
                    x += (step * keyWidth);
                    j += step;
                }
                x += keyWidth;

            }
            y += rowHeight;
        }
        return result;
    }

    private Keyboard.Key nu(final KeyPositionCache cache, final int index, final Keyboard.Row row, final KeyData data, final int x, final int y, final int keyWidth, final int keyHeight) {

        Keyboard.Key key = new Keyboard.Key(row) {
            @Override
            // This is hacky beyond hacky. Essentially, the KeyboardView code provided by Android doesn't use the
            // nearestKeys check to find a candidate key if the keycode is not larger than 32. Therefore, if it is
            // not considered inside, no key press is sent through. Through logging, I was able to identify that the
            // nearestKeys part was saying I was hitting backspace, but the view stopped it going through. I think the
            // > 32 check was also why my space sometimes doesn't work. It would be great if this hack fixes that
            // problem. This was all dependent on the value of proximity correction as well (sort of).
            public boolean isInside(final int x, final int y) {
                if (super.isInside(x, y)) return true;
                final Option<Integer> cached = cache.find(x, y);
                return cached.isDefined() && cached.getOrDie() == index;
            }
        };
        key.width = keyWidth;
        key.height = keyHeight;

        key.x = x;
        key.y = y;

        updater.mutate(key, data);
        key.width = keyWidth * data.colspan;
        return key;
    }
}
