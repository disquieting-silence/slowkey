package dsq.slowkey.data;

// Really basic definition, but just don't want nulls.
public interface Option<A> {
    A getOrDie();
    boolean isDefined();
}
