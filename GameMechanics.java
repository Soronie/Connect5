/**
 * Connect 5 Game
 *
 * @author Rafaeli Arroyo
 * @version January 9, 2013
 *
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.border.EtchedBorder;
import java.awt.event.*;
import javax.swing.*;

public class GameMechanics extends JFrame implements ActionListener {
    private JPanel centerPanel;
    private JButton button;
    private int[][] pos;
    private int width, height;
    private boolean player1turn = true;
    private boolean gameover = false;

   public GameMechanics() {
        initUI();
    }
   public final void initUI() {
        /* Counts an array of components whose buttonIndex is equal to 0.  Each button with an index of 0 is a question box.
         * Buttons with a buttonIndex of 0 are empty; a piece can be laid on them.
         */
        pos = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                };
       centerPanel = new JPanel();
       centerPanel.setLayout(new GridLayout(12, 12, 0, 0));
       add(Box.createRigidArea(new Dimension(0, 9)), BorderLayout.NORTH); //Creates the dimensions and borders of the game.
       add(Box.createRigidArea(new Dimension(0, 9)), BorderLayout.SOUTH);
       add(Box.createRigidArea(new Dimension(0, 9)), BorderLayout.WEST);
       add(Box.createRigidArea(new Dimension(0, 9)), BorderLayout.EAST);
       add(centerPanel);

       /* JButtons are instantiated
        * JButtons fill the 12x12 grid.  Each JButton's display is set to a question box.
        */
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                    button = new JButton();
                    button.addActionListener(this);
                    centerPanel.add(button);
                    button.setIcon(new ImageIcon(GameMechanics.class.getResource("question_box.png")));
                }
            }
        setSize(950, 950);
        setTitle("Connect 5");
        setResizable(true);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
   /*
     * @param ActionEvent e senses the mouse-click on a JButton.
     */
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource(); //obtains information on a change to a JButton by a mouse-click
        Dimension size = button.getSize(); //obtains the size of a JButton

        int buttonX = button.getX(); //obtains the x-coordinate of button.
        int buttonY = button.getY(); //obtains the y-coordinate of button.
        int buttonPosX = buttonX / size.width; //x-coordinate of a JButton
        int buttonPosY = buttonY / size.height; //y-coordinate of a JButton
        int buttonIndex = pos[buttonPosY][buttonPosX];  //defines a JButton's index depending on y- and x-coordinates on the grid
        //if a player clicks on a tile with a question mark while the game is resuming, he/she can place a red mushroom.
        if(player1turn && buttonIndex == 0 && !gameover)
        {
          pos[buttonPosY][buttonPosX] = 1; //A button with a red mushroom has a buttonIndex of 1.
          button.setIcon(new ImageIcon("red_mushroom.png")); //A button's icon is changed to a red mushroom.
          player1turn = false; //After a button's icon changes, it is no longer this user's turn.  It is the other's.
        }
        else if (buttonIndex == 0 && !gameover)
        {
          pos[buttonPosY][buttonPosX] = 2; //buttonIndex of 2 indicates a Green mushroom.
          button.setIcon(new ImageIcon("green_mushroom.png"));
          player1turn = true; //after a Green piece is placed, it is once again the red player's turn.
        }
        
        gameover = checkVictory(pos[buttonPosY][buttonPosX]);
}

  public boolean checkVictory(int mushroom)
  {
      int row;
      int col;

        for(row = 0; row < 8; row++)
        {
          for(col = 0; col < 8; col++)
            if(pos[row][col] == mushroom && ((pos[row][col+1] == mushroom && pos[row][col+2] == mushroom && pos[row][col+3] == mushroom && pos[row][col+4] == mushroom) ||//horizontal
                                             (pos[row+1][col] == mushroom && pos[row+2][col] == mushroom && pos[row+3][col] == mushroom && pos[row+4][col] == mushroom) ||//vertical
                                             (pos[row+1][col+1] == mushroom && pos[row+2][col+2] == mushroom && pos[row+3][col+3] == mushroom && pos[row+4][col+4] == mushroom)))//diagonal
              return true;
            
          for(col = 4; col < 12; col++)
            if(pos[row][col] == mushroom && (pos[row+1][col-1] == mushroom && pos[row+2][col-2] == mushroom && pos[row+3][col-3] == mushroom && pos[row+4][col-4] == mushroom))//diagonal 2
              return true;
        }
        
            return false;
  }
}