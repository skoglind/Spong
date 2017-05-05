/**
 * Spong - A PONG Clone
 * @author Fredrik Skoglind
 */
public class Game {
    private boolean isRunning = false;
    private InputHandler input;
    private GraphicsHandler gh;
    public GameState state;

    // Game Settings
    public static String gameTitle = "Spong";
    private int windowWidth = 800;
    private int windowHeight = 600;
    private int gameloopFPS = 60;

    // Controllers
    private GameController gc;
    private MenuController menu;
    private PauseController pause;

    private void init() {
        isRunning = true;

        // GraphicsHandler
        gh = new GraphicsHandler(gameTitle, windowWidth, windowHeight);
        gh.showWindow();

        // InputHandler
        input = new InputHandler(gh);

        // Controllers
        gc = new GameController(gh, input);
        menu = new MenuController(gh, input);
        pause = new PauseController(gh, input);

        // Startstate
        state = GameState.MENU;
        //gc.init();
        menu.init();
    }

    private void run() {
        init();

        while(isRunning) {
            if(input.escape.keyDown) {
                isRunning = false;
                System.exit(0);
            }

            switch(state) {
                case MENU:
                    menu.logic();
                    menu.render();
                    break;
                case GAME:
                    gc.logic();
                    gc.render();
                    break;
                case PAUSE:
                    pause.logic();
                    pause.render();
                    break;
            }

            try { Thread.sleep(1000/gameloopFPS); } catch(Exception e) { }
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}
