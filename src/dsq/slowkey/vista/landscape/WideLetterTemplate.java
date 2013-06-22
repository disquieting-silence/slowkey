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

public class WideLetterTemplate implements KeyTemplate {
    private final CommonTemplates common = new DefaultCommonTemplates();

    private final List<List<Option<KeyData>>> keys;

    public WideLetterTemplate(final Context context) {
        this.keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> first = common.topMenu();
        keys.add(first);

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.add(common.codeLabel(SpecialKeyCodes.TO_SYMBOL, "MOVE"));
        second.addAll(common.keyRow(new char[]{'z', 'x', 'q', ':', 'w', 'l', 'y', 'u', 'p', '(', ')'}));
        second.add(common.backspaceKey(context, 2));
        second.add(new None<KeyData>());
        keys.add(second);

//        final List<Option<KeyData>> third = common.keyRow(new char[]{'l', 'w', 'y', 'u', 'p', '(', ')'});
//        keys.add(third);
//
//        final List<Option<KeyData>> fourth = common.keyRow(new char[]{'f', 'e', 'r', 't', 'i', 'o', '/'});
//        keys.add(fourth);
//
//        final List<Option<KeyData>> fifth = common.keyRow(new char[]{'m', 'a', 's', 'd', 'h', 'n', '\''});
//        keys.add(fifth);
//
//        final List<Option<KeyData>> sixth = common.keyRow(new char[]{'j', 'c', 'k', 'b', 'v', 'g', '?'});
//        keys.add(sixth);
//
//        final ArrayList<Option<KeyData>> seventh = new ArrayList<Option<KeyData>>();
//        seventh.add(common.shiftKey(context, 2));
//        seventh.add(new None<KeyData>());
//        seventh.add(common.keyChar(','));
//        seventh.add(common.spaceKey(context, 2));
//        seventh.add(new None<KeyData>());
//        seventh.add(common.keyChar('.'));
//        seventh.add(common.enterKey(context, 1));
//        keys.add(seventh);
    }


    @Override
    public Option<KeyData> get(final int row, final int column) {
        return common.get(keys, row, column);
    }

    @Override
    public int getRowHeight(final int row) {
        return 1;
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
