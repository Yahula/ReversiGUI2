package TheGame.GameCode;

import javafx.scene.layout.StackPane;

/**
 * Created by sagi on 15/01/2018.
 */
public class Disk extends StackPane {
    private int row;
    private int column;
    private int color;

    public Disk(int r, int col, int color) {
        this.row = r;
        this.column = col;
        this.color = color;
//        relocate(column*TILE_SIZE,row*TILE_SIZE);
//        Circle disk = new Circle(TILE_SIZE*0.4);
//        disk.setFill(color == 1 ? Color.WHITE : Color.BLACK);
//
//        disk.setTranslateX((TILE_SIZE-TILE_SIZE*0.8)*0.5);
//        setTranslateY((TILE_SIZE-TILE_SIZE*0.8)*0.5);
//        getChildren().add(disk);
    }
    public Disk(Disk d) {
        this.row = d.getRow();
        this.column = d.getColumn();
        this.color = d.getColor();
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
