import java.awt.*;

/**
 * Sprite Class
 * @author Fredrik Skoglind
 */
public class Sprite {
    protected int positionX;
    protected int positionY;

    protected int velocityX;
    protected int velocityY;

    protected int width;
    protected int height;

    public Sprite(int positionX, int positionY, int velocityX, int velocityY, int width, int height) {
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
        g.setColor(Color.WHITE);
        g.fillRect(this.positionX, this.positionY, this.width, this.height);
    }
}
