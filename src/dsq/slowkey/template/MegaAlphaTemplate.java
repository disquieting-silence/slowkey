package dsq.slowkey.template;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import dsq.slowkey.R;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;
import dsq.slowkey.desk.KeyData;
import dsq.slowkey.desk.KeyTemplate;

import java.util.ArrayList;
import java.util.List;

import static dsq.slowkey.action.SpecialKeyCodes.KEYBOARD_GROW;
import static dsq.slowkey.action.SpecialKeyCodes.KEYBOARD_SHRINK;

public class MegaAlphaTemplate implements KeyTemplate {
    private static Option<KeyData> backspaceKey(final Context context) {
        return new Some<KeyData>(new KeyData(
            new Some<Integer>(Keyboard.KEYCODE_DELETE),
            new None<String>(),
            new Some<Drawable>(context.getResources().getDrawable(R.drawable.sym_keyboard_delete)),
            new None<String>(),
            2,
            false,
            false,
            true
        ));
    }

    private static Option<KeyData> shiftKey(final Context context) {
        return new Some<KeyData>(new KeyData(
            new Some<Integer>(Keyboard.KEYCODE_SHIFT),
            new None<String>(),
            new Some<Drawable>(context.getResources().getDrawable(R.drawable.sym_keyboard_shift)),
            new None<String>(),
            2,
            true,
            true,
            false
        ));
    }

    private static Option<KeyData> spaceKey(final Context context) {
        return new Some<KeyData>(new KeyData(
            new Some<Integer>(32),
            new None<String>(),
            new Some<Drawable>(context.getResources().getDrawable(R.drawable.sym_keyboard_space)),
            new None<String>(),
            2,
            false,
            false,
            true
        ));
    }

    private static Option<KeyData> enterKey(final Context context) {
        return new Some<KeyData>(new KeyData(
            new Some<Integer>(10),
            new None<String>(),
            new Some<Drawable>(context.getResources().getDrawable(R.drawable.sym_keyboard_return)),
            new None<String>(),
            1,
            false,
            false,
            false
        ));
    }

    private final List<List<Option<KeyData>>> keys;

    private static List<Option<KeyData>> keyRow(final char[] chars) {
        final List<Option<KeyData>> result = new ArrayList<Option<KeyData>>();
        for (final char aChar : chars) {
            result.add(keyChar(aChar));
        }
        return result;
    }

    private static Option<KeyData> keyChar(final char character) {
        return new Some<KeyData>(new KeyData(character));
    }

    private List<Option<KeyData>> menu() {
        final List<Option<KeyData>> first = new ArrayList<Option<KeyData>>();
        first.add(codeLabel(KEYBOARD_GROW, "<->"));
        first.add(codeLabel(KEYBOARD_SHRINK, ">-<"));
        first.add(codeLabel(Keyboard.KEYCODE_CANCEL, "HIDE"));
        first.add(codeLabel(SpecialKeyCodes.CHANGE_INPUT, "INPUT"));
        first.add(codeLabel(SpecialKeyCodes.TO_NUMBER,"0…9"));
        return first;
    }

    public MegaAlphaTemplate(final Context context) {
        this.keys = new ArrayList<List<Option<KeyData>>>();

        final List<Option<KeyData>> first = menu();
        keys.add(first);

        final List<Option<KeyData>> second = new ArrayList<Option<KeyData>>();
        second.add(codeLabel(SpecialKeyCodes.TO_MEGA_SYMBOLS, "MOVE"));
        second.addAll(keyRow(new char[]{'z', 'x', 'q', ':'}));
        second.add(backspaceKey(context));
        second.add(new None<KeyData>());
        keys.add(second);

        final List<Option<KeyData>> third = keyRow(new char[]{'l', 'w', 'y', 'u', 'p', '(', ')'});
        keys.add(third);

        final List<Option<KeyData>> fourth = keyRow(new char[]{'f', 'e', 'r', 't', 'i', 'o', '/'});
        keys.add(fourth);

        final List<Option<KeyData>> fifth = keyRow(new char[]{'m', 'a', 's', 'd', 'h', 'n', '\''});
        keys.add(fifth);

        final List<Option<KeyData>> sixth = keyRow(new char[]{'j', 'c', 'k', 'b', 'v', 'g', '?'});
        keys.add(sixth);

        final ArrayList<Option<KeyData>> seventh = new ArrayList<Option<KeyData>>();
        seventh.add(shiftKey(context));
        seventh.add(new None<KeyData>());
        seventh.add(keyChar(','));
        seventh.add(spaceKey(context));
        seventh.add(new None<KeyData>());
        seventh.add(keyChar('.'));
        seventh.add(enterKey(context));
        keys.add(seventh);
    }


    private Some<KeyData> codeLabel(final int code, final String label) {
        return new Some<KeyData>(new KeyData(code, label));
    }

    @Override
    public Option<KeyData> get(final int row, final int column) {
        if (keys.size() == 0) return new None<KeyData>();
        if (row >= 0 && row < keys.size() && column >= 0 && column < keys.get(row).size()) {
            return keys.get(row).get(column);
        }
        return new None<KeyData>();
    }

    @Override
    public int numRows() {
        return keys.size();
    }

    @Override
    public int numColumns(int row) {
        return row >= 0 && row < keys.size() ? keys.get(row).size() : 0;
    }

    @Override
    public int maxColumns() {
        int maxColumns = 0;
        for (final List<Option<KeyData>> row : keys) {
            maxColumns = Math.max(maxColumns, row.size());
        }
        return maxColumns;
    }
}
