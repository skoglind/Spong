import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * GraphicsHandler Class
 * @author Fredrik Skoglind
 */
public class GraphicsHandler extends JFrame {
    private Graphics g;
    private BufferedImage backBuffer;
    private Rectangle screenSize;
    private String title;
    private Insets insets;
    private int width;
    private int height;

    public GraphicsHandler(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        g = getGraphics();
    }

    public void showWindow() {
        setTitle(this.title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width,height);
        getContentPane().setBackground( Color.YELLOW );
        setLocationRelativeTo(null);
        setVisible(true);

        // Hide mousecursor
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        getContentPane().setCursor(blankCursor);

        // Get insets
        insets = getInsets();

        // Save screensize
        screenSize = new Rectangle(0, 0,
                (int)getContentPane().getSize().getWidth(),
                (int)getContentPane().getSize().getHeight());

        // Setup buffer
        backBuffer = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
    }

    public Rectangle getScreenSize() {
        return screenSize;
    }

    public Graphics backBuffer() {
        return backBuffer.getGraphics();
    }

    public void render() {
        Graphics screen = getGraphics();
        screen.drawImage(backBuffer, insets.left, insets.top, this);
    }
}
