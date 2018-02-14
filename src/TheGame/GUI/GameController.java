package TheGame.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private HBox root;

    @FXML
    private Button settings;

    @FXML
    private MenuBar Menu;

    @FXML
    public Label BlackScore;

    @FXML
    public Label WhiteScore;

    @FXML
    public Text Turn;


    @FXML
    private static Stage menuStage;



public void initialize(URL location, ResourceBundle resources) {
        GameBoardController gameBoard = new GameBoardController( this);
        gameBoard.setPrefWidth(400);
        gameBoard.setPrefHeight(400);
        root.getChildren().add(0, gameBoard);
        int[] score = new int[2];
        gameBoard.draw();
    }

    @FXML
    public void updateVisibleScore(int score[]){
        BlackScore.setText("Black:  " + score[0]);
        WhiteScore.setText("White:  " + score[1]);
    }

    @FXML
    public void setTurn(int player){
        if (player == -1) {
            Turn.setText("Black's turn");
        }
        else{
            Turn.setText("White's turn");
        }
    }

    public void openSettings(ActionEvent actionEvent) {
        menuStage = new Stage();
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("Enter.fxml"));
//            HBox root = (HBox)FXMLLoader.load(getClass().getResource("Game.fxml"));
            Scene scene = new Scene(root, 600, 500);
            scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
            menuStage.setTitle("Reversi Game");
            menuStage.setScene(scene);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getMenuStage() {
        return menuStage;
    }
}


