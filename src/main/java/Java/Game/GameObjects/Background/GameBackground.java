package Java.Game.GameObjects.Background;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class GameBackground extends Rectangle {

    public GameBackground(int width, int height, Color color) {
        super(width, height, color);

        setTranslateX(0);
        setTranslateY(0);

        setViewOrder(10);
    }

}