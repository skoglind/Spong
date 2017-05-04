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

    // Game Settings
    private String gameTitle = "Spong";
    private int windowWidth = 800;
    private int windowHeight = 600;
    private int gameloopFPS = 60;

    // Gamestates
    private GameController gc;

    /**
     * Initialize Game
     */
    private void init() {
        isRunning = true;

        // GraphicsHandler
        gh = new GraphicsHandler(gameTitle, windowWidth, windowHeight);
        gh.showWindow();

        // InputHandler
        input = new InputHandler(gh);

        // Controllers
        gc = new GameController(gh, input);
    }

    /**
     * Run Game
     */
    private void run() {
        init();

        while(isRunning) {
            if(input.escape.keyDown) {
                System.out.println("ESCAPE");
                isRunning = false;
                System.exit(0);
            }

            gc.logic();
            gc.render();

            try { Thread.sleep(1000/gameloopFPS); } catch(Exception e) { }
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}
