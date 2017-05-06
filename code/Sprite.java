import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Sprite Class
 * @author Fredrik Skoglind
 */
public class Sprite {
    protected BufferedImage spriteImage;
    protected GraphicsHandler gh;

    protected int positionX;
    protected int positionY;

    protected int velocityX;
    protected int velocityY;

    protected int width;
    protected int height;

    public Sprite(GraphicsHandler gh, BufferedImage spriteImage, int positionX, int positionY, int velocityX, int velocityY, int width, int height) {
        this.gh = gh;
        this.spriteImage = spriteImage;

        this.positionX = positionX;
        this.positionY = positionY;

        this.velocityX = velocityX;
        this.velocityY = velocityY;

        this.width = width;
        this.height = height;
    }

    public boolean collide(Sprite s) {
        return this.getHitbox().intersects(s.getHitbox());
    }

    public Rectangle getHitbox() {
        return new Rectangle(positionX, positionY, width, height);
    }

    public void setVelocityX(int velocity) {
        this.velocityX = velocity;
    }

    public void setVelocityY(int velocity) {
        this.velocityY = velocity;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void Draw(Graphics g) {
        int borderOffset = 1;
        g.setColor(Color.WHITE);

        g.drawRect(this.positionX-borderOffset, this.positionY-borderOffset,
                   this.width+(borderOffset*2), this.height+(borderOffset*2));

        g.drawImage(spriteImage, positionX, positionY, width, height, gh);
    }
}
