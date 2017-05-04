import java.awt.*;
import java.util.Random;

/**
 * Controller
 * @author Fredrik Skoglind
 */
public class Controller {
    protected Random rnd;

    protected GraphicsHandler gh;
    protected InputHandler input;

    protected Color gameBackground = Color.BLACK;
    protected Color gameForeground = Color.WHITE;

    public Controller(GraphicsHandler gh, InputHandler input) {
        this.rnd = new Random();
        this.gh = gh;
        this.input = input;
    }

    public void dispose() {
        input.releaseAll();
    }

    public void init() {
        // Nothing
    }

    public void logic() {
        // Nothing
    }

    public void render() {
        // Nothing
    }
}
