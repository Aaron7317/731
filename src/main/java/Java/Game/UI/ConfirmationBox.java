package Java.Game.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationBox {

    private static Button confirmButton, cancelButton;
    private static GridPane layout;
    private static Label text;
    private static boolean response;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        response = false;

        text = new Label(message);
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");
        confirmButton.setOnAction(e -> {
            response = true;
            window.close();
        });
        cancelButton.setOnAction(e -> {
            response = false;
            window.close();
        });

        layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setHgap(10);
        layout.setVgap(8);

        layout.setConstraints(text, 0, 0);
        layout.setConstraints(confirmButton, 0, 1);
        layout.setConstraints(cancelButton, 1, 1);

        layout.getChildren().addAll(confirmButton, cancelButton, text);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 400, 100);
        window.setScene(scene);
        window.showAndWait();

        return response;
    }
}