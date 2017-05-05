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
    protected Color buttonColor = Color.WHITE;
    protected Color activeButtonColor = Color.YELLOW;

    protected Font headerFont = new Font("Arial", Font.BOLD, 56);
    protected Font buttonFont = new Font("Arial", Font.BOLD, 38);

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
