package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {

    public final int UP_KEY = KeyEvent.VK_UP;
    public final int DOWN_KEY = KeyEvent.VK_DOWN;
    public final int RIGHT_KEY = KeyEvent.VK_RIGHT;
    public final int LEFT_KEY = KeyEvent.VK_LEFT;
    public final int SHIFT_KEY = KeyEvent.VK_SHIFT;

    public int keyPressed = ' ';

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        keyPressed = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        keyPressed = ' ';
    }

    public void resetKeyPressed() {
        keyPressed = ' ';
    }
}
