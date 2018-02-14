package TheGame.GUI;

import TheGame.GameCode.Disk;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static java.lang.Thread.sleep;

/**
 * Created by sagi on 17/01/2018.
 */
public class Tile extends StackPane {
    private int size,row, col;;
    private int color = 0;
    private static final int WHITE = 1;
    private static final int BLACK = -1;
    private Color blackColor;
    private Color whiteColor;





    public Tile(int s , int x, int y, Color blackCol, Color whiteCol) {
        size = s;
        col= x;
        row = y;
        blackColor = blackCol;
        whiteColor = whiteCol;
        relocate(x*size, y*size);
        Rectangle border = new Rectangle(size, size);
        border.setFill(Color.LIGHTBLUE);
        border.setStroke(Color.WHITE);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Disk disk = new Disk(row,col, GameBoardController.getCurrnetPlayer());
                boolean thereWasAMove = GameBoardController.getRules().play(GameBoardController.getBoard(), disk);

                if(thereWasAMove){
                    if (GameBoardController.getCurrnetPlayer()==-1) {
                        getChildren().add(blackDisk());
                        setColor(-1);
                    }else{
                        getChildren().add(whiteDisk());
                        setColor(1);
                    }

                    GameBoardController.updateTiles();
                    GameBoardController.setCurrnetPlayer(-GameBoardController.getCurrnetPlayer());
              }else{
//                    border.setFill(Color.RED);
//                    try {
//                        sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    border.setFill(Color.LIGHTBLUE);
                }
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                    getChildren().add(whiteDisk());
            }
        });

    }
    private Circle blackDisk() {return new Circle(size/2.5,blackColor);}
    private Circle whiteDisk() {return new Circle(size/2.5,whiteColor);}


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        if(this.color==BLACK){
            getChildren().add(blackDisk());
        }
        if(this.color==WHITE){
            getChildren().add(whiteDisk());
        }
    }

}

