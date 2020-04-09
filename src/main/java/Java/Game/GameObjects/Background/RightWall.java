package Java.Game.GameObjects.Background;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RightWall extends Rectangle {

    public RightWall(int width, int height, Color color) {
        super(width, height, color);

        setTranslateX(0);
        setTranslateY(0);

        setViewOrder(9);
    }

}