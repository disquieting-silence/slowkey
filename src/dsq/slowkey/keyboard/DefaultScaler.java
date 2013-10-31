package dsq.slowkey.keyboard;

public class DefaultScaler implements Scaler {

    private final double min;
    private final double max;
    private final double step;
    private double value = 0.2;

    public DefaultScaler(final double min, final double max, final double value, final double step) {
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = value;
    }

    @Override
    public void grow() {
        value = Math.min(max, value + step);
    }

    @Override
    public void shrink() {
        value = Math.max(min, value - step);
    }

    @Override
    public double get() {
        return value;
    }

    @Override
    public void growMax() {
        value = max;
    }

    @Override
    public void shrinkMax() {
        value = min;
    }
}
