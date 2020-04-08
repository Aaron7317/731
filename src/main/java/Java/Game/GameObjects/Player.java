package Java.Game.GameObjects;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {
    
    private double xVelocity, yVelocity, xAcceleration, yAcceleration, xOffset, yOffset;

    public Player(double x, double y, int width, int height, Color color) {
        super(width, height, color);

        xOffset = x;
        yOffset = y;
        xVelocity = 0;
        yVelocity = 0;
        xAcceleration = 0;
        yAcceleration = 0;

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

    public void gotoPosition(double x, double y) {
        xOffset = x;
        yOffset = y;
    }

    public void update() {

        // Calculates velocity and updates the player's position
        yVelocity += yAcceleration;
        xVelocity += xAcceleration;
        yVelocity *= 0.95;
        xVelocity *= 0.95;
        yOffset += yVelocity; 
        xOffset += xVelocity;

    }

    public void render() {
        update();

        setTranslateX(xOffset);
        setTranslateY(yOffset);
    }

    public double getYOffset() {
        return yOffset;
    }

    public double getXOffset() {
        return xOffset;
    }

}