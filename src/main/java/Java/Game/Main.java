package Java.Game;

import java.util.ArrayList;

import Java.Game.GameObjects.Background.*;
import Java.Game.GameObjects.Entities.Enemy;
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
    private double rightBounds, leftBounds, wallThickness;
    ArrayList<Enemy> enemies;
    private int currentDistance, lastMilestone;
    // Milestones will help determine when to generate enemies

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

        enemies = new ArrayList<>();
        
        // Initial setup
        primaryStage.setTitle("731");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
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
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

        player.gotoPosition(scene.getWidth() / 2, scene.getHeight() / 2);
        player.setWidth(scene.getWidth() / 40);
        player.setHeight(scene.getWidth() / 40);

        wallThickness = scene.getWidth() / 20;
        leftBounds = scene.getWidth() / 20;
        rightBounds = scene.getWidth() - scene.getWidth() / 20;
        player.setBounds(leftBounds, rightBounds);

        leftWall.setWidth(wallThickness);
        leftWall.setHeight(scene.getHeight());
        rightWall.setTranslateX(rightBounds);
        rightWall.setWidth(wallThickness);
        rightWall.setHeight(scene.getHeight());
        background.setWidth(scene.getWidth());
        background.setHeight(scene.getHeight());

        
        generateWave(1);

        // Game Loop
        final long startTime = System.nanoTime();
        new AnimationTimer() {
        
            @Override
            public void handle(long currentNanoTime) {
                player.checkInputs(inputs);
                currentDistance = -(int)player.getYOffset();
                
                player.render();
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).render(player.getYOffset(), scene.getHeight(), player.getYVelocity());
                }



            }
        }.start();
        
    }


    private void closeProgram(Stage window) {
        if (ConfirmationBox.display("Close", "Are you sure you would like to exit the program?")) {
            window.close();
        }
    }

    // * Enemy Generation

    private Enemy generateEnemy(double sceneWidth, double sceneHeight, double playerY) {
        return new Enemy(Math.random() * sceneWidth, playerY - (Math.random() * 200) - (sceneHeight / 2), 40, 40, Color.RED, 0, player.getYOffset());
    }

    private void implementEnemy(double sceneWidth, double sceneHeight, double playerY) {
        enemies.add(generateEnemy(sceneWidth, sceneHeight, playerY));
        layout.getChildren().add(enemies.get(enemies.size() - 1));
    }

    private void deleteEnemy(int enemyIndex) {
        layout.getChildren().remove(enemies.get(enemyIndex));
        enemies.remove(enemyIndex);
    }

    private void deleteAllEnemies(int enemyAmout) {
        for (int i = 0; i < enemyAmout; i++) {
            deleteEnemy(i);
        }
    }

    private void generateWave(int difficultyModifier) {
        for (int i = 0; i < difficultyModifier; i++) {
            implementEnemy(scene.getWidth(), scene.getHeight(), player.getYOffset());
        }
    }

}
