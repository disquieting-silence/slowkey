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

import static dsq.slowkey.action.SpecialKeyCodes.KEYBOARD_GROW;
import static dsq.slowkey.action.SpecialKeyCodes.KEYBOARD_SHRINK;

public class ScaleKeyboardTemplate implements KeyTemplate {
    private final CommonTemplates common = new DefaultCommonTemplates();

    private final List<List<Option<KeyData>>> keys;

    public ScaleKeyboardTemplate(final Context context) {
        this.keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> first = common.topMenu();
        keys.add(first);

        keys.addAll(Arrays.asList(
            row(SpecialKeyCodes.KEYBOARD_GROW, "+", SpecialKeyCodes.KEYBOARD_SHRINK, "-"),
            row(SpecialKeyCodes.KEYBOARD_MAX, "MAX", SpecialKeyCodes.KEYBOARD_MIN, "MIN")
        ));
    }

    private List<Option<KeyData>> row(final int code1, final String label1, final int code2, final String label2) {
        final List<Option<KeyData>> r = new ArrayList<Option<KeyData>>();
        r.add(common.codeLabel(code1, label1));
        r.add(common.codeLabel(code2, label2));
        return r;
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
