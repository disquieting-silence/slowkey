package dsq.slowkey.template;

import android.util.DisplayMetrics;
import android.view.Window;

public class DefaultWindowMetrics implements WindowMetrics {
    public int heightPx(final Window window, final double percent) {
        final DisplayMetrics metrics = metrics(window);
        return (int)(percent * metrics.heightPixels);
    }

    public int widthPx(final Window window, final double percent) {
        final DisplayMetrics metrics = metrics(window);
        return (int)(percent * metrics.widthPixels);
    }

    private DisplayMetrics metrics(final Window window) {
        final DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
