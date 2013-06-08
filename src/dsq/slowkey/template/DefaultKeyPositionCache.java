package dsq.slowkey.template;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;

import java.util.List;

public class DefaultKeyPositionCache implements KeyPositionCache {

    int[][] cache;

    @Override
    public Option<Integer> find(final int x, final int y) {
        return x >= 0 && x < cache.length && y >= 0 && y < cache[x].length ? new Some<Integer>(cache[x][y]) : new None<Integer>();
    }

    @Override
    public void update(final List<Keyboard.Key> keys, int width, int height) {
        cache = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cache[i][j] = nearest(keys, i, j);
            }
        }
    }

    private int nearest(final List<Keyboard.Key> keys, final int x, final int y) {
        int result = -1;
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < keys.size(); i++) {
            Keyboard.Key key = keys.get(i);
            final int distance = key.squaredDistanceFrom(x, y);
            if (distance < best) {
                best = distance;
                result = i;
            }
        }
        return result;
    }
}
