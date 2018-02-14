package TheGame.GameCode;

/**
 * Created by sagi on 16/01/2018.
 */
public class Game {

    private Board myboard;
    private GameRules gamerules;
    private Player[] players = new Player[2];
    private boolean isRemoteGame = false;
    private int endFlag;
    private boolean firstTurn;


    public Game() {
        this.myboard = new Board(10, 10);
        this.gamerules = new Reversi_rules();

        this.isRemoteGame = false;

        this.players[0] = new HumanPlayer(-1, isRemoteGame);
        this.players[1] = new HumanPlayer(1, isRemoteGame);
        this.endFlag = 0;
        firstTurn = true;
    }


    public void playGame() {
        int player = 0;
        int noMoves = 0;
        myboard.displayBoard();

        while (true) {
            if (myboard.isBoardfull()) {
                //cout << "Board Full. Game Over!" << endl;
                break;
            } else {
                int n = playerPlay(players[player]);
                player = (player + 1) % 2;

                if (n == 0) {
                    noMoves = 0;
                } else if (n == 1) {
                    noMoves++;
                    if (noMoves == 1) {
                        continue;
                    }
                    if (noMoves == 2) {
                        //cout << "Game Over" << endl;
                        break;
                    }

                } else if (n == 2) {
                    //this->client->readFromServer();
                    return;
                }
            }
        }
    }

    public int playerPlay(Player player) {
        Disk d;

        if (this.gamerules.canPlay(myboard, player)) {
            boolean thereWasAMove;
            d = new Disk(player.move());

            thereWasAMove = this.gamerules.play(myboard, d);
            while (!thereWasAMove) {
                //cout << "oops! not there! try agian: " << endl;
                d = new Disk(player.move());
                thereWasAMove = this.gamerules.play(myboard, d);
            }
//        if (this.isRemoteGame &&!player.getIsRemote()) {
//        //this.client.writeToServer(d);
//        }
//        //cout << endl << "Score - " << "White (O): " << this.gamerules.getScore()[0] << ", Black (X): "
//      //  << this.gamerules.getScore()[1] << endl << endl;
//            this.myboard.displayBoard();
//            this.endFlag = 0;
//            return 0;
//        } else {
////             if (this.isRemoteGame){
//                 if(endFlag){
//                 return 2;
//                 }
//                 if(!player.getIsRemote()) {
//                    char m[] = "nmv";
//                     this.client.writeStringToServer(m);
//                      this.endFlag = 1;
//                 }else{
//                    this.client.readFromServer();
//                    }
//                return 0;

             } else {
            //cout << "No moves!" << endl;
            return 1;}
            //}
            //}
        return 0;
        }

    }

