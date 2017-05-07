import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Paddle Class
 * @author Fredrik Skoglind
 */
public class Paddle extends Sprite {
    public Paddle(GraphicsHandler gh, BufferedImage spriteImage, int positionX, int positionY, int velocityX, int velocityY, int width, int height) {
        super(gh, spriteImage, positionX, positionY, velocityX, velocityY, width, height);
    }

    public void moveUp(Rectangle boundaries) {
        int newPositionY = this.positionY - this.velocityY;
        if( newPositionY < boundaries.y ) { newPositionY = boundaries.y; }
        this.move(this.positionX, newPositionY);
    }

    public void moveLeft(Rectangle boundaries) {
        int newPositionX = this.positionX - this.velocityX;
        if( newPositionX < boundaries.x ) { newPositionX = boundaries.x; }
        this.move(newPositionX, this.positionY);
    }

    public void moveDown(Rectangle boundaries) {
        int newPositionY = this.positionY + this.velocityY;
        if( (newPositionY + this.height) > (boundaries.y + boundaries.height) ) { newPositionY = (boundaries.y + boundaries.height) - this.height; }
        this.move(this.positionX, newPositionY);
    }

    public void moveRight(Rectangle boundries) {
        int newPositionX = this.positionX + this.velocityX;
        if( (newPositionX + width) > (boundries.x + boundries.width) ) { newPositionX = (boundries.x + boundries.width) - this.width ; }
        this.move(newPositionX, this.positionY);
    }
}
