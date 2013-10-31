package dsq.slowkey.vista.portrait;

import android.content.Context;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;
import dsq.slowkey.vista.common.CommonTemplates;
import dsq.slowkey.vista.common.DefaultCommonTemplates;

import java.util.List;

public class ColemakMiddleTemplate implements KeyTemplate {
    private final CommonTemplates common = new DefaultCommonTemplates();
    private final ColemakSections colemak = new DefaultColemakSections();
    private final List<List<Option<KeyData>>> keys;

    public ColemakMiddleTemplate(final Context context) {
        final char[] topRow = new char[]{'e', 'r', 't', 'i', 'o'};
        final char[] bottomRow = { 'a', 's', 'd', 'h', 'n' };
        keys = colemak.generate(context, topRow, SpecialKeyCodes.TO_COLEMAK_TOP, SpecialKeyCodes.TO_COLEMAK_BOTTOM, bottomRow);
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
