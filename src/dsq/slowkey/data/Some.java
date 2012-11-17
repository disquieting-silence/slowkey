package dsq.slowkey.data;

public class Some<A> implements Option<A> {

    private final A value;

    public Some(final A value) {
        this.value = value;
    }

    @Override
    public A getOrDie() {
        return this.value;
    }

    @Override
    public boolean isDefined() {
        return true;
    }
}
