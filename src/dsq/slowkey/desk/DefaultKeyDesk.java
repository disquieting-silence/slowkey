package dsq.slowkey.desk;

public class DefaultKeyDesk implements KeyDesk {
    private final int rows;
    private final int columns;

    private KeyTemplate template;

    public DefaultKeyDesk(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.columns;
    }

    @Override
    public void update(final KeyTemplate template) {
        this.template = template;
    }
}
