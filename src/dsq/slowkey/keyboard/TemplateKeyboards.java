package dsq.slowkey.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.view.Window;
import dsq.slowkey.data.None;
import dsq.slowkey.data.Option;
import dsq.slowkey.data.Some;
import dsq.slowkey.desk.KeyTemplate;
import dsq.slowkey.template.AbstractTemplateKeyboard;
import dsq.slowkey.template.DefaultTemplateKeyboard;
import dsq.slowkey.vista.MegaAlphaTemplate;
import dsq.slowkey.vista.MegaNumberTemplate;
import dsq.slowkey.vista.MegaSymbolTemplate;

import java.util.HashMap;
import java.util.Map;

public class TemplateKeyboards implements Keyboards {
    private final Map<KeyboardType, KeyTemplate> templates;
    private final AbstractTemplateKeyboard keyboard;
    private final Window window;

    private double keyScale = 0.09;
    public static final double SCALE_MIN = 0.07;
    public static final double SCALE_MAX = 0.12;
    private KeyTemplate current;

    public TemplateKeyboards(final Window window, final Context context) {
        this.window = window;
        templates = new HashMap<KeyboardType, KeyTemplate>();
        keyboard = new DefaultTemplateKeyboard(context);

        templates.put(KeyboardType.COLEMAK_ALPHA, new MegaAlphaTemplate(context));
        templates.put(KeyboardType.MEGA_SYMBOLS, new MegaSymbolTemplate(context));
        templates.put(KeyboardType.NUMBER, new MegaNumberTemplate(context));

    }
    @Override
    public Option<Keyboard> get(final KeyboardType type) {
        final KeyTemplate template = templates.get(type);
        if (template != null) {
            current = template;
            update();
            return new Some<Keyboard>(keyboard);
        } else {
            return new None<Keyboard>();
        }
    }

    private void update() {
        keyboard.update(window, keyScale, current);
    }

    @Override
    public void adjustHeight(final double percent) {
        keyScale = Math.max(SCALE_MIN, Math.min(SCALE_MAX, keyScale + percent));
        update();
    }

    @Override
    public Option<Keyboard> first() {
        return get(KeyboardType.COLEMAK_ALPHA);
    }
}
