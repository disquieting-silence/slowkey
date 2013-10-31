package dsq.slowkey.keyboard;

public interface Scaler {
    void grow();
    void shrink();
    double get();

    void growMax();
    void shrinkMax();
}
