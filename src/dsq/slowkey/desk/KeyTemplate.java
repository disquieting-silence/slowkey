package dsq.slowkey.desk;

import dsq.slowkey.data.Option;

public interface KeyTemplate {
    Option<KeyData> get(int row, int column);

    // FIX: These don't need to be here.
    int numRows();
    int numColumns(int row);
    int maxColumns();
}
