package dsq.slowkey.template;

import android.view.Window;

public interface WindowMetrics {
    int widthPx(final Window window, final double percent);
    int heightPx(final Window window, final double percent);
}
