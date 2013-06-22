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

public class WideSymbolTemplate implements KeyTemplate {
    private List<List<Option<KeyData>>> keys;
    private final CommonTemplates common = new DefaultCommonTemplates();

    public WideSymbolTemplate(final Context context) {
        keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> first = common.topMenu();
        keys.add(first);

        /*
          MOVE  Left Up Right Down Backspace
          +      -   *    /    ^    %   =
          (      )   [    ]    {    }   \
          <      >   @    #    &    _   ;
          !      $   ?   '    "    :    |
          ~      `   ,   [ space ] .  Enter


          M  +  -  *  /  ^  %  \  =  B--B
          (  )  [  ]  {  }  <  >  ~  @  |
          &  _  ;  !  $  ?  '  "  #  ` ~
          :  L  U  R  D  S--S  ,  .  ...E

         */

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.add(common.codeLabel(SpecialKeyCodes.TO_LETTER, "ALPHA"));
        second.addAll(common.keyRow(new char[] { '+', '-', '*', '/', '^', '%', '\\', '=' }));
        second.add(common.backspaceKey(context, 2));
        second.add(new None<KeyData>());
        keys.add(second);

        keys.add(common.keyRow(new char[] { '(', ')', '[', ']', '{', '}', '<', '>', '~', '@', '|' }));
        keys.add(common.keyRow(new char[] { '&', '_', ';', '!', '$', '?', '\'', '"', '#', '`', '#' }));

        final ArrayList<Option<KeyData>> last = new ArrayList<Option<KeyData>>();
        last.addAll(common.keyRow(new char[] { ':' }));
        last.add(common.codeLabel(SpecialKeyCodes.LEFT, "\u2190", true));
        last.add(common.codeLabel(SpecialKeyCodes.UP, "\u2191", true));
        last.add(common.codeLabel(SpecialKeyCodes.RIGHT, "\u2192", true));
        last.add(common.codeLabel(SpecialKeyCodes.DOWN, "\u2193", true));
        last.add(common.spaceKey(context, 2));
        last.add(new None<KeyData>());
        last.add(common.keyChar(','));
        last.add(common.keyChar('.'));
        last.add(common.keyChar('…'));
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
    public int numColumns(final int row) {
        return common.numColumns(keys, row);
    }

    @Override
    public int maxColumns() {
        return common.maxColumns(keys);
    }
}
