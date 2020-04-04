package Java.Game;

import java.util.ArrayList;

import Java.Game.GameObjects.Background.*;
import Java.Game.GameObjects.Player;
import Java.Game.UI.ConfirmationBox;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.geometry.Rectangle2D;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

public class Main extends Application {

    private Pane layout;
    private Scene scene;
    public ArrayList<String> inputs;
    private Player player;
    private GameBackground background;
    private LeftWall leftWall;
    private RightWall rightWall;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Creating Game Objects
        background = new GameBackground(0, 0, Color.WHITE);
        leftWall = new LeftWall(0, 0, Color.DARKGRAY);
        rightWall = new RightWall(0, 0, Color.DARKGRAY);
        player = new Player(0, 0, 40, 40, Color.BLUE);

        // Initial setup
        primaryStage.setTitle("731");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        System.out.println();
        // TODO: find a way to force full screen while making it easy to close the application
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
        layout = new Pane();
        layout.getChildren().addAll(player, background, leftWall, rightWall);
        scene = new Scene(layout);
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
                background.render(scene.getWidth(), scene.getHeight());
                leftWall.render(scene.getWidth() / 20, scene.getHeight());
                rightWall.render(scene.getWidth() / 20, scene.getHeight());
                player.render();
            }
        }.start();
        

        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }


    private void closeProgram(Stage window) {
        boolean result = ConfirmationBox.display("Close", "Are you sure you would like to exit the program?");
        if (result) {
            window.close();
        }
    }

    
}
