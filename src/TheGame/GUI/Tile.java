package TheGame.GUI;

import TheGame.GameCode.Disk;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Tile extends StackPane {
    private int size, row, col;
    ;
    private int color = 0;
    private static final int WHITE = 1;
    private static final int BLACK = -1;
    private Color blackColor;
    private Color whiteColor;
    private Rectangle border;
    private int flag;


    public Tile(int s, int x, int y, Color blackCol, Color whiteCol) {
        flag = 0;
        size = s;
        col = x;
        row = y;
        blackColor = blackCol;
        whiteColor = whiteCol;
        relocate(x * size, y * size);
        border = new Rectangle(size, size);
        border.setFill(Color.LIGHTBLUE);
        border.setStroke(Color.WHITE);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);
        GameBoardController.setCurrnetPlayer(GameBoardController.getCurrnetPlayer());
        setOnMouseClicked(event -> {
            boolean thereWasAMove = false;
            if (event.getButton() == MouseButton.PRIMARY) {
                Disk disk = new Disk(row, col, GameBoardController.getCurrnetPlayer());
                try {
                    thereWasAMove = GameBoardController.getRules().play(GameBoardController.getBoard(), disk);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (thereWasAMove) {
                    if (GameBoardController.getCurrnetPlayer() == -1) {
                        getChildren().add(blackDisk());
                        setColor(-1);
                    } else {
                        getChildren().add(whiteDisk());
                        setColor(1);
                    }
                    GameBoardController.updateTiles();
                    GameBoardController.setCurrnetPlayer(-GameBoardController.getCurrnetPlayer());

                    if (!GameBoardController.getRules().canPlay(GameBoardController.getBoard(),
                            GameBoardController.getCurrnetPlayer())) { //next player cant play
                        if (flag == 1) {
                            GameBoardController.getRules().updateScore(GameBoardController.getBoard());
                            endGameWindow();
                        } else if (flag == 0) { //next player can play
                            flag = 1;
                            if (!GameBoardController.getRules().canPlay(GameBoardController.getBoard(),
                                    GameBoardController.getCurrnetPlayer())) { //next player cant play
                                GameBoardController.getRules().updateScore(GameBoardController.getBoard());
                                endGameWindow();
                            }
                        }
                    } else if (GameBoardController.getBoard().isBoardfull()) { //board is full
                        endGameWindow();
                    } else {
                        flag = 0;
                    }

                } else {
//                    Rectangle tmpBorder = new Rectangle(size, size);
//                    tmpBorder.setFill(Color.LIGHTBLUE);
//                    tmpBorder.setStroke(Color.RED);
//                    getChildren().add(tmpBorder);
//                    getChildren().remove(border);
//
//                    try
//                    {
//                        Thread.sleep(1000);
//                    }
//                    catch(InterruptedException ex)
//                    {
//                        Thread.currentThread().interrupt();
//                    }
//                    getChildren().add(border);
//                    getChildren().remove(tmpBorder);

                }
            }
        });
    }

    private Circle blackDisk() {
        return new Circle(size / 2.5, blackColor);
    }

    private Circle whiteDisk() {
        return new Circle(size / 2.5, whiteColor);
    }

    public void setColor(int color) {
        this.color = color;
        if (this.color == BLACK) {
            getChildren().add(blackDisk());
        }
        if (this.color == WHITE) {
            getChildren().add(whiteDisk());
        }
    }

    public void endGameWindow() {
        Stage stage = new Stage();
        try {
            VBox root = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
            Scene scene = new Scene(root, 300, 200);
            scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

