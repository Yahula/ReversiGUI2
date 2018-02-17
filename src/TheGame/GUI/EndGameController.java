package TheGame.GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {

    @FXML
    public Button closeButton;

    @FXML
    public Text score;

    @FXML
    public Text winner;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String b = "Black Score: " + GameBoardController.getRules().getScore()[1] + "     White Score: " + GameBoardController.getRules().getScore()[0];
        score.setText(b);
        if (GameBoardController.getRules().getScore()[1] > GameBoardController.getRules().getScore()[0]){
            winner.setText("Black Player Wins!!!");
            winner.setFill(GameBoardController.getBlackColor());
        } else if (GameBoardController.getRules().getScore()[1] < GameBoardController.getRules().getScore()[0]){
            winner.setText("White Player Wins!!!");
            winner.setFill(GameBoardController.getWhiteColor());
        } else {
            winner.setText("It's a Tie!");


        }
    }

    @FXML
    public void closeWindow(javafx.event.ActionEvent actionEvent){
        Platform.exit();
    }
}
