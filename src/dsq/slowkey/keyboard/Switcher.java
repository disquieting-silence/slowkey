package dsq.slowkey.keyboard;

public interface Switcher {
    void toKeyboard(KeyboardType type);
    void toggleShifted();

    boolean isShifted();
    void shrink();
    void grow();

    void setScreen(ScreenMode mode);
}
