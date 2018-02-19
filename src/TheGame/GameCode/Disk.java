package TheGame.GameCode;

import javafx.scene.layout.StackPane;

public class Disk extends StackPane {
    private int row;
    private int column;
    private int color;

    public Disk(int r, int col, int color) {
        this.row = r;
        this.column = col;
        this.color = color;
    }

    public Disk(Disk d) {
        this.row = d.getRow();
        this.column = d.getColumn();
        this.color = d.getColor();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getColor() {
        return color;
    }
}
