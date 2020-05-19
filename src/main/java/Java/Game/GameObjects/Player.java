package Java.Game.GameObjects;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {
    
    private double xVelocity, yVelocity, xAcceleration, yAcceleration, xOffset, yOffset, leftBounds, rightBounds;
    private boolean safe;

    public Player(double x, double y, int width, int height, Color color) {
        super(width, height, color);

        xOffset = x;
        yOffset = y;
        xVelocity = 0;
        yVelocity = 0;
        xAcceleration = 0;
        yAcceleration = 0;
        leftBounds = 0;
        rightBounds = 0;

        safe = false;

        setViewOrder(5);

        
    }

    public void checkInputs(ArrayList<String> inputs) {
        if(inputs.contains("W")) {
            moveUp();
        } 
        if(inputs.contains("S")) {
            moveDown();
        }
        if(!inputs.contains("W") && !inputs.contains("S")) {
            yAcceleration = 0;
        }
        if(inputs.contains("A")) {
            moveLeft();
        } 
        if(inputs.contains("D")) {
            moveRight();
        }
        if(!inputs.contains("A") && !inputs.contains("D")) {
            xAcceleration = 0;
        }
    }

    private void moveUp() {
        if (yVelocity >= -10) {
            yAcceleration = -0.2;
        } 
    }

    private void moveDown() {
        if (yVelocity <= 10) {
            yAcceleration = 0.2;
        } 
    }

    private void moveRight() {
        if (xVelocity <= 6) {
            xAcceleration = 0.2;
        }
    }

    private void moveLeft() {
        if (xVelocity >= -6) {
            xAcceleration = -0.2;
        }
    }

    private void render() {
        setTranslateX(xOffset);
    }

    public void gotoPosition(double x, double y) {
        xOffset = x;
        yOffset = 0;
        setTranslateY(y);
    }

    public void setBounds(double left, double right) {
        leftBounds = left;
        rightBounds = right;
    }
    
    public void move() {
        
        yVelocity += yAcceleration;
        xVelocity += xAcceleration;
        yVelocity *= 0.95;
        xVelocity *= 0.95;

        if (xOffset <= leftBounds) {
            xVelocity = Math.abs(xVelocity);
            xVelocity *= 0.5;
        }
        if (xOffset >= rightBounds - getWidth()) {
            xVelocity = -Math.abs(xVelocity);
            xVelocity *= 0.5;
        }
        if (Math.abs(yVelocity) < 0.02) {
            yVelocity = 0;
        }

        yOffset += yVelocity; 
        xOffset += xVelocity;

        render();
    }

    public double getYOffset() {
        return yOffset;
    }

    public double getXOffset() {
        return xOffset;
    }

    public double getYVelocity() {
        return yVelocity;
    }

}