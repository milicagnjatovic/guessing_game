import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        VBox gameBox = new VBox();
        Button btnNewGame = new Button("New Game");
        btnNewGame.setOnAction(e -> {
            gameBox.getChildren().clear();
            GameGUI newGame = new GameGUI();
            gameBox.getChildren().addAll(newGame.getRoot());
        });


        btnNewGame.setStyle("-fx-background-color: LIGHTSALMON");
        btnNewGame.setPrefWidth(500);
        root.getChildren().addAll(btnNewGame, gameBox);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Guessing game");
        primaryStage.show();
    }
}
