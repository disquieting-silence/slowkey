package dsq.slowkey.template;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.view.Window;
import dsq.slowkey.desk.KeyTemplate;

import java.util.List;

public abstract class AbstractTemplateKeyboard extends NoopKeyboard implements TemplateKeyboard {
    public AbstractTemplateKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    @Override
    public abstract void update(final Window window, final double height, final KeyTemplate template);
}
