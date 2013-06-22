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
import dsq.slowkey.vista.landscape.WideLetterTemplate;
import dsq.slowkey.vista.portrait.TallLetterTemplate;
import dsq.slowkey.vista.portrait.TallNumberTemplate;
import dsq.slowkey.vista.portrait.TallSymbolTemplate;

import java.util.HashMap;
import java.util.Map;

public class TemplateKeyboards implements Keyboards {
    private final Map<KeyboardType, KeyTemplate> portraits;
    private final Map<KeyboardType, KeyTemplate> landscapes;
    private final AbstractTemplateKeyboard keyboard;
    private final Window window;

    private ScreenMode mode = ScreenMode.PORTRAIT;
    private KeyboardType currentType = KeyboardType.LETTER;

    private double keyScale = 0.09;
    public static final double SCALE_MIN = 0.07;
    public static final double SCALE_MAX = 0.12;
    private KeyTemplate current;

    public TemplateKeyboards(final Window window, final Context context) {
        this.window = window;
        portraits = new HashMap<KeyboardType, KeyTemplate>();
        keyboard = new DefaultTemplateKeyboard(context);

        portraits.put(KeyboardType.LETTER, new TallLetterTemplate(context));
        portraits.put(KeyboardType.SYMBOL, new TallSymbolTemplate(context));
        portraits.put(KeyboardType.NUMBER, new TallNumberTemplate(context));

        landscapes = new HashMap<KeyboardType, KeyTemplate>();
        landscapes.put(KeyboardType.LETTER, new WideLetterTemplate(context));
    }

    @Override
    public Option<Keyboard> get(final KeyboardType type) {
        final Map<KeyboardType, KeyTemplate> templates = mode == ScreenMode.PORTRAIT ? portraits : landscapes;
        final KeyTemplate template = templates.get(type);
        if (template != null) {
            currentType = type;
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
        return get(KeyboardType.LETTER);
    }

    @Override
    public void setScreen(final ScreenMode mode) {
        this.mode = mode;
        // FIX: Sort of a abuse of how it should work. This should return a value, but it mutates as well. Hmm.
        get(currentType);
    }
}
