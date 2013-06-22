package dsq.slowkey.desk;

import android.inputmethodservice.Keyboard;

public class DefaultKeyUpdater implements KeyUpdater {
    @Override
    public void mutate(final Keyboard.Key key, final KeyData data) {
        key.codes = data.code.isDefined() ? new int [] { data.code.getOrDie() } : new int [0];
        if (data.image.isDefined()) key.icon = data.image.getOrDie();
        if (data.text.isDefined()) key.text = data.text.getOrDie();
        if (data.label.isDefined()) key.label = data.label.getOrDie();

        key.repeatable = data.isRepeatable;
        key.sticky = data.isSticky;
        key.modifier = data.isModifier;
    }
}
