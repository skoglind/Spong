import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Game - A PONG Clone
 * @author Fredrik Skoglind
 */
public class Game extends JFrame {
    private boolean isRunning = false;
    private InputHandler input;

    // Game Settings
    private int gameloopFPS = 20;

    private void setupGame() {
        isRunning = true;

        // JFrame - Setup
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        }

        // Hide mousecursor
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        getContentPane().setCursor(blankCursor);

        // Show JFrame window
        setVisible(true);

        // Bind keyboard input
        input = new InputHandler(this);
    }

    private void playGame() {
        setupGame();

        while(isRunning) {
            if(input.escape.keyDown) { isRunning = false; }

            logic();
            render();

            try { Thread.sleep(gameloopFPS); } catch(Exception e) { }
        }

        System.exit(0);
    }

    private void logic() {
        // TODO
    }

    private void render() {
        // TODO
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
