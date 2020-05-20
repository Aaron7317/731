package Java.Game.GameObjects.Entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends Rectangle {

    private double xOffset, yOffset, speed, xAcceleration, yAcceleration, xVelocity, yVelocity;

    public Enemy(double x, double y, int width, int height, Color color, double speed, double playerY) {
        super(width, height, color);

        xOffset = x;
        yOffset = y;

        this.speed = speed;
        xAcceleration = 0;
        yAcceleration = 0;
        xVelocity = 0;
        yVelocity = 0;
        setTranslateX(xOffset);
        setTranslateY(yOffset - playerY);
    }

    public void move(double playerY, double playerX, double sceneHeight) {
        xAcceleration = 0;
        yAcceleration = 0;

        if (Math.abs(Math.abs(yOffset) - Math.abs(playerY)) < sceneHeight) {
            if (playerX - xOffset < 0 && xVelocity > -6) {
                xAcceleration = -0.1;
            }
            if (playerX - xOffset > 0 && xVelocity < 6) {
                xAcceleration = 0.1;
            }
            if (playerY - yOffset > 0 && yVelocity < 6) {
                yAcceleration = 0.1;
            }
            if (playerY - yOffset < 0 && yVelocity > -6) {
                yAcceleration = -0.1;
            }

            yVelocity += yAcceleration;
            xVelocity += xAcceleration;
            yVelocity *= 0.95;
            xVelocity *= 0.95;

            yOffset += yVelocity; 
            xOffset += xVelocity;

            render(playerY, sceneHeight);
        }
    }

    public void render(double playerY, double sceneHeight) {
        if (Math.abs(playerY - yOffset) < (sceneHeight / 2) + (getHeight() * 2)) {
            setTranslateX(xOffset);
            setTranslateY(yOffset - playerY + (sceneHeight / 2));
        }
    }

    public double getYOffset() {
        return yOffset;
    }
    
}