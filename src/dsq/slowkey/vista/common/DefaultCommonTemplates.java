package dsq.slowkey.vista.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import dsq.slowkey.R;
import dsq.slowkey.action.SpecialKeyCodes;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;
import dsq.slowkey.desk.KeyData;

import java.util.ArrayList;
import java.util.List;

import static android.inputmethodservice.Keyboard.KEYCODE_DELETE;
import static android.inputmethodservice.Keyboard.KEYCODE_SHIFT;
//import static dsq.slowkey.R.drawable.*;
import static dsq.slowkey.action.SpecialKeyCodes.KEYBOARD_GROW;
import static dsq.slowkey.action.SpecialKeyCodes.KEYBOARD_SHRINK;

public class DefaultCommonTemplates implements CommonTemplates {
    private Option<String> noLabel = new None<String>();
    private Option<String> noText = new None<String>();
    private Option<Integer> noCode = new None<Integer>();
    private Option<Drawable> noIcon = new None<Drawable>();

    private Option<Drawable> image(final Context context, final int id) {
        return new Some<Drawable>(context.getResources().getDrawable(id));
    }

    private Option<Integer> keycode(int code) {
        return new Some<Integer>(code);
    }

    private Option<KeyData> special(final Context context, final int code, final int id, final int span, final boolean modifier, final boolean sticky, final boolean repeatable) {
        return new Some<KeyData>(
                new KeyData(keycode(code), noText, image(context, id), noLabel, span, modifier, sticky, repeatable)
        );
    }

    @Override
    public Option<KeyData> backspaceKey(final Context context, final int colspan) {
        return special(context, KEYCODE_DELETE, R.drawable.sym_keyboard_delete, colspan, false, false, true);
    }

    @Override
    public Option<KeyData> shiftKey(final Context context, final int colspan) {
        return special(context, KEYCODE_SHIFT, R.drawable.new_shift, colspan, true, true, false);
    }

    @Override
    public Option<KeyData> spaceKey(final Context context, final int colspan) {
        return special(context, 32, R.drawable.sym_keyboard_space, colspan, false, false, true);
    }

    @Override
    public Option<KeyData> enterKey(final Context context, final int colspan) {
        return special(context, 10, R.drawable.sym_keyboard_return, colspan, false, false, false);
    }

    @Override
    public int maxColumns(final List<List<Option<KeyData>>> keys) {
        int maxColumns = 0;
        for (final List<Option<KeyData>> row : keys) {
            maxColumns = Math.max(maxColumns, row.size());
        }
        return maxColumns;
    }

    @Override
    public Option<KeyData> get(final List<List<Option<KeyData>>> keys, final int row, final int column) {
        if (keys.size() == 0) return new None<KeyData>();
        if (row >= 0 && row < keys.size() && column >= 0 && column < keys.get(row).size()) {
            return keys.get(row).get(column);
        }
        return new None<KeyData>();
    }

    @Override
    public int numColumns(final List<List<Option<KeyData>>> keys, int row) {
        return row >= 0 && row < keys.size() ? keys.get(row).size() : 0;
    }

    @Override
    public Some<KeyData> codeLabel(final int code, final String label) {
        return new Some<KeyData>(new KeyData(code, label));
    }

    @Override
    public Some<KeyData> codeLabel(final int code, final String label, final boolean isRepeatable) {
        return new Some<KeyData>(new KeyData(code, label, isRepeatable));
    }

    @Override
    public List<Option<KeyData>> topMenu() {
        final List<Option<KeyData>> menu = new ArrayList<Option<KeyData>>();
        menu.add(codeLabel(KEYBOARD_GROW, "<->"));
        menu.add(codeLabel(KEYBOARD_SHRINK, ">-<"));
        menu.add(codeLabel(Keyboard.KEYCODE_CANCEL, "HIDE"));
        menu.add(codeLabel(SpecialKeyCodes.TO_KEYBOARD_LIST, "\u21d3"));
        //menu.add(codeLabel(SpecialKeyCodes.CHANGE_INPUT, "INPUT"));
        menu.add(codeLabel(SpecialKeyCodes.TO_NUMBER, "0…9"));

        return menu;
    }

    @Override
    public List<Option<KeyData>> keyRow(final char[] chars) {
        final List<Option<KeyData>> result = new ArrayList<Option<KeyData>>();
        for (final char aChar : chars) {
            result.add(keyChar(aChar));
        }
        return result;
    }

    @Override
    public Option<KeyData> keyChar(final char character) {
        return new Some<KeyData>(new KeyData(character));
    }

    @Override
    public Option<KeyData> keyCharSpan(final char character, final int span) {
        final KeyData data = new KeyData(
            new Some<Integer>((int) character),
            new Some<String>(String.valueOf(character)),
            new None<Drawable>(),
            new Some<String>(String.valueOf(character)),
            span,
            false, false, false
        );
        return new Some<KeyData>(data);
    }

    @Override
    public int numRows(final List<List<Option<KeyData>>> keys) {
        return keys.size();
    }


}
