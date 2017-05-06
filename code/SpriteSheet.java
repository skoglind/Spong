import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * SpriteSheet Class
 * @author Fredrik Skoglind
 */
public class SpriteSheet {
    private String filename;
    private BufferedImage spritesheet;

    public SpriteSheet(String filename) {
        this.filename = filename;
        try {
            spritesheet = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}
