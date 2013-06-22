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
        /* Tall template
        Mv  :
          ( )
         /


        [Shift]  [Space] . enter
         */


        /* Wide template
            q w e r t y u i o p [Bksp]
            a s d f g h j k l ; ' ?
            [Mv] z x c v b n m , . ?
            [Shift] : ( [Space] ) / [Ret]
         */

        this.keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> first = common.topMenu();
        keys.add(first);

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.addAll(common.keyRow(new char[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' }));
        second.add(common.backspaceKey(context, 2));
        second.add(new None<KeyData>());
        keys.add(second);

        final List<Option<KeyData>> third = common.keyRow(new char[] { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\''});
        keys.add(third);

        final List<Option<KeyData>> fourth = common.keyRow(new char[] { '(', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', ')' });
        keys.add(fourth);

        final List<Option<KeyData>> fifth = new ArrayList<Option<KeyData>>();
        fifth.add(common.shiftKey(context, 2));
        fifth.add(new None<KeyData>());
        fifth.add(common.codeLabel(SpecialKeyCodes.TO_SYMBOL, "MOVE"));
        fifth.add(common.keyChar(':'));
        fifth.add(common.spaceKey(context, 3));
        fifth.add(new None<KeyData>());
        fifth.add(new None<KeyData>());
        fifth.add(common.keyChar('/'));
        fifth.add(new None<KeyData>());
        fifth.add(new None<KeyData>());
        fifth.add(common.enterKey(context, 1));
        keys.add(fifth);
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
