package dsq.slowkey.action;

import dsq.slowkey.api.SlowInputMethodService;
import dsq.slowkey.view.SlowKeyboardView;

public class ErrorKeyAction implements KeyAction {
    private final String message;

    public ErrorKeyAction(final String message) {
        this.message = message;
    }

    @Override
    public void run(final SlowInputMethodService service, final SlowKeyboardView view) {
        throw new RuntimeException(message);
    }
}
