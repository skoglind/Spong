import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * InputHandler Class
 *
 * Creds to Notch (MiniCraft) for this class.
 *
 * @author Fredrik Skoglind
 */
public class InputHandler implements KeyListener {
    public class Key {
        public boolean keyDown, keyUp;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean keyPress) {
            if(keyPress != keyDown) {
                keyDown = keyPress;
                keyUp = !keyDown;
            }
        }
    }

    public InputHandler(GraphicsHandler g) {
        g.addKeyListener(this);
    }

    public List<Key> keys = new ArrayList<>();

    public Key escape = new Key();
    public Key debug = new Key();

    public Key up_p1 = new Key();
    public Key down_p1 = new Key();

    public Key up_p2 = new Key();
    public Key down_p2 = new Key();

    public void releaseAll() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).keyDown = false;
        }
    }

    private void toggle(KeyEvent e, boolean keyPress) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) { escape.toggle(keyPress); }

        if(e.getKeyCode() == KeyEvent.VK_UP) { up_p1.toggle(keyPress); }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) { down_p1.toggle(keyPress); }

        if(e.getKeyCode() == KeyEvent.VK_1) { up_p2.toggle(keyPress); }
        if(e.getKeyCode() == KeyEvent.VK_Q) { down_p2.toggle(keyPress); }

        if(e.getKeyCode() == KeyEvent.VK_0) { debug.toggle(keyPress); }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
