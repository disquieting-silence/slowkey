package dsq.slowkey.vista;

import android.content.Context;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;
import dsq.slowkey.desk.KeyData;

import java.util.List;

public interface CommonTemplates {
    Option<KeyData> backspaceKey(Context context, final int colspan);

    Option<KeyData> shiftKey(Context context, final int colspan);

    Option<KeyData> spaceKey(Context context, final int colspan);

    Option<KeyData> enterKey(Context context, final int colspan);

    int maxColumns(List<List<Option<KeyData>>> keys);

    int numColumns(List<List<Option<KeyData>>> keys, int row);

    Option<KeyData> get(List<List<Option<KeyData>>> keys, int row, int column);

    Some<KeyData> codeLabel(int code, String label);

    List<Option<KeyData>> topMenu();

    List<Option<KeyData>> keyRow(char[] chars);

    Option<KeyData> keyChar(char character);

    int numRows(List<List<Option<KeyData>>> keys);
}
