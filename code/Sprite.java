import java.awt.*;

/**
 * Sprite Class
 * @author Fredrik Skoglind
 */
public class Sprite implements Drawable {
    private int positionX;
    private int positionY;

    private int velocityX;
    private int velocityY;

    private int width;
    private int height;

    public Sprite(int positionX, int positionY, int velocityX, int velocityY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;

        this.velocityX = velocityX;
        this.velocityY = velocityY;

        this.width = width;
        this.height = height;
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

    public void moveUp(Rectangle boundries) {
        int newPositionY = this.positionY - this.velocityY;
        if( newPositionY < boundries.y ) { newPositionY = boundries.y; }
        this.move(this.positionX, newPositionY);
    }

    public void moveLeft(Rectangle boundries) {
        int newPositionX = this.positionX - this.velocityX;
        if( newPositionX < boundries.x ) { newPositionX = boundries.x; }
        this.move(newPositionX, this.positionY);
    }

    public void moveDown(Rectangle boundries) {
        int newPositionY = this.positionY + this.velocityY;
        if( (newPositionY + this.height) > (boundries.y + boundries.height) ) { newPositionY = (boundries.y + boundries.height) - height; }
        this.move(this.positionX, newPositionY);
    }

    public void moveRight(Rectangle boundries) {
        int newPositionX = this.positionX + this.velocityX;
        if( (newPositionX + width) > (boundries.x + boundries.width) ) { newPositionX = (boundries.x + boundries.width) - width ; }
        this.move(newPositionX, this.positionY);
    }

    public void Draw(Graphics g) {
        // Draw Hitbox
        g.setColor(Color.WHITE);
        g.fillRect(this.positionX, this.positionY, this.width, this.height);
    }
}
