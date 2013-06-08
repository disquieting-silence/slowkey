package dsq.slowkey.desk;

import android.inputmethodservice.Keyboard;

public class DefaultKeyUpdater implements KeyUpdater {
    @Override
    public void mutate(final Keyboard.Key key, final KeyData data) {
        if (data.code.isDefined()) key.codes = new int[] { data.code.getOrDie() };
        if (data.image.isDefined()) key.icon = data.image.getOrDie();
        if (data.text.isDefined()) key.text = data.text.getOrDie();
        if (data.label.isDefined()) key.label = data.label.getOrDie();
    }
}
