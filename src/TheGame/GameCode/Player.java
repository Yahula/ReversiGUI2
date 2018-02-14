package TheGame.GameCode;

/**
 * Created by sagi on 15/01/2018.
 */
abstract class Player {

    int pNum = 0;
    boolean isRemote = false;

    public Player(int pNum, boolean isRemote) {
        this.pNum = pNum;
        this.isRemote = isRemote;
    }

    abstract Disk move();


    public boolean getIsRemote() {
        return isRemote;
    }

    public void setIsRemote(boolean isRemote) {
        isRemote = isRemote;
    }


    public int getpNum() {
        return pNum;
    }

}
