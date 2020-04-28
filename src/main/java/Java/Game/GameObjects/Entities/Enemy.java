package Java.Game.GameObjects.Entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends Rectangle {

    private double xOffset, yOffset, speed;

    public Enemy(double x, double y, int width, int height, Color color, double speed, double playerY) {
        super(width, height, color);

        xOffset = x;
        yOffset = y;
        this.speed = speed;

        setTranslateX(xOffset);
        setTranslateY(yOffset - playerY);
    }

    public void render(double playerY, double sceneHeight, double playerYVelocity) {
        if (playerYVelocity != 0 && Math.abs(playerY - yOffset) < (sceneHeight / 2) + (getHeight() * 2)) {
            setTranslateX(xOffset);
            setTranslateY(yOffset - playerY + (sceneHeight / 2));
        }
    }

    
}