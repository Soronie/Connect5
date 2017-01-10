
/**
 * Connect 5 Game - with mushrooms! :D
 * 
 * @author Rafaeli Arroyo
 * @version January 10, 2013
 */

import javax.swing.SwingUtilities;

public class Connect5
{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                GameMechanics game = new GameMechanics();
                game.setVisible(true);  //Makes the game visible to play.
            }
        });
   }
}
