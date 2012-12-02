package dsq.slowkey.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import dsq.slowkey.R;

import java.util.HashMap;
import java.util.Map;

import static dsq.slowkey.keyboard.KeyboardType.*;

public class QwertyKeyboards implements Keyboards {
    
    private final Map<KeyboardType, Keyboard> mapping = new HashMap<KeyboardType, Keyboard>();
    
    public QwertyKeyboards(final Context context) {
        final Keyboard binary = new Keyboard(context, R.xml.binary);
        final Keyboard a1 = new Keyboard(context, R.xml.a1);
        final Keyboard a2 = new Keyboard(context, R.xml.a2);
        final Keyboard a3 = new Keyboard(context, R.xml.a3);
        final Keyboard a4 = new Keyboard(context, R.xml.a4);
        final Keyboard b1 = new Keyboard(context, R.xml.b1);
        final Keyboard b2 = new Keyboard(context, R.xml.b2);
        final Keyboard b3 = new Keyboard(context, R.xml.b3);
        final Keyboard b4 = new Keyboard(context, R.xml.b4);
        final Keyboard symbol = new Keyboard(context, R.xml.symbol);
        final Keyboard number = new Keyboard(context, R.xml.number);
        final Keyboard navigation = new Keyboard(context, R.xml.navigation);
        final Keyboard blueprint = new Keyboard(context, R.xml.blueprint);

        mapping.put(A1, a1);
        mapping.put(A2, a2);
        mapping.put(A3, a3);
        mapping.put(A4, a4);
        mapping.put(B1, b1);
        mapping.put(B2, b2);
        mapping.put(B3, b3);
        mapping.put(B4, b4);
        mapping.put(BINARY, binary);
        mapping.put(BLUEPRINT, blueprint);
        mapping.put(SYMBOL, symbol);
        mapping.put(NUMBER, number);
        mapping.put(NAVIGATION, navigation);
    }
    
    @Override
    public Keyboard get(final KeyboardType type) {
        Keyboard keyboard = mapping.get(type);
        if (keyboard == null) throw new RuntimeException("Unknown keyboard type: " + type.name());
        return keyboard;
    }

    @Override
    public Keyboard first() {
        return get(BLUEPRINT);
    }
}
