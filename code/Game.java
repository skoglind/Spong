import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Spong - A PONG Clone
 * @author Fredrik Skoglind
 */
public class Game extends JFrame {
    private boolean isRunning = false;
    private InputHandler input;
    private BufferedImage backBuffer;
    private Insets insets;

    // Game Settings
    private int gameloopFPS = 60;
    private Color gameBackground = Color.BLACK;
    private Color gameForeground = Color.WHITE;
    private Rectangle screenSize;

    // Game Variables
    private Sprite playerOne;
    private Sprite playerTwo;
    private Rectangle playerOneBoundries;
    private Rectangle playerTwoBoundries;

    /**
     * Initialize Game
     */
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
        getContentPane().setBackground( gameBackground );
        setVisible(true);

        // Get screen data
        screenSize = new Rectangle(0, 0, (int)getSize().getWidth(), (int)getSize().getHeight());

        // Bind Graphics
        insets = getInsets();
        backBuffer = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);

        // Bind keyboard input
        input = new InputHandler(this);

        // Player Settings
        int playerSpeedX = 0;
        int playerSpeedY = 25;
        int playerWidth = 20;
        int playerHeight = 100;
        int playerOneOffsetX = 20;
        int playerTwoOffsetX = 20;

        // Player One
        playerOneBoundries = new Rectangle(playerOneOffsetX, 0, playerWidth, screenSize.height-1);
        playerOne = new Sprite(playerOneOffsetX, ((screenSize.x + screenSize.height)/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);

        // Player Two
        playerTwoBoundries = new Rectangle((screenSize.x + screenSize.width) - (playerTwoOffsetX + playerWidth), 0,
                playerWidth, screenSize.height-1);
        playerTwo = new Sprite((screenSize.x + screenSize.width) - (playerTwoOffsetX + playerWidth),
                ((screenSize.x + screenSize.height)/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);
    }

    /**
     * Run Game
     */
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

    /**
     * Game Logic
     */
    private void logic() {
        // Movement (Player One)
            boolean moveOneUp = false;
            boolean moveOneDown = false;

            if(input.up.keyDown) { moveOneUp = true; }
            if(input.down.keyDown) { moveOneDown = true; }

            if(moveOneUp && !moveOneDown) {
                playerOne.moveUp(playerOneBoundries);
            } else if(moveOneDown && !moveOneUp) {
                playerOne.moveDown(playerOneBoundries);
            }

        // Movement (Player Two)
            boolean moveTwoUp = false;
            boolean moveTwoDown = false;

            if(input.up_w.keyDown) { moveTwoUp = true; }
            if(input.down_s.keyDown) { moveTwoDown = true; }

            if(moveTwoUp && !moveTwoDown) {
                playerTwo.moveUp(playerTwoBoundries);
            } else if(moveTwoDown && !moveTwoUp) {
                playerTwo.moveDown(playerTwoBoundries);
            }
    }

    /**
     * Render Graphics
     */
    private void render() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        // Clear screen
        bbg.setColor(gameBackground);
        bbg.fillRect(screenSize.x, screenSize.y, screenSize.width, screenSize.height);

        // Boundries
        bbg.setColor(gameForeground);
        bbg.drawRect(playerOneBoundries.x, playerOneBoundries.y, playerOneBoundries.width, playerOneBoundries.height);
        bbg.drawRect(playerTwoBoundries.x, playerTwoBoundries.y, playerTwoBoundries.width, playerTwoBoundries.height);

        // Draw Players
        playerOne.Draw(bbg);
        playerTwo.Draw(bbg);

        // Draw buffer to screen
        g.drawImage(backBuffer, insets.left, insets.top, this);
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
