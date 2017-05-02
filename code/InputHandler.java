import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

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

    public InputHandler(Game g) {
        g.addKeyListener(this);
    }

    public List<Key> keys = new ArrayList<>();

    public Key escape = new Key();
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    private void toggle(KeyEvent e, boolean keyPress) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) { escape.toggle(keyPress); }
        if(e.getKeyCode() == KeyEvent.VK_UP) { up.toggle(keyPress); }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) { down.toggle(keyPress); }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) { left.toggle(keyPress); }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) { right.toggle(keyPress); }
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