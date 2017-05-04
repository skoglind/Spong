import java.awt.*;
import java.util.Random;

/**
 * Spong - A PONG Clone
 * @author Fredrik Skoglind
 */
public class Game {
    private boolean isRunning = false;
    private InputHandler input;
    private GraphicsHandler gh;
    private Random rnd;

    // Debug Settings
    private boolean showBoundries = false;

    // Game Settings
    private String gameTitle = "Spong";
    private int windowWidth = 800;
    private int windowHeight = 600;
    private int gameloopFPS = 60;
    private Color gameBackground = Color.BLACK;
    private Color gameForeground = Color.WHITE;

    // Game Items
    private Paddle playerOne;
    private Paddle playerTwo;
    private Rectangle playerOneBoundries;
    private Rectangle playerTwoBoundries;
    private Ball ball;
    private Rectangle ballBoundries;

    /**
     * Initialize Game
     */
    private void setupGame() {
        isRunning = true;

        // Graphics
        gh = new GraphicsHandler(gameTitle, windowWidth, windowHeight);
        gh.showWindow();

        // Bind keyboard input
        input = new InputHandler(gh);

        // Pseudo-Random Generator
        rnd = new Random();

        // Start Game
        this.setupPlayers();
        this.resetBall();
    }

    /**
     * Players
     */
    private void setupPlayers() {
        // Player Settings
        int playerSpeedX = 0;
        int playerSpeedY = 30;
        int playerWidth = 20;
        int playerHeight = 100;
        int playerOneOffsetX = 20;
        int playerTwoOffsetX = 20;

        // Player One
        playerOneBoundries = new Rectangle(playerOneOffsetX, 0, playerWidth, gh.getScreenSize().height-1);
        playerOne = new Paddle(playerOneOffsetX, (gh.getScreenSize().height/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);

        // Player Two
        playerTwoBoundries = new Rectangle(gh.getScreenSize().width - (playerTwoOffsetX + playerWidth), 0,
                playerWidth, gh.getScreenSize().height-1);
        playerTwo = new Paddle(gh.getScreenSize().width - (playerTwoOffsetX + playerWidth),
                (gh.getScreenSize().height/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);
    }

    /**
     * Ball
     */
    private void resetBall() {
        // Ball
        int ballSpeed = 10;
        int ballSize = 20;
        double velocityIncrease = 1.2;
        int maxBallSpeed = 50;

        int direction = rnd.nextInt(360);
        System.out.println("BALL DIRECTION: " + direction);

        ballBoundries = new Rectangle(gh.getScreenSize().x, gh.getScreenSize().y, gh.getScreenSize().width-1, gh.getScreenSize().height-1);
        ball = new Ball((gh.getScreenSize().width/2)-(ballSize/2), (gh.getScreenSize().height/2)-(ballSize/2),
                ballSpeed, ballSpeed, ballSize, ballSize, velocityIncrease, maxBallSpeed);
        ball.setDirection(direction);
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

            // Show Debug info
            if(input.debug.keyDown) {
                showBoundries = true;
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

            if(input.up_p1.keyDown) { moveOneUp = true; }
            if(input.down_p1.keyDown) { moveOneDown = true; }

            if(moveOneUp && !moveOneDown) {
                System.out.println("UP_P1");
                playerOne.moveUp(playerOneBoundries);
            } else if(moveOneDown && !moveOneUp) {
                System.out.println("DOWN_P1");
                playerOne.moveDown(playerOneBoundries);
            }

        // Movement (Player Two)
            boolean moveTwoUp = false;
            boolean moveTwoDown = false;

            if(input.up_p2.keyDown) { moveTwoUp = true; }
            if(input.down_p2.keyDown) { moveTwoDown = true; }

            if(moveTwoUp && !moveTwoDown) {
                System.out.println("UP_P2");
                playerTwo.moveUp(playerTwoBoundries);
            } else if(moveTwoDown && !moveTwoUp) {
                System.out.println("DOWN_P2");
                playerTwo.moveDown(playerTwoBoundries);
            }

        // Movement (Ball)
            if(!ball.updatePosition(ballBoundries)) {
                this.resetBall();
            }
    }

    /**
     * Render Graphics
     */
    private void render() {
        Graphics canvas = gh.backBuffer();

            // Clear screen
            canvas.setColor(gameBackground);
            canvas.fillRect(0, 0, gh.getScreenSize().width, gh.getScreenSize().height);

            // Net
            canvas.setColor(gameForeground);
            canvas.fillRect((gh.getScreenSize().width/2)-4, 20, 8, gh.getScreenSize().height-40);

            // Boundries
            if(showBoundries) {
                canvas.setColor(Color.RED);
                canvas.drawRect(playerOneBoundries.x, playerOneBoundries.y, playerOneBoundries.width, playerOneBoundries.height);
                canvas.drawRect(playerTwoBoundries.x, playerTwoBoundries.y, playerTwoBoundries.width, playerTwoBoundries.height);
                canvas.setColor(Color.YELLOW);
                canvas.drawRect(ballBoundries.x, ballBoundries.y, ballBoundries.width, ballBoundries.height);
            }

            // Draw Players
            playerOne.Draw(canvas);
            playerTwo.Draw(canvas);

            // Draw Ball
            ball.Draw(canvas);

        // Draw backbuffer to screen
        gh.render();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
