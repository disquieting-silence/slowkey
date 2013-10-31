package dsq.slowkey.vista.portrait;

import android.content.Context;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;
import dsq.slowkey.vista.common.CommonTemplates;
import dsq.slowkey.vista.common.DefaultCommonTemplates;

import java.util.ArrayList;
import java.util.List;

public class ColemakTopTemplate implements KeyTemplate {
    private final CommonTemplates common = new DefaultCommonTemplates();
    private final List<List<Option<KeyData>>> keys;
    private final ColemakSections colemak = new DefaultColemakSections();

    public ColemakTopTemplate(final Context context) {
        final char[] topRow = new char[]{'q', 'w', 'y', 'u', 'p'};
        final char[] bottomRow = { 'f', 'g', 'j', 'l', ';' };
        final int left = SpecialKeyCodes.TO_COLEMAK_MIDDLE;
        final int right = SpecialKeyCodes.TO_COLEMAK_BOTTOM;
        keys = colemak.generate(context, topRow, left, right, bottomRow);
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
