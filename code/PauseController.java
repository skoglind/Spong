import java.awt.*;

/**
 * MenuController
 * @author Fredrik Skoglind
 */
public class PauseController extends Controller {
    public PauseController(Game game, GraphicsHandler gh, InputHandler input) {
        super(game, gh, input);
    }

    public void dispose() {
        super.dispose();
    }

    public void init() {
        dispose();
    }

    public void logic() {
        // Stop Game
        if (input.escape.keyDown || input.pause.keyDown) {
            input.releaseAll();
            game.setState(GameState.GAME, false);
        }
    }

    public void render() {
        Graphics canvas = gh.backBuffer();

        // Clear screen
        canvas.setColor(gameBackground);
        canvas.fillRect(0, 0, gh.getScreenSize().width, gh.getScreenSize().height);

        // PAUSE
        canvas.setFont(headerFont);
        canvas.setColor(gameForeground);
        canvas.drawString("Pause...", gh.getScreenSize().width/2 - 80, gh.getScreenSize().height/2);

        // Draw backbuffer to screen
        gh.render();
    }
}
