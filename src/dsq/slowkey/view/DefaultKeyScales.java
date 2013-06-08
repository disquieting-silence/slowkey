package dsq.slowkey.view;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.DefaultKeyUpdater;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;
import dsq.slowkey.desk.KeyUpdater;
import dsq.slowkey.keyboard.DynamicKeyboard;
import dsq.slowkey.template.MegaAlphaTemplate;

import java.util.ArrayList;
import java.util.List;

public class DefaultKeyScales implements KeyScales {

    private final KeyTemplate dummy;

    public DefaultKeyScales(final Context context) {
        dummy = new MegaAlphaTemplate(context);
    }

    private final KeyUpdater updater = new DefaultKeyUpdater();

    @Override
    public void scale(final Window window, final DynamicKeyboard keyboard, final double percent) {
        final int height = heightPx(window, percent);
        final int width = widthPx(window, 1.0);
//        ((Keyboard/)keyboard).
        final List<Keyboard.Key> keys = keyboard.getKeys();
        if (keys.size() > 0) {
            final Keyboard.Key[][] sorted = identify(keys, dummy);

            mutate(sorted, width, height);
            final int numRows = dummy.numRows();
            final int totalHeight = numRows > 0 ? bottomOfRow(sorted, numRows - 1) : 0;
            keyboard.setDynamicHeight(totalHeight);
        }
    }

    private int bottomOfRow(final Keyboard.Key[][] sorted, final int row) {
        final Keyboard.Key lastKey = row < sorted.length && sorted[row].length > 0 ? sorted[row][0] : null;
        if (lastKey != null) Log.v("SLOWKEY", "bottom: " + row + ", " + lastKey.y + ", " + lastKey.height);
        return lastKey != null ? lastKey.y + lastKey.height + 1 : 0;
    }

    private int heightPx(final Window window, final double percent) {
        final DisplayMetrics metrics = metrics(window);
        return (int)(percent * metrics.heightPixels);
    }

    private int widthPx(final Window window, final double percent) {
        final DisplayMetrics metrics = metrics(window);
        return (int)(percent * metrics.widthPixels);
    }

    private DisplayMetrics metrics(final Window window) {
        final DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    private Keyboard.Key[][] identify(final List<Keyboard.Key> keys, KeyTemplate template) {
        final List<List<Keyboard.Key>> r = new ArrayList<List<Keyboard.Key>>();
        Keyboard.Key prev = null;

        List<Keyboard.Key> current = new ArrayList<Keyboard.Key>();
        for (Keyboard.Key key : keys) {
            if (prev != null && prev.x > key.x) {
                Log.v("SLOWKEY", "New row");
                r.add(current);
                current = new ArrayList<Keyboard.Key>();
            }

            current.add(key);
            prev = key;
        }
        if (! current.isEmpty()) r.add(current);

        final Keyboard.Key[][] result = new Keyboard.Key[r.size()][];
        for (int i = 0; i < r.size(); i++) {
            final List<Keyboard.Key> mogel = r.get(i);
            result[i] = new Keyboard.Key[mogel.size()];
            for (int j = 0; j < mogel.size(); j++) {
                result[i][j] = mogel.get(j);
            }
        }

        return result;
    }

    private void mutate(final Keyboard.Key[][] keys, final int width, final int height) {
        int menuHeight = 0;

        for (int r = 0; r < keys.length; r++) {
            for (int c = 0; c < keys[r].length; c++) {
                final int w = dummy.numColumns(r) > 0 ? width / dummy.numColumns(r) : 0;
                Log.v("SLOWKEY", "r: " + r + ", c: " + c);
                final Keyboard.Key key = keys[r][c];
                Log.v("SLOWKEY", "key= " + key);
                if (r == 0) menuHeight = Math.max(menuHeight, key.height);
                else {
                    key.height = height;
                    key.y = menuHeight + (r - 1) * (height + 1);
                }

                final Option<KeyData> dataOption = dummy.get(r, c);
                if (dataOption.isDefined()) {
                    final KeyData data = dataOption.getOrDie();
                    updater.mutate(key, data);
                    int delta = Math.max(0, data.colspan - 1);
                    key.width = w * data.colspan;
                    c += delta;
                } else {
                    Log.v("SLOWKEY", "making key: " + key.label + " disappear");
                    key.height = 0;
                    key.width = 0;
                }
            }
        }
    }

//    private void mutate2(final List<Keyboard.Key> keys, final int height) {
//        Keyboard.Key prev = null;
//        int menuHeight = 0;
//        int row = 0;
//        int col = 0;
//        for (Keyboard.Key key : keys) {
//            if (prev != null && prev.x > key.x) {
//                row++;
//                col = 0;
//            }
//            if (row > 0) {
//                key.height = height;
////                key.codes = new int [ ] { 97 };
//                key.y = menuHeight + (row - 1) * (height + 1);
//            } else {
//                menuHeight = Math.max(menuHeight, key.height);
//            }
//
//            final Option<KeyData> possible = dummy.get(row, col);
//            if (possible.isDefined()) updater.mutate(key, possible.getOrDie());
//
//            col++;
//            prev = key;
//        }
//    }
}
