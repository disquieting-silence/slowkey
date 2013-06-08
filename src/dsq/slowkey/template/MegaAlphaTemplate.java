package dsq.slowkey.template;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import dsq.slowkey.R;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;

import static dsq.slowkey.action.SpecialKeyCodes.*;

public class MegaAlphaTemplate implements KeyTemplate {
    /*
     <Row android:keyHeight="6%p" android:keyWidth="20%p">
        <Key android:codes="-20000" android:keyLabel="&gt;-&lt;"/>
        <Key android:codes="-21000" android:keyLabel="&lt;-&gt;"/>
        <Key android:codes="-3" android:keyLabel="HIDE"/>
        <Key android:codes="-17000" android:keyLabel="INPUT"/>
        <Key android:codes="-10000" android:keyLabel="0…9"/>
    </Row>
    <Row>
        <Key android:codes="-16000" android:keyLabel="MOVE" android:keyEdgeFlags="left"/>
        <Key android:codes="122" android:keyLabel="z"/>
        <Key android:codes="120" android:keyLabel="x"/>
        <Key android:codes="113" android:keyLabel="q"/>
        <Key android:codes="58" android:keyLabel=":"/>
        <Key android:codes="-5" android:keyIcon="@drawable/sym_keyboard_delete" android:keyWidth="28.4%p" android:isRepeatable="true" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="108" android:keyLabel="l" android:keyEdgeFlags="left"/>
        <Key android:codes="119" android:keyLabel="w"/>
        <Key android:codes="121" android:keyLabel="y"/>
        <Key android:codes="117" android:keyLabel="u"/>
        <Key android:codes="112" android:keyLabel="p"/>
        <Key android:codes="40" android:keyLabel="(" />
        <Key android:codes="41" android:keyLabel=")" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="102" android:keyLabel="f" android:keyEdgeFlags="left"/>
        <Key android:codes="101" android:keyLabel="e"/>
        <Key android:codes="114" android:keyLabel="r"/>
        <Key android:codes="116" android:keyLabel="t"/>
        <Key android:codes="105" android:keyLabel="i"/>
        <Key android:codes="111" android:keyLabel="o"/>
        <Key android:codes="47" android:keyLabel="/" android:keyEdgeFlags="right" />
    </Row>
    <Row>
        <Key android:codes="109" android:keyLabel="m" android:keyEdgeFlags="left"/>
        <Key android:codes="97" android:keyLabel="a"/>
        <Key android:codes="115" android:keyLabel="s"/>
        <Key android:codes="100" android:keyLabel="d"/>
        <Key android:codes="104" android:keyLabel="h"/>
        <Key android:codes="110" android:keyLabel="n"/>
        <Key android:codes="39" android:keyLabel="\'" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="106" android:keyLabel="j" android:keyEdgeFlags="left"/>
        <Key android:codes="99" android:keyLabel="c"/>
        <Key android:codes="107" android:keyLabel="k"/>
        <Key android:codes="98" android:keyLabel="b"/>
        <Key android:codes="118" android:keyLabel="v"/>
        <Key android:codes="103" android:keyLabel="g"/>
        <Key android:codes="63" android:keyLabel="\?" android:keyEdgeFlags="right"/>
    </Row>
    <Row>
        <Key android:codes="-1" android:keyIcon="@drawable/sym_keyboard_shift" android:keyWidth="28.4%p" android:isModifier="true" android:isSticky="true" android:keyEdgeFlags="left"/>
        <Key android:codes="44" android:keyLabel=","/>
        <Key android:codes="32" android:keyIcon="@drawable/sym_keyboard_space" android:keyWidth="28.4%p" android:isRepeatable="true"/>
        <Key android:codes="46" android:keyLabel="."/>
        <Key android:codes="10" android:keyIcon="@drawable/sym_keyboard_return" android:keyEdgeFlags="right"/>
    </Row>
     */


    private static final int NUM_ROWS = 7;
    private static final int NUM_COLUMNS = 7;
    private final KeyData[][] keys;

//    <Row android:keyHeight="6%p" android:keyWidth="20%p">
//    <Key android:codes="-20000" android:keyLabel="&gt;-&lt;"/>
//    <Key android:codes="-21000" android:keyLabel="&lt;-&gt;"/>
//    <Key android:codes="-3" android:keyLabel="HIDE"/>
//    <Key android:codes="-17000" android:keyLabel="INPUT"/>
//    <Key android:codes="-10000" android:keyLabel="0…9"/>
//    </Row>

//    <Row>
//    <Key android:codes="-16000" android:keyLabel="MOVE" android:keyEdgeFlags="left"/>
//    <Key android:codes="122" android:keyLabel="z"/>
//    <Key android:codes="120" android:keyLabel="x"/>
//    <Key android:codes="113" android:keyLabel="q"/>
//    <Key android:codes="58" android:keyLabel=":"/>
//    <Key android:codes="-5" android:keyIcon="@drawable/sym_keyboard_delete" android:keyWidth="28.4%p" android:isRepeatable="true" android:keyEdgeFlags="right"/>
//    </Row>
//

    public MegaAlphaTemplate(final Context context) {
        this.keys = new KeyData[][]{
            new KeyData[] {
                new KeyData(KEYBOARD_GROW, "<->"),
                new KeyData(KEYBOARD_SHRINK, ">-<"),
                new KeyData(Keyboard.KEYCODE_CANCEL, "HIDE"),
                new KeyData(CHANGE_INPUT, "INPUT"),
                new KeyData(TO_NUMBER, "0…9")
            },

            new KeyData[] {
                new KeyData(TO_MEGA_SYMBOLS, "MOVE"),
                new KeyData('z'),
                new KeyData('x'),
                new KeyData('q'),
                new KeyData(':'),
                new KeyData(Keyboard.KEYCODE_DELETE, context.getResources().getDrawable(R.drawable.sym_keyboard_delete))
            }
        };
    }

    @Override
    public Option<KeyData> get(final int row, final int column) {
        if (keys.length == 0) return new None<KeyData>();
        if (row >= 0 && row < keys.length && column >= 0 && column < keys[row].length) {
            return new Some<KeyData>(keys[row][column]);
        }
        return new Some<KeyData>(new KeyData('?'));
    }
}
