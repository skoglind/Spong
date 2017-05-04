import java.awt.*;

/**
 * Ball Class
 * @author Fredrik Skoglind
 */
public class Ball extends Sprite {
    private double directionX;
    private double directionY;
    private double velocityIncrease;
    private int maxBallSpeed;
    private int oldPositionX;
    private int oldPositionY;
    private int bounces = 0;

    public Ball(int positionX, int positionY, int velocityX, int velocityY, int width, int height, double velocityIncrease, int maxBallSpeed) {
        super(positionX, positionY, velocityX, velocityY, width, height);
        this.directionX = 0;
        this.directionY = 0;
        this.velocityIncrease = velocityIncrease;
        this.maxBallSpeed = maxBallSpeed;
    }

    public void setDirection(int degree) {
        if(degree > 85 && degree < 95) { degree = 85; }
        if(degree > 265 && degree < 275) { degree = 275; }

        double radians = (Math.PI/180 * degree);
        System.out.println("  - X: " + Math.cos(radians));
        System.out.println("  - Y: " + -Math.sin(radians));

        this.directionX = Math.cos(radians);
        this.directionY = -Math.sin(radians);
    }

    public void increaseVelocity() {
        if (this.velocityX < this.maxBallSpeed) {
            this.velocityX = (int) (this.velocityX * velocityIncrease);
        } else { this.velocityX = this.maxBallSpeed; }

        if (this.velocityY < this.maxBallSpeed) {
            this.velocityY = (int) (this.velocityY * velocityIncrease);
        } else { this.velocityY = this.maxBallSpeed; }

        System.out.println("BALL SPEED CONSTANT: " + velocityIncrease);
        System.out.println("  - X: " + this.velocityX);
        System.out.println("  - Y: " + this.velocityY);
    }

    public boolean updatePosition(Rectangle boundries) {
        boolean stillAlive = true;
        int newPositionX = (int)(this.positionX + (this.directionX * this.velocityX));
        int newPositionY = (int)(this.positionY + (this.directionY * this.velocityY));

        this.oldPositionX = this.positionX;
        this.oldPositionY = this.positionY;

        // Bounce on boundries
        if(newPositionY < boundries.y) {
            newPositionY = boundries.y;
            this.directionY = -this.directionY;
            this.increaseVelocity();
        } else if((newPositionY + this.height) > (boundries.y + boundries.height)) {
            newPositionY = (boundries.y + boundries.height) - this.height;
            this.directionY = -this.directionY;
            this.increaseVelocity();
        }

        // Kill Ball if outside
        if((newPositionX + this.width) < boundries.x) {
            stillAlive = false; // Kill
        } else if(newPositionX > (boundries.x + boundries.width)) {
            stillAlive = false; // Kill
        }

        this.positionX = newPositionX;
        this.positionY = newPositionY;

        return stillAlive;
    }

    public void Draw(Graphics g) {
        super.Draw(g);
        // Graphics
    }
}
