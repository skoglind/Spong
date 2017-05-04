import java.awt.*;

/**
 * Paddle Class
 * @author Fredrik Skoglind
 */
public class Paddle extends Sprite {
    public Paddle(int positionX, int positionY, int velocityX, int velocityY, int width, int height) {
        super(positionX, positionY, velocityX, velocityY, width, height);
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
        if( (newPositionY + this.height) > (boundries.y + boundries.height) ) { newPositionY = (boundries.y + boundries.height) - this.height; }
        this.move(this.positionX, newPositionY);
    }

    public void moveRight(Rectangle boundries) {
        int newPositionX = this.positionX + this.velocityX;
        if( (newPositionX + width) > (boundries.x + boundries.width) ) { newPositionX = (boundries.x + boundries.width) - this.width ; }
        this.move(newPositionX, this.positionY);
    }

    public void Draw(Graphics g) {
        super.Draw(g);
        // Graphics
    }
}
