package dsq.slowkey.desk;

import dsq.slowkey.data.Option;

public interface KeyTemplate {
    Option<KeyData> get(int row, int column);

    // FIX: These don't need to be here.
    double getRowScale(int row);
    int numRows();
    int numColumns(int row);
    int maxColumns();
}
