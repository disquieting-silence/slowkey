package dsq.slowkey.template;

import android.inputmethodservice.Keyboard;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;

import java.util.List;

public class DefaultKeyPositionCache implements KeyPositionCache {

    int[][] cache;
    private int unitWidth = 0;
    private int unitHeight = 0;

    @Override
    public Option<Integer> find(final int x, final int y) {
        final Position cell = toGrid(x, y);
        return cell.x >= 0 && cell.x < cache.length && cell.y >= 0 && cell.y < cache[cell.x].length ?
            new Some<Integer>(cache[cell.x][cell.y]) : new None<Integer>();
    }

    private static class Position {
        public final int x;
        public final int y;

        private Position(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Position toXY(int row, int column) {
        final int x = column * unitWidth + (unitWidth / 2);
        final int y = row * unitHeight + (unitHeight / 2);
        return new Position(x, y);
    }

    private Position toGrid(int x, int y) {
        final int row = y / unitHeight;
        final int column = x / unitWidth;
        return new Position(column, row);
    }

    @Override
    public void update(final List<Keyboard.Key> keys, int rows, int columns, int unitWidth, int unitHeight) {
        this.unitWidth = unitWidth;
        this.unitHeight = unitHeight;
        cache = new int[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final Position position = toXY(i, j);
                cache[j][i] = nearest(keys, position.x, position.y);
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
