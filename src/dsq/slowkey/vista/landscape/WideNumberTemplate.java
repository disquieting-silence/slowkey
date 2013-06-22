package dsq.slowkey.vista.landscape;

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

public class WideNumberTemplate implements KeyTemplate {
    private final CommonTemplates common = new DefaultCommonTemplates();

    private final List<List<Option<KeyData>>> keys;

    public WideNumberTemplate(final Context context) {
        this.keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> menu = common.topMenu();
        menu.set(menu.size() - 1, common.codeLabel(SpecialKeyCodes.TO_LETTER, "ALPHA"));
        keys.add(menu);

        // ( ) / * << $  8 9 + pound 4 5 6 - 1/4 1 2 3 +/- 1/2 0 , . 3/4 : enter

        /*
            $  p    7 8 9 Bksp
            (   )   4 5 6 - /
           1/4 1/2 1 2 3 +  *
           3/4 +/-   0 , . Return
         */

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.addAll(common.keyRow(new char[] { '$', '£', '7', '8', '9' }));
        second.add(common.backspaceKey(context, 2));
        second.add(new None<KeyData>());
        keys.add(second);

        keys.add(common.keyRow(new char[] { '(', ')', '4', '5', '6', '-', '/' }));
        keys.add(common.keyRow(new char[] { '¼', '½', '1', '2', '3', '+', '*' }));

        final List<Option<KeyData>> last = new ArrayList<Option<KeyData>>();
        last.addAll(common.keyRow(new char[]{'¾', '±', '0',}));
        last.add(common.spaceKey(context, 1));
        last.addAll(common.keyRow(new char[] { ',', '.'}));
        last.add(common.enterKey(context, 1));
        keys.add(last);
    }
    @Override
    public Option<KeyData> get(final int row, final int column) {
        return common.get(keys, row, column);
    }

    @Override
    public double getRowScale(final int row) {
        return row == 0 ? 0.5 : 1.0;
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
