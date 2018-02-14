package TheGame.GameCode;

import java.util.Scanner;

/**
 * Created by sagi on 15/01/2018.
 */
public class HumanPlayer extends Player{


    public HumanPlayer(int pNum, boolean isRemote) {
        super(pNum, isRemote);
    }

    @Override
    Disk move() {
        int row, col;

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a number: ");
         row = reader.nextInt();
        col = reader.nextInt();
        reader.close();


        Disk d = new Disk(row-1,col-1, pNum);

        return d;
    }

}
