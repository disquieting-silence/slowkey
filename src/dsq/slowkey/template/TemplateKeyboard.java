package dsq.slowkey.template;

import android.view.Window;
import dsq.slowkey.desk.KeyTemplate;

public interface TemplateKeyboard extends PublicKeyboard {
    void update(final Window window, final double height, final KeyTemplate template);
    void portrait();
    void landscape();
}
