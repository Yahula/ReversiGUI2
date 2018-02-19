package TheGame.GameCode;

public class Board {
    private int[][] board;
    private int column, row;

    public Board(int r, int c) {
        board = new int[r][c];
        this.column = c;
        this.row = r;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = 0;
            }
        }

        board[row / 2 - 1][column / 2 - 1] = 1;
        board[row / 2][column / 2] = 1;
        board[row / 2 - 1][column / 2] = -1;
        board[row / 2][column / 2 - 1] = -1;
    }

    //gets the cell starting at 1 not from 0.
    public int getCell(int row, int column) {
        return board[row - 1][column - 1];
    }

    public boolean isBoardfull() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }


    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setCell(Disk disk) {
        board[disk.getRow() - 1][disk.getColumn() - 1] = disk.getColor();
    }

    public void displayBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }
}
