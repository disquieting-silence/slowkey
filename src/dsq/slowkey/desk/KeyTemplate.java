package dsq.slowkey.desk;

import dsq.slowkey.data.Option;

public interface KeyTemplate {
    Option<KeyData> get(int row, int column);
    int numRows();
    int numColumns(int row);
}
