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
    private Graphics g;

    // Game Settings
    private int gameloopFPS = 60;
    private Color gameBackgroundColor = Color.BLACK;

    // Game Variables
    private Sprite player;

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
        getContentPane().setBackground( gameBackgroundColor );
        setVisible(true);

        // Bind Graphics
        g = this.getGraphics();

        // Bind keyboard input
        input = new InputHandler(this);

        // Game Variables
        player = new Sprite(20, 10, 0, 10, 20, 100);
    }

    private void playGame() {
        setupGame();

        while(isRunning) {
            if(input.escape.keyDown) { isRunning = false; }

            logic();
            render();

            try { Thread.sleep(1000/gameloopFPS); } catch(Exception e) { }
        }

        System.exit(0);
    }

    private void logic() {
        boolean moveUp = false;
        boolean moveDown = false;

        if(input.up.keyDown) { moveUp = true; }
        if(input.down.keyDown) { moveDown = true; }

        int boundryTop = 10 + 5;
        int boundryBottom = (int)getSize().getHeight() - (10 + player.getHeight() + 5);
        if(moveUp && !moveDown) {
            if(player.getPositionY() >= boundryTop) {
                player.moveUp();
            }
        } else if(moveDown && !moveUp) {
            if(player.getPositionY() <= boundryBottom) {
                player.moveDown();
            }
        }
    }

    private void render() {
        // Clear screen
        g.setColor(gameBackgroundColor);
        g.fillRect(0, 0, (int)getSize().getWidth(), (int)getSize().getHeight());

        // Boundries
        g.setColor(Color.WHITE);
        g.drawRect(10, 10, (int)getSize().getWidth() - 20, (int)getSize().getHeight() - 20);

        // Draw sprites
        g.setColor(Color.WHITE);
        player.Draw(g);
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
