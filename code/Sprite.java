import java.awt.*;

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

    public void moveUp() {
        this.move(this.positionX, this.positionY - this.velocityY);
    }

    public void moveLeft() {
        this.move(this.positionX - this.velocityX, this.positionY);
    }

    public void moveDown() {
        this.move(this.positionX, this.positionY + this.velocityY);
    }

    public void moveRight() {
        this.move(this.positionX + this.velocityX, this.positionY);
    }

    public void Draw(Graphics g) {
        g.fillRect(this.positionX, this.positionY, this.width, this.height);
    }
}
