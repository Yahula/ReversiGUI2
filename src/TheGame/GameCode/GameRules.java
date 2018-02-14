package TheGame.GameCode;

/**
 * Created by sagi on 15/01/2018.
 */
public abstract class  GameRules {
   private int[] score = new int[2];

   public GameRules(){
      score[0] = 2;
      score[1] = 2;
   }

   public int[] getScore() {
      return score;
   }

   public void setScore(int[] score) {
      this.score = score;
   }

   public void updateScore(int change, int winner){
      if(winner ==1 ){score[0]+=change; score[1]-=change-1;}
      if(winner ==2 ){score[1]+=change; score[0]-=change-1;}
   }

   public abstract boolean play(Board b, Disk d);

   /**
    * This method checks if a player has any moves
    * @param p - the current player
    * @return true if there is at least one move
    */
   abstract boolean canPlay(Board b,Player p);

}
