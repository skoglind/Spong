import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Ball Class
 * @author Fredrik Skoglind
 */
public class Ball extends Sprite {
    private int direction;
    private double directionX;
    private double directionY;
    private double velocityIncrease;
    private int maxBallSpeed;

    public Ball(GraphicsHandler gh, BufferedImage spriteImage, int positionX, int positionY, int velocityX, int velocityY, int width, int height, double velocityIncrease, int maxBallSpeed) {
        super(gh, spriteImage, positionX, positionY, velocityX, velocityY, width, height);
        this.directionX = 0;
        this.directionY = 0;
        this.velocityIncrease = velocityIncrease;
        this.maxBallSpeed = maxBallSpeed;
    }

    public void setDirection(int degree) {
        if(degree > 85 && degree < 95) { degree = 85; }
        if(degree > 265 && degree < 275) { degree = 275; }
        this.direction = degree;

        double radians = (Math.PI/180 * degree);
        this.directionX = Math.cos(radians);
        this.directionY = -Math.sin(radians);
    }

    public int bounceDirection(Paddle p, int playerNo) {
        int deltaY = (positionY + (height/2)) - (p.positionY + (p.height/2));
        int direction = 0;

        switch(playerNo) {
            case 1:
                // Upper part
                if( deltaY < -50 ) { direction = 65; }
                else if( deltaY < -35 ) { direction = 45; }
                else if( deltaY < -25 ) { direction = 30; }
                else if( deltaY < -10 ) { direction = 15; }
                else if( deltaY <= 0 ) { direction = 5; }

                // Lower part
                else if( deltaY > 50 ) { direction = 295; }
                else if( deltaY > 35 ) { direction = 315; }
                else if( deltaY > 25 ) { direction = 330; }
                else if( deltaY > 10 ) { direction = 345; }
                else if( deltaY >= 0 ) { direction = 355; }

                break;
            case 2:
                // Upper part
                if( deltaY < -50 ) { direction = 115; }
                else if( deltaY < -35 ) { direction = 135; }
                else if( deltaY < -25 ) { direction = 150; }
                else if( deltaY < -10 ) { direction = 165; }
                else if( deltaY <= 0 ) { direction = 175; }

                // Lower part
                else if( deltaY > 50 ) { direction = 245; }
                else if( deltaY > 35 ) { direction = 225; }
                else if( deltaY > 25 ) { direction = 210; }
                else if( deltaY > 10 ) { direction = 195; }
                else if( deltaY >= 0 ) { direction = 185; }

                break;
        }

        return direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void increaseVelocity() {
        if (this.velocityX < this.maxBallSpeed) {
            this.velocityX = (int) (this.velocityX * velocityIncrease);
        } else { this.velocityX = this.maxBallSpeed; }

        if (this.velocityY < this.maxBallSpeed) {
            this.velocityY = (int) (this.velocityY * velocityIncrease);
        } else { this.velocityY = this.maxBallSpeed; }
    }

    public int updatePosition(Rectangle boundaries) {
        int ballStatus = 0;
        int newPositionX = (int)(this.positionX + (this.directionX * this.velocityX));
        int newPositionY = (int)(this.positionY + (this.directionY * this.velocityY));

        // Bounce on boundries
        if(newPositionY < boundaries.y) {
            newPositionY = boundaries.y;
            this.directionY = -this.directionY;
            this.increaseVelocity();
            ballStatus = 3;
        } else if((newPositionY + this.height) > (boundaries.y + boundaries.height)) {
            newPositionY = (boundaries.y + boundaries.height) - this.height;
            this.directionY = -this.directionY;
            this.increaseVelocity();
            ballStatus = 3;
        }

        // Kill Ball if outside
        if((newPositionX + this.width) < boundaries.x) {
            ballStatus = 1;
        } else if(newPositionX > (boundaries.x + boundaries.width)) {
            ballStatus = 2;
        }

        this.positionX = newPositionX;
        this.positionY = newPositionY;

        return ballStatus;
    }
}
