package dsq.slowkey.vista.portrait;

import android.content.Context;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;
import dsq.slowkey.vista.common.CommonTemplates;
import dsq.slowkey.vista.common.DefaultCommonTemplates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardListTemplate implements KeyTemplate {
    private final CommonTemplates common = new DefaultCommonTemplates();

    private final List<List<Option<KeyData>>> keys;

    public KeyboardListTemplate(final Context context) {
        this.keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> first = common.topMenu();
        keys.add(first);

        keys.addAll(Arrays.asList(
                row(SpecialKeyCodes.TO_SYMBOL, "SYMBOLS"),
                row(SpecialKeyCodes.TO_LETTER, "ALPHABET"),
                row(SpecialKeyCodes.TO_NUMBER, "NUMBERS"),
                row(SpecialKeyCodes.TO_COLEMAK_MIDDLE, "COMPRESSED"),
                row(SpecialKeyCodes.TO_INTERNATIONAL, "INTERNATIONAL"),
                row(SpecialKeyCodes.CHANGE_INPUT, "PICKER")
        ));
    }

    private List<Option<KeyData>> row(final int code, final String label) {
        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.add(common.codeLabel(code, label));
        return second;
    }

    @Override
    public Option<KeyData> get(final int row, final int column) {
        return common.get(keys, row, column);
    }

    @Override
    public double getRowScale(final int row) {
        return row == 0 ? 0.5 : 1;
    }

    @Override
    public int numRows() {
        return common.numRows(keys);
    }

    @Override
    public int numColumns(int row) {
        return common.numColumns(keys, row);
    }

    @Override
    public int maxColumns() {
        return common.maxColumns(keys);
    }
}
