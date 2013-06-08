package dsq.slowkey.vista;

import android.content.Context;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;

import java.util.ArrayList;
import java.util.List;

public class MegaNumberTemplate implements KeyTemplate {
    private final List<List<Option<KeyData>>> keys;
    private final CommonTemplates common = new DefaultCommonTemplates();

    public MegaNumberTemplate(final Context context) {
        keys = new ArrayList<List<Option<KeyData>>>();
        
        keys.add(common.topMenu());

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
        sixth.addAll(common.keyRow(new char[] { ',', '.' }));
        keys.add(sixth);

        final List<Option<KeyData>> last = new ArrayList<Option<KeyData>>();
        last.add(common.keyChar('¾'));
        last.add(common.spaceKey(context, 2));
        last.add(common.keyChar(':'));
        last.add(common.enterKey(context, 1));
        keys.add(last);
    }

    @Override
    public Option<KeyData> get(final int row, final int column) {
        return common.get(keys, row, column);
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
