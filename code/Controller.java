import java.awt.*;
import java.util.Random;

/**
 * Controller
 * @author Fredrik Skoglind
 */
public class Controller {
    protected Random rnd;

    protected Game game;
    protected GraphicsHandler gh;
    protected InputHandler input;
    protected AudioHandler sound;

    protected Color gameBackground = Color.BLACK;
    protected Color gameForeground = Color.WHITE;
    protected Color scoreColor = Color.WHITE;
    protected Color buttonColor = Color.WHITE;
    protected Color activeButtonColor = Color.YELLOW;

    protected Font headerFont = new Font("Arial", Font.BOLD, 56);
    protected Font buttonFont = new Font("Arial", Font.BOLD, 38);
    protected Font scoreFont = new Font("Arial", Font.BOLD, 70);

    public Controller(Game game, GraphicsHandler gh, InputHandler input, AudioHandler sound) {
        this.game = game;
        this.rnd = new Random();
        this.gh = gh;
        this.input = input;
        this.sound = sound;
    }

    public void dispose() {
        input.releaseAll();
    }

    public void init() {
    }

    public void logic() {
    }

    public void render() {
    }
}
