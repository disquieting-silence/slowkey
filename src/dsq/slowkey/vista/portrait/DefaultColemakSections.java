package dsq.slowkey.vista.portrait;

import android.content.Context;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.vista.common.CommonTemplates;
import dsq.slowkey.vista.common.DefaultCommonTemplates;

import java.util.ArrayList;
import java.util.List;

public class DefaultColemakSections implements ColemakSections {
    private final CommonTemplates common = new DefaultCommonTemplates();

    @Override
    public List<List<Option<KeyData>>> generate(final Context context, final char[] topRow, final int left, final int right, final char[] bottomRow) {
        final List<List<Option<KeyData>>> keys = new ArrayList<List<Option<KeyData>>>();
        final List<Option<KeyData>> first = common.topMenu();
        keys.add(first);

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.add(common.backspaceKey(context, 1));
        second.addAll(common.keyRow(topRow));
        second.add(common.backspaceKey(context, 1));
        keys.add(second);

        final ArrayList<Option<KeyData>> third = new ArrayList<Option<KeyData>>();
        third.add(common.codeLabel(left, "<<"));
        third.addAll(common.keyRow(bottomRow));
        third.add(common.codeLabel(right, ">>"));
        keys.add(third);

        final ArrayList<Option<KeyData>> fourth = new ArrayList<Option<KeyData>>();
        fourth.add(common.shiftKey(context, 2));
        fourth.add(new None<KeyData>());
        fourth.add(common.keyChar(','));
        fourth.add(common.spaceKey(context, 2));
        fourth.add(new None<KeyData>());
        fourth.add(common.keyChar('.'));
        fourth.add(common.enterKey(context, 1));
        keys.add(fourth);

        return keys;
    }
}
