package GameCode;

public abstract class GameRules {
    private int[] score = new int[2];

    public GameRules() {
        score[0] = 2;
        score[1] = 2;
    }

    public int[] getScore() {
        return score;
    }

    public void updateScore(Board b) {
        score[0] = 0;
        score[1] = 0;
        for (int i = 1; i <= b.getRow(); i++) {
            for (int j = 1; j <= b.getColumn(); j++) {
                if (b.getCell(i, j) == 1) {
                    score[0]++;
                } else if (b.getCell(i, j) == -1) {
                    score[1]++;
                }
            }
        }
    }

    public abstract boolean play(Board b, Disk d);

    /**
     * This method checks if a player has any moves
     *
     * @param p - the current player
     * @return true if there is at least one move
     */
    public abstract boolean canPlay(Board b, int p);
}
