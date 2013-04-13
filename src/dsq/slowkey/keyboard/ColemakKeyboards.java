package dsq.slowkey.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import dsq.slowkey.R;

import java.util.HashMap;
import java.util.Map;

import static dsq.slowkey.keyboard.KeyboardType.*;

public class ColemakKeyboards implements Keyboards {

    private final Map<KeyboardType, Keyboard> mapping = new HashMap<KeyboardType, Keyboard>();

    public ColemakKeyboards(final Context context) {
        final Keyboard symbol = new Keyboard(context, R.xml.symbol);
        final Keyboard number = new Keyboard(context, R.xml.number);
        final Keyboard navigation = new Keyboard(context, R.xml.navigation);
        final Keyboard colemak0 = new Keyboard(context, R.xml.colemak0);
        final Keyboard colemak1 = new Keyboard(context, R.xml.colemak1);
        final Keyboard colemak2 = new Keyboard(context, R.xml.colemak2);
        final Keyboard colemakAlpha = new Keyboard(context, R.xml.colemak_alpha);

        final Keyboard blueprint = new Keyboard(context, R.xml.colemak_blueprint);
        mapping.put(BLUEPRINT, blueprint);
        mapping.put(SYMBOL, symbol);
        mapping.put(NUMBER, number);
        mapping.put(NAVIGATION, navigation);
        mapping.put(COLEMAK0, colemak0);
        mapping.put(COLEMAK1, colemak1);
        mapping.put(COLEMAK2, colemak2);
        mapping.put(COLEMAK_ALPHA, colemakAlpha);
    }
    
    @Override
    public Keyboard get(final KeyboardType type) {
        Keyboard keyboard = mapping.get(type);
        if (keyboard == null) throw new RuntimeException("Unknown keyboard type: " + type.name());
        return keyboard;
    }

    @Override
    public Keyboard first() {
        return get(COLEMAK_ALPHA);
    }
}
