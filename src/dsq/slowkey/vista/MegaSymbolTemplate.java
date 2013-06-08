package dsq.slowkey.vista;

import android.content.Context;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;

import java.util.ArrayList;
import java.util.List;

public class MegaSymbolTemplate implements KeyTemplate {
    /*
    <Row android:keyHeight="6%p" android:keyWidth="20%p">
        <Key android:codes="-20000" android:keyLabel="&gt;-&lt;"/>
        <Key android:codes="-21000" android:keyLabel="&lt;-&gt;"/>
        <Key android:codes="-3" android:keyLabel="HIDE"/>
        <Key android:codes="-17000" android:keyLabel="INPUT"/>
        <Key android:codes="-10000" android:keyLabel="0…9"/>
    </Row>
    <Row>
        <Key android:codes="-15000" android:keyLabel="ALPHA" android:keyEdgeFlags="left"/>
        <Key android:codes="-1100" android:keyLabel="\u2190" android:isRepeatable="true" />
        <Key android:codes="-1102" android:keyLabel="\u2191" android:isRepeatable="true" />
        <Key android:codes="-1101" android:keyLabel="\u2192" android:isRepeatable="true" />
        <Key android:codes="-1103" android:keyLabel="\u2193" android:isRepeatable="true" />
        <Key android:codes="-5" android:keyIcon="@drawable/sym_keyboard_delete" android:keyWidth="28.4%p" android:isRepeatable="true" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="43" android:keyLabel="+" android:keyEdgeFlags="left"/>
        <Key android:codes="45" android:keyLabel="-"/>
        <Key android:codes="42" android:keyLabel="*"/>
        <Key android:codes="47" android:keyLabel="/"/>
        <Key android:codes="94" android:keyLabel="^"/>
        <Key android:codes="37" android:keyLabel="%" />
        <Key android:codes="61" android:keyLabel="=" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="40" android:keyLabel="(" android:keyEdgeFlags="left"/>
        <Key android:codes="41" android:keyLabel=")"/>
        <Key android:codes="91" android:keyLabel="["/>
        <Key android:codes="93" android:keyLabel="]"/>
        <Key android:codes="123" android:keyLabel="{"/>
        <Key android:codes="125" android:keyLabel="}"/>
        <Key  android:codes="92" android:keyLabel="\\" android:keyEdgeFlags="right" />
    </Row>
    <Row>
        <Key android:codes="60" android:keyLabel="&lt;" android:keyEdgeFlags="left"/>
        <Key android:codes="62" android:keyLabel="&gt;"/>
        <Key android:codes="64" android:keyLabel="\@"/>
        <Key android:codes="35" android:keyLabel="#"/>
        <Key android:codes="38" android:keyLabel="&amp;"/>
        <Key android:codes="95" android:keyLabel="_"/>
        <Key android:codes="59" android:keyLabel=";" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="33" android:keyLabel="!" android:keyEdgeFlags="left"/>
        <Key android:codes="36" android:keyLabel="$"/>
        <Key android:codes="63" android:keyLabel="\?"/>
        <Key android:codes="39" android:keyLabel="\'"/>
        <Key android:codes="34" android:keyLabel="&quot;"/>
        <Key android:codes="58" android:keyLabel=":"/>
        <Key android:codes="124" android:keyLabel="|" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="126" android:keyLabel="~" android:keyEdgeFlags="left"/>
        <Key android:codes="96" android:keyLabel="`"/>
        <Key android:codes="44" android:keyLabel=","/>
        <Key android:codes="32" android:keyIcon="@drawable/sym_keyboard_space" android:keyWidth="28.4%p" android:isRepeatable="true"/>
        <Key android:codes="46" android:keyLabel="."/>
        <Key android:codes="10" android:keyIcon="@drawable/sym_keyboard_return" android:keyEdgeFlags="right"/>
    </Row>
     */
    private List<List<Option<KeyData>>> keys;
    private final CommonTemplates common = new DefaultCommonTemplates();

    public MegaSymbolTemplate(final Context context) {
        keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> first = common.topMenu();
        keys.add(first);

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.add(common.codeLabel(SpecialKeyCodes.TO_COLEMAK_ALPHA, "ALPHA"));
        second.add(common.codeLabel(SpecialKeyCodes.LEFT, "\u2190"));
        second.add(common.codeLabel(SpecialKeyCodes.UP, "\u2191"));
        second.add(common.codeLabel(SpecialKeyCodes.RIGHT, "\u2192"));
        second.add(common.codeLabel(SpecialKeyCodes.DOWN, "\u2193"));
        second.add(common.backspaceKey(context, 2));
        second.add(new None<KeyData>());
        keys.add(second);

        keys.add(common.keyRow(new char[] { '+', '-', '*', '/', '^', '%', '=' }));
        keys.add(common.keyRow(new char[] { '(', ')', '[', ']', '{', '}', '\\'}));
        keys.add(common.keyRow(new char[] { '<', '>', '@', '#', '&', '_', ';' }));
        keys.add(common.keyRow(new char[] { '!', '$', '?', '\'', '"', ':', '|' }));

        final ArrayList<Option<KeyData>> last = new ArrayList<Option<KeyData>>();
        last.addAll(common.keyRow(new char[] { '~', '`', ',' }));
        last.add(common.spaceKey(context, 2));
        last.add(common.keyChar('.'));
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
