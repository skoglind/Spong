/**
 * Spong - A PONG Clone
 * @author Fredrik Skoglind
 */
public class Game {
    private boolean isRunning = false;

    private InputHandler input;
    private GraphicsHandler gh;
    private AudioHandler sound;

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

    public void setState(GameState state, boolean doInit) {
        this.state = state;
        switch(state) {
            case MENU:
                if(doInit) { menu.init(); }
                break;
            case GAME:
                if(doInit) { gc.init(); }
                break;
            case PAUSE:
                if(doInit) { pause.init(); }
                break;
        }
    }

    public GameState getState() {
        return state;
    }

    private void init() {
        isRunning = true;

        // GraphicsHandler
        gh = new GraphicsHandler(gameTitle, windowWidth, windowHeight);
        gh.showWindow();

        // InputHandler
        input = new InputHandler(gh);

        // SoundHandler
        sound = new AudioHandler();

        // Controllers
        gc = new GameController(this, gh, input, sound);
        menu = new MenuController(this, gh, input, sound);
        pause = new PauseController(this, gh, input, sound);

        // Startstate
        setState(GameState.MENU, true);
    }

    public void quit() {
        isRunning = false;
        System.exit(0);
    }

    private void run() {
        init();

        while(isRunning) {
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
