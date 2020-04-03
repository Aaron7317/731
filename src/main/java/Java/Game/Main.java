package Java.Game;

import java.util.ArrayList;

import Java.Game.GameObjects.*;
import Java.Game.UI.ConfirmationBox;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.geometry.Rectangle2D;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class Main extends Application {

    private Pane layout;
    private Scene scene;
    ArrayList<String> inputs;
    private Player player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Creating Game Objects
        player = new Player(100, 100, 40, 40, Color.BLUE);

        // Initial setup
        primaryStage.setTitle("JavaFX Game");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
        layout = new Pane();
        layout.getChildren().addAll(player);
        scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);

        // Getting User Input
        inputs = new ArrayList<String>();
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            if(!inputs.contains(code)) {
                inputs.add(code);
            }
        });
        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            inputs.remove(code);
        });

        // Any Additional Setup Required Before Running Game Loop
        

        // Game Loop
        final long startTime = System.nanoTime();
        new AnimationTimer() {
        
            @Override
            public void handle(long currentNanoTime) {
                player.checkInputs(inputs);
                player.update();
                player.render();
            }
        }.start();
        
        // * USE SCENE INSTEAD OF STAGE FOR SIZE REFERENCING

        primaryStage.show();
    }


    private void closeProgram(Stage window) {
        boolean result = ConfirmationBox.display("Close", "Are you sure you would like to exit the program?");
        if (result) {
            window.close();
        }
    }

    
}
