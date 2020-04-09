package Java.Game.GameObjects.Entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends Rectangle {

    private double xOffset, yOffset, speed;

    public Enemy(int x, int y, int width, int height, Color color, double speed) {
        super(width, height, color);

        xOffset = x;
        yOffset = y;
        this.speed = speed;

        setTranslateX(xOffset);
    }

    public void render() {
        
    }
}