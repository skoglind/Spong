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

        // Hide mousecursor
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        getContentPane().setCursor(blankCursor);

        // Show JFrame window
        setTitle("Spong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800,600);
        getContentPane().setBackground( Color.YELLOW );
        setLocationRelativeTo(null);
        setVisible(true);

        // Get screen data
        insets = getInsets();

        // Get screen data
        screenSize = new Rectangle(0, 0,
                (int)getContentPane().getSize().getWidth(),
                (int)getContentPane().getSize().getHeight());

        // Bind Graphics
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
        playerOne = new Sprite(playerOneOffsetX, (screenSize.height/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);

        // Player Two
        playerTwoBoundries = new Rectangle(screenSize.width - (playerTwoOffsetX + playerWidth), 0,
                playerWidth, screenSize.height-1);
        playerTwo = new Sprite(screenSize.width - (playerTwoOffsetX + playerWidth),
                (screenSize.height/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);
    }

    /**
     * Run Game
     */
    private void playGame() {
        setupGame();

        while(isRunning) {
            if(input.escape.keyDown) {
                System.out.println("ESCAPE");
                isRunning = false;
                System.exit(0);
            }

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
                System.out.println("UP");
                playerOne.moveUp(playerOneBoundries);
            } else if(moveOneDown && !moveOneUp) {
                System.out.println("DOWN");
                playerOne.moveDown(playerOneBoundries);
            }

        // Movement (Player Two)
            boolean moveTwoUp = false;
            boolean moveTwoDown = false;

            if(input.up_w.keyDown) { moveTwoUp = true; }
            if(input.down_s.keyDown) { moveTwoDown = true; }

            if(moveTwoUp && !moveTwoDown) {
                System.out.println("UP_W");
                playerTwo.moveUp(playerTwoBoundries);
            } else if(moveTwoDown && !moveTwoUp) {
                System.out.println("DOWN_S");
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
        bbg.fillRect(0, 0, screenSize.width, screenSize.height);

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
