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
    private int selectedID = 0;

    // Cooldowns
    private int keyPressCoolDown_Menu = 0;

    public MenuController(Game game, GraphicsHandler gh, InputHandler input) {
        super(game, gh, input);
    }

    public void dispose() {
        super.dispose();
        menuButtons.clear();
        selectedID = 0;
    }

    public void init() {
        dispose();

        menuButtons.add(new MenuButton("Play", ButtonAction.PLAY));
        menuButtons.add(new MenuButton("Quit", ButtonAction.QUIT));

        menuButtons.get(0).selected = true;
    }

    public void logic() {
        // Quit Game
        if (input.escape.keyDown) {
            game.quit();
        }

        // Move in menu
        keyPressCoolDown_Menu = keyPressCoolDown_Menu - 1;
        if(keyPressCoolDown_Menu <= 0) {
            keyPressCoolDown_Menu = 0;
            if (input.down_p1.keyDown) {
                keyPressCoolDown_Menu = 8;
                setSelected(selectedID+1);
            }

            if (input.up_p1.keyDown) {
                keyPressCoolDown_Menu = 8;
                setSelected(selectedID-1);
            }
        }

        // Use menuitem
        if (input.enter.keyDown) {
            switch(menuButtons.get(selectedID).action) {
                case PLAY:
                    input.releaseAll();
                    game.setState(GameState.GAME, true);
                    break;
                case QUIT:
                    game.quit();
                    break;
            }
        }
    }

    private void setSelected(int newIndex) {
        for (MenuButton button : menuButtons) {
            button.selected = false;
        }

        if(newIndex > menuButtons.size()-1) { newIndex = menuButtons.size()-1; }
        if(newIndex < 0) { newIndex = 0; }
        selectedID = newIndex;

        menuButtons.get(selectedID).selected = true;
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
