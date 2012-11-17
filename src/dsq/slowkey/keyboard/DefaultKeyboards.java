package dsq.slowkey.keyboard;

import android.inputmethodservice.Keyboard;

import java.util.HashMap;
import java.util.Map;

import static dsq.slowkey.keyboard.KeyboardType.*;

public class DefaultKeyboards implements Keyboards {
    
    private final Map<KeyboardType, Keyboard> mapping = new HashMap<KeyboardType, Keyboard>();
    
    public DefaultKeyboards(
        final Keyboard a1,
        final Keyboard a2,
        final Keyboard a3,
        final Keyboard a4,
        final Keyboard b1,
        final Keyboard b2,
        final Keyboard b3,
        final Keyboard b4,
        final Keyboard blueprint,
        final Keyboard binary
    ) {
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
