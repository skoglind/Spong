import java.awt.*;
import java.util.Random;

/**
 * GameController
 * @author Fredrik Skoglind
 */
public class GameController extends Controller {
    public boolean showBoundries = false;

    private Paddle playerOne;
    private Paddle playerTwo;
    private Rectangle playerOneBoundries;
    private Rectangle playerTwoBoundries;
    private Ball ball;
    private Rectangle ballBoundries;

    public GameController(GraphicsHandler gh, InputHandler input) {
        super(gh, input);
    }

    public void init() {
        dispose();
        this.setupPlayers();
        this.setupBall();
    }

    public void dispose() {
        super.dispose();

        playerOne = null;
        playerTwo = null;
        playerOneBoundries = null;
        playerTwoBoundries = null;
        ball = null;
        ballBoundries = null;
        showBoundries = false;
    }

    public void logic() {
        // Show Debug info
        if(input.debug.keyDown) {
            showBoundries = true;
        }

        // Movement (Player One)
        boolean moveOneUp = false;
        boolean moveOneDown = false;

        if(input.up_p1.keyDown) { moveOneUp = true; }
        if(input.down_p1.keyDown) { moveOneDown = true; }

        if(moveOneUp && !moveOneDown) {
            playerOne.moveUp(playerOneBoundries);
        } else if(moveOneDown && !moveOneUp) {
            playerOne.moveDown(playerOneBoundries);
        }

        // Movement (Player Two)
        boolean moveTwoUp = false;
        boolean moveTwoDown = false;

        if(input.up_p2.keyDown) { moveTwoUp = true; }
        if(input.down_p2.keyDown) { moveTwoDown = true; }

        if(moveTwoUp && !moveTwoDown) {
            playerTwo.moveUp(playerTwoBoundries);
        } else if(moveTwoDown && !moveTwoUp) {
            playerTwo.moveDown(playerTwoBoundries);
        }

        // Movement (Ball)
        if(!ball.updatePosition(ballBoundries)) {
            this.setupBall();
        }
    }

    public void render() {
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

    private void setupPlayers() {
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

    private void setupBall() {
        int ballSpeed = 10;
        int ballSize = 20;
        double velocityIncrease = 1.2;
        int maxBallSpeed = 50;

        int direction = rnd.nextInt(360);

        ballBoundries = new Rectangle(gh.getScreenSize().x, gh.getScreenSize().y, gh.getScreenSize().width-1, gh.getScreenSize().height-1);
        ball = new Ball((gh.getScreenSize().width/2)-(ballSize/2), (gh.getScreenSize().height/2)-(ballSize/2),
                ballSpeed, ballSpeed, ballSize, ballSize, velocityIncrease, maxBallSpeed);
        ball.setDirection(direction);
    }
}
