import java.awt.*;

/**
 * MenuController
 * @author Fredrik Skoglind
 */
public class PauseController extends Controller {
    public PauseController(GraphicsHandler gh, InputHandler input) {
        super(gh, input);
    }

    public void dispose() {
        super.dispose();
    }

    public void init() {
        dispose();
    }

    public void logic() {
        // Nothing
    }

    public void render() {
        Graphics canvas = gh.backBuffer();

        // Clear screen
        canvas.setColor(gameBackground);
        canvas.fillRect(0, 0, gh.getScreenSize().width, gh.getScreenSize().height);

        // PAUSE

        // Draw backbuffer to screen
        gh.render();
    }
}
