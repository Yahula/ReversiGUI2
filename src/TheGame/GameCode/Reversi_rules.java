package TheGame.GameCode;

public class Reversi_rules extends GameRules {

    public Reversi_rules() {
    }

    public boolean canPlay(Board b, int p) {

        for (int i = 1; i <= b.getRow(); ++i) {
            for (int j = 1; j <= b.getColumn(); ++j) {
                if (b.getCell(i, j) == 0) {
                    int[] lookarray = new int[8];
                    lookAround(b, new Disk(i, j, p), lookarray);
                    for (int k = 0; k < 8; ++k) {
                        if (lookarray[k] == 1) {
                            boolean bol = lookForDisk(b, new Disk(i, j, p), k, false);
                            if (bol) {
                                return bol;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean play(Board board, Disk disk) {
        boolean didHePlayd = false;

        //checks if to move is ok
        if (disk.getRow() > board.getRow() ||
                disk.getRow() < 0 ||
                disk.getColumn() < 0 ||
                disk.getColumn() > board.getColumn()) {//inside the board bounds
            return false;
        }
        if (board.getCell(disk.getRow(), disk.getColumn()) != 0) {//cell already occupied
            return false;
        }

        int[] allsides = new int[8];
        lookAround(board, disk, allsides);
        for (int i = 0; i < 8; i++) {
            if (allsides[i] == 1) {
                boolean t = lookForDisk(board, disk, i, true);
                if (t) {
                    didHePlayd = true;
                    board.setCell(disk);
                }
            }
        }
        return didHePlayd;
    }

    private void lookAround(Board board, Disk disk, int[] v) {
        if (disk.getColumn() - 1 > 0) {
            if (board.getCell(disk.getRow(), disk.getColumn() - 1) == -disk.getColor()) {
                v[0] = 1;
            }
        }
        if ((disk.getColumn() - 1) > 0 && (disk.getRow() - 1) > 0) {
            if (board.getCell(disk.getRow() - 1, disk.getColumn() - 1) == -disk.getColor()) {
                v[1] = 1;
            }
        }
        if (disk.getRow() - 1 > 0) {
            if (board.getCell(disk.getRow() - 1, disk.getColumn()) == -disk.getColor()) {
                v[2] = 1;
            }
        }
        if (disk.getRow() - 1 > 0 && disk.getColumn() + 1 < board.getColumn()) {
            if (board.getCell(disk.getRow() - 1, disk.getColumn() + 1) == -disk.getColor()) {
                v[3] = 1;
            }
        }
        if (disk.getColumn() + 1 < board.getColumn()) {
            if (board.getCell(disk.getRow(), disk.getColumn() + 1) == -disk.getColor()) {
                v[4] = 1;
            }
        }
        if (disk.getColumn() + 1 < board.getColumn() && disk.getRow() + 1 < board.getRow()) {
            if (board.getCell(disk.getRow() + 1, disk.getColumn() + 1) == -disk.getColor()) {
                v[5] = 1;
            }
        }
        if (disk.getRow() + 1 < board.getRow()) {
            if (board.getCell(disk.getRow() + 1, disk.getColumn()) == -disk.getColor()) {
                v[6] = 1;
            }
        }
        if (disk.getColumn() - 1 > 0 && disk.getRow() + 1 < board.getRow()) {
            if (board.getCell(disk.getRow() + 1, disk.getColumn() - 1) == -disk.getColor()) {
                v[7] = 1;
            }
        }
    }


    private boolean lookForDisk(Board b, Disk d, int direction, boolean changePath) {
        boolean isthereadisk = false;

        switch (direction) {

            case 0:
                for (int i = 2; i < d.getColumn(); ++i) {
                    if (b.getCell(d.getRow(), d.getColumn() - i) == 0) {
                        break;
                    } else {
                        if (b.getCell(d.getRow(), d.getColumn() - i) == d.getColor()) {
                            if (changePath) {
                                for (int j = i - 1; j > 0; --j) {
                                    b.setCell(new Disk(d.getRow(), d.getColumn() - j, d.getColor()));
                                }
                            }
                            isthereadisk = true;
                            break;
                        }
                    }
                }
                break;

            case 1:
                for (int i = 2; i < d.getColumn() && i < d.getRow(); ++i) {
                    if (b.getCell(d.getRow() - i, d.getColumn() - i) == 0) {
                        break;
                    }
                    if (b.getCell(d.getRow() - i, d.getColumn() - i) == d.getColor()) {
                        if (changePath) {
                            for (int j = i - 1; j > 0; --j) {
                                b.setCell(new Disk(d.getRow() - j, d.getColumn() - j, d.getColor()));
                            }
                        }
                        isthereadisk = true;
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 2; i < d.getRow(); ++i) {
                    if (b.getCell(d.getRow() - i, d.getColumn()) == 0) {
                        break;
                    }
                    if (b.getCell(d.getRow() - i, d.getColumn()) == d.getColor()) {
                        if (changePath) {
                            for (int j = i - 1; j > 0; --j) {
                                b.setCell(new Disk(d.getRow() - j, d.getColumn(), d.getColor()));
                            }
                        }
                        isthereadisk = true;
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 2; d.getColumn() + i <= b.getColumn() && i < d.getRow(); ++i) {
                    if (b.getCell(d.getRow() - i, d.getColumn() + i) == 0) {
                        break;
                    }
                    if (b.getCell(d.getRow() - i, d.getColumn() + i) == d.getColor()) {
                        if (changePath) {
                            for (int j = i - 1; j > 0; --j) {
                                b.setCell(new Disk(d.getRow() - j, d.getColumn() + j, d.getColor()));
                            }
                        }
                        isthereadisk = true;
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 2; d.getColumn() + i <= b.getColumn(); ++i) {
                    if (b.getCell(d.getRow(), d.getColumn() + i) == 0) {
                        break;
                    }
                    if (b.getCell(d.getRow(), d.getColumn() + i) == d.getColor()) {
                        if (changePath) {
                            for (int j = i - 1; j > 0; --j) {
                                b.setCell(new Disk(d.getRow(), d.getColumn() + j, d.getColor()));
                            }
                        }
                        isthereadisk = true;
                        break;
                    }
                }
                break;
            case 5:
                for (int i = 2; d.getColumn() + i <= b.getColumn() && d.getRow() + i <= b.getRow(); ++i) {
                    if (b.getCell(d.getRow() + i, d.getColumn() + i) == 0) {
                        break;
                    }
                    if (b.getCell(d.getRow() + i, d.getColumn() + i) == d.getColor()) {
                        if (changePath) {
                            for (int j = i - 1; j > 0; --j) {
                                b.setCell(new Disk(d.getRow() + j, d.getColumn() + j, d.getColor()));
                            }
                        }
                        isthereadisk = true;
                        break;
                    }
                }
                break;
            case 6:
                for (int i = 2; d.getRow() + i <= b.getRow(); ++i) {
                    if (b.getCell(d.getRow() + i, d.getColumn()) == 0) {
                        break;
                    }
                    if (b.getCell(d.getRow() + i, d.getColumn()) == d.getColor()) {
                        if (changePath) {
                            for (int j = i - 1; j > 0; --j) {
                                b.setCell(new Disk(d.getRow() + j, d.getColumn(), d.getColor()));
                            }
                        }
                        isthereadisk = true;
                        break;
                    }
                }
                break;
            case 7:
                for (int i = 2; i < d.getColumn() && d.getRow() + i <= b.getRow(); ++i) {
                    if (b.getCell(d.getRow() + i, d.getColumn() - i) == 0) {
                        break;
                    }
                    if (b.getCell(d.getRow() + i, d.getColumn() - i) == d.getColor()) {
                        if (changePath) {

                            for (int j = i - 1; j > 0; --j) {
                                b.setCell(new Disk(d.getRow() + j, d.getColumn() - j, d.getColor()));
                            }
                        }
                        isthereadisk = true;
                        break;
                    }
                }
                break;
        }
        return isthereadisk;
    }
}
