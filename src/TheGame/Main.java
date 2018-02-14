package TheGame; /**
 * Created by sagi on 13/01/2018.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import TheGame.GameCode.Board;

public class Main extends Application {
    private Board b = new Board(6,6);
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            HBox root = (HBox) FXMLLoader.load(getClass().getResource("GUI/Game.fxml"));
            Scene scene = new Scene(root, 520, 400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
