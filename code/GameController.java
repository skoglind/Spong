import javax.sound.midi.Sequencer;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * GameController
 * @author Fredrik Skoglind
 */
public class GameController extends Controller {
    public SpriteSheet spritesheetGame;
    public boolean showBoundries = false;

    private Paddle playerOne;
    private Paddle playerTwo;
    private Rectangle playerOneBoundries;
    private Rectangle playerTwoBoundries;
    private Ball ball;
    private Rectangle ballBoundries;

    // Graphics
    private BufferedImage imgPlayer;
    private BufferedImage imgBall;

    // Scorecount
    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    // Cooldowns
    private int ballCollideCoolDown = 0;
    private int keyPressCoolDown_Debug = 0;

    // Spritesheets
    private String gameSpritesheet = "spritesheet/game.png";

    // Sounds
    private String ballBounce = "sound/bounce.wav";
    private String looseBall = "sound/loose.wav";
    private String bgmusic = "sound/bgmusic.mid";

    private Sequencer musicPlayer;

    public GameController(Game game, GraphicsHandler gh, InputHandler input, AudioHandler sound) {
        super(game, gh, input, sound);
    }

    public void init() {
        dispose();

        // Load sprites
        spritesheetGame = new SpriteSheet(gameSpritesheet);
        imgPlayer = spritesheetGame.getImage(0, 0, 20, 100);
        imgBall = spritesheetGame.getImage(20, 0, 20, 20);

        musicPlayer = sound.playMusic(bgmusic);

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
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    public void logic() {
        // Stop Game
        if (input.escape.keyDown) {
            input.releaseAll();
            musicPlayer.stop();
            game.setState(GameState.MENU, true);
        }

        // Pause Game
        if (input.pause.keyDown) {
            input.releaseAll();
            game.setState(GameState.PAUSE, true);
        }

        // Show Debug info
        keyPressCoolDown_Debug = keyPressCoolDown_Debug - 1;
        if(keyPressCoolDown_Debug <= 0) {
            keyPressCoolDown_Debug = 0;
            if (input.debug.keyDown) {
                keyPressCoolDown_Debug = 15;
                if (showBoundries) {
                    showBoundries = false;
                } else {
                    showBoundries = true;
                }
            }
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
        int ballStatus = ball.updatePosition(ballBoundries);
        switch(ballStatus) {
            case 1:
                playerTwoScore++;
                this.setupBall();
                sound.playSound(looseBall, 0);
                break;
            case 2:
                playerOneScore++;
                this.setupBall();
                sound.playSound(looseBall, 0);
                break;
            case 3:
                sound.playSound(ballBounce, 0);
                break;
        }

        // Collision Checks
        ballCollideCoolDown = ballCollideCoolDown - 1;
        if(ballCollideCoolDown <= 0) {
            ballCollideCoolDown = 0;

            if (ball.collide(playerOne)) {
                ballCollideCoolDown = 20;
                ball.setDirection(ball.bounceDirection(playerOne, 1));
                sound.playSound(ballBounce, 0);
            }

            if (ball.collide(playerTwo)) {
                ballCollideCoolDown = 20;
                ball.setDirection(ball.bounceDirection(playerTwo, 2));
                sound.playSound(ballBounce, 0);
            }
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

        // Draw Ball
        ball.Draw(canvas);

        // Draw Players
        playerOne.Draw(canvas);
        playerTwo.Draw(canvas);

        // Score
        canvas.setFont(scoreFont);
        canvas.setColor(scoreColor);

        int scoreXOffset = 80;
        int leftScoreOffset = 100;
        int rightScoreOffset = 50;
        if(playerOneScore > 9) { leftScoreOffset = 135; }
        if(playerOneScore > 99) { leftScoreOffset = 170; }
        if(playerOneScore > 999) { leftScoreOffset = 205; }
        canvas.drawString(Integer.toString(playerOneScore), (gh.getScreenSize().width / 2) - leftScoreOffset, scoreXOffset);
        canvas.drawString(Integer.toString(playerTwoScore), (gh.getScreenSize().width / 2) + rightScoreOffset, scoreXOffset);

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
        playerOne = new Paddle(gh, imgPlayer, playerOneOffsetX, (gh.getScreenSize().height/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);

        // Player Two
        playerTwoBoundries = new Rectangle(gh.getScreenSize().width - (playerTwoOffsetX + playerWidth), 0,
                playerWidth, gh.getScreenSize().height-1);
        playerTwo = new Paddle(gh, imgPlayer, gh.getScreenSize().width - (playerTwoOffsetX + playerWidth),
                (gh.getScreenSize().height/2)-(playerHeight/2),
                playerSpeedX, playerSpeedY, playerWidth, playerHeight);
    }

    private void setupBall() {
        int ballSpeed = 15;
        int ballSize = 20;
        double velocityIncrease = 1.1;
        int maxBallSpeed = 40;

        // Start direction
        int direction = rnd.nextInt(360);

        ballBoundries = new Rectangle(gh.getScreenSize().x, gh.getScreenSize().y, gh.getScreenSize().width-1, gh.getScreenSize().height-1);
        ball = new Ball(gh, imgBall, (gh.getScreenSize().width/2)-(ballSize/2), (gh.getScreenSize().height/2)-(ballSize/2),
                ballSpeed, ballSpeed, ballSize, ballSize, velocityIncrease, maxBallSpeed);
        ball.setDirection(direction);
    }
}
