package dsq.slowkey.data;

public class None<A> implements Option<A> {
    @Override
    public A getOrDie() {
        throw new RuntimeException("getOrDie called on none.");
    }

    @Override
    public boolean isDefined() {
        return false;
    }
}
