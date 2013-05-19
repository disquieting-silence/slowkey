package dsq.slowkey.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.view.Window;
import dsq.slowkey.R;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;

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
        final Keyboard colemakAlpha = new DefaultDynamicKeyboard(context, R.xml.colemak_alpha);
        final Keyboard megaSymbols = new DefaultDynamicKeyboard(context, R.xml.mega_symbols);

        final Keyboard blueprint = new Keyboard(context, R.xml.colemak_blueprint);
        mapping.put(BLUEPRINT, blueprint);
        mapping.put(SYMBOL, symbol);
        mapping.put(NUMBER, number);
        mapping.put(NAVIGATION, navigation);
        mapping.put(COLEMAK0, colemak0);
        mapping.put(COLEMAK1, colemak1);
        mapping.put(COLEMAK2, colemak2);
        mapping.put(COLEMAK_ALPHA, colemakAlpha);
        mapping.put(MEGA_SYMBOLS, megaSymbols);
    }
    
    @Override
    public Option<Keyboard> get(final KeyboardType type) {
        Keyboard keyboard = mapping.get(type);
        if (keyboard == null) return new None<Keyboard>();
        return new Some<Keyboard>(keyboard);
    }

    @Override
    public Option<Keyboard> first() {
        return get(MEGA_SYMBOLS);
    }
}
