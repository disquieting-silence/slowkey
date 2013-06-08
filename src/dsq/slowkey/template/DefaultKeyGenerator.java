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
        for (int i = 0; i < template.numRows(); i++) {
            Keyboard.Row row = new Keyboard.Row(keyboard);
            final int numColumns = template.numColumns(i);
            final int keyWidth = totalWidth / numColumns;
            for (int j = 0; j < numColumns; j++) {
                final Option<KeyData> dataOption = template.get(i, j);
                if (dataOption.isDefined()) {
                    final KeyData data = dataOption.getOrDie();
                    Keyboard.Key key = nu(cache, result.size(), row, data, j, i, keyWidth, keyHeight);
                    result.add(key);
                    // FIX: So very, very evil --- but so is this whole method atm.
                    j += (data.colspan - 1);
                }

            }
        }
        return result;
    }

    private Keyboard.Key nu(final KeyPositionCache cache, final int index, final Keyboard.Row row, final KeyData data, final int x, final int y, final int keyWidth, final int keyHeight) {

        Keyboard.Key key = new Keyboard.Key(row) {
            @Override
            // This is hacky beyond hacky.
            public boolean isInside(final int x, final int y) {
                if (super.isInside(x, y)) return true;
                final Option<Integer> cached = cache.find(x, y);
                return cached.isDefined() && cached.getOrDie() == index;
            }
        };
        key.width = keyWidth;
        key.height = keyHeight;

        key.x = keyWidth * x;
        key.y = keyHeight * y;

        updater.mutate(key, data);
        key.width = keyWidth * data.colspan;
        return key;
    }
}
