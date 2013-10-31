package dsq.slowkey.keyboard;

public interface Switcher {
    void toKeyboard(KeyboardType type);
    void toggleShifted();

    boolean isShifted();
    void shrink();
    void grow();

    void growMax();
    void shrinkMax();

    void setScreen(ScreenMode mode);
}
