import java.awt.*;
import java.util.ArrayList;

/**
 * MenuController
 * @author Fredrik Skoglind
 */
public class MenuController extends Controller {
    enum ButtonAction {
        PLAY,
        QUIT
    }

    class MenuButton {
        boolean selected;
        String name;
        ButtonAction action;

        MenuButton(String name, ButtonAction action) {
            this.name = name;
            this.action = action;
        }
    }

    private ArrayList<MenuButton> menuButtons = new ArrayList<>();

    public MenuController(GraphicsHandler gh, InputHandler input) {
        super(gh, input);
    }

    public void dispose() {
        super.dispose();
    }

    public void init() {
        dispose();

        menuButtons.add(new MenuButton("Play", ButtonAction.PLAY));
        menuButtons.add(new MenuButton("Quit", ButtonAction.QUIT));

        menuButtons.get(0).selected = true;
    }

    public void logic() {
        // Nothing
    }

    public void render() {
        Graphics canvas = gh.backBuffer();

        // Clear screen
        canvas.setColor(gameBackground);
        canvas.fillRect(0, 0, gh.getScreenSize().width, gh.getScreenSize().height);

        // Header
        canvas.setColor(gameForeground);
        canvas.setFont(headerFont);
        canvas.drawString(Game.gameTitle, gh.getScreenSize().width/2 - 100, 80);

        //Menu Items
        canvas.setFont(buttonFont);
        int i = 140;
        for (MenuButton button : menuButtons) {
            i = i + 60;

            if(button.selected) { canvas.setColor(activeButtonColor); }
            else { canvas.setColor(buttonColor); }

            canvas.drawString(button.name, gh.getScreenSize().width/2 - 50, i);
        }

        // Draw backbuffer to screen
        gh.render();
    }
}
