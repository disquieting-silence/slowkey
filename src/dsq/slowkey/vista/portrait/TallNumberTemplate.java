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

public class TallNumberTemplate implements KeyTemplate {
    private final List<List<Option<KeyData>>> keys;
    private final CommonTemplates common = new DefaultCommonTemplates();

    public TallNumberTemplate(final Context context) {
        keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> menu = common.topMenu();
        menu.set(menu.size() - 1, common.codeLabel(SpecialKeyCodes.TO_LETTER, "ALPHA"));
        keys.add(menu);

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.addAll(common.keyRow(new char[] { '(', ')', '/', '*' }));
        second.add(common.backspaceKey(context, 1));
        keys.add(second);

        keys.add(common.keyRow(new char[] { '$', '7', '8', '9', '+' }));
        keys.add(common.keyRow(new char[] { '£', '4', '5', '6', '-' }));
        keys.add(common.keyRow(new char[] { '¼', '1', '2', '3', '±' }));

        final List<Option<KeyData>> sixth = new ArrayList<Option<KeyData>>();
        sixth.add(common.keyChar('½'));
        sixth.add(common.keyCharSpan('0', 2));
        sixth.add(new None<KeyData>());
        sixth.addAll(common.keyRow(new char[] { ',', '.' }));
        keys.add(sixth);

        final List<Option<KeyData>> last = new ArrayList<Option<KeyData>>();
        last.add(common.keyChar('¾'));
        last.add(common.spaceKey(context, 2));
        last.add(new None<KeyData>());
        last.add(common.keyChar(':'));
        last.add(common.enterKey(context, 1));
        keys.add(last);
    }

    @Override
    public Option<KeyData> get(final int row, final int column) {
        return common.get(keys, row, column);
    }

    @Override
    public double getRowScale(final int row) {
        return 1;
    }

    @Override
    public int numRows() {
        return common.numRows(keys);
    }

    @Override
    public int numColumns(final int row) {
        return common.numColumns(keys, row);
    }

    @Override
    public int maxColumns() {
        return common.maxColumns(keys);
    }
}
