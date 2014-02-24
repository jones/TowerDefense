// This class represents the monsters in the game
// A group (or wave) of these is created by CreepsLine
// Each "Creep" is created with an instance of ControlPanel and a count,
// which is used to tell which creep is which
// The money and health value of each increases with every wave


import java.awt.event.*;
import javax.swing.*;

public class Creep
        implements ActionListener
{
	public static int TIMER_DELAY = 500; //in milliseconds
	
	public int HEALTH = 5;
	public int MONEY_VALUE = 5;
	
	public int count;
	public int row;
	public int col;
	
	private boolean isDead;
	private boolean start;
	
	private ControlPanel controlPanel;
	private Timer time;
	

	public Creep(ControlPanel cPanel, int countV)
	{
	  controlPanel = cPanel;
	  HEALTH += (int)((controlPanel.getWave() - 1) * 1.25);
	  MONEY_VALUE += (int)(controlPanel.getWave() * 1.5);
	  controlPanel.creepStatus.setText("Creep Status\n\nHealth: " + HEALTH + "\nMoney Value: $" + MONEY_VALUE);
  
      col = 4;
      row = 1;
      count = countV;
      isDead = false;
      start = true;
      
      time = new Timer(TIMER_DELAY, this);
	  time.start();
	}

	// Damages creeps, and also adds money earned from dead Creeps
	public void damage(int damage)
	{
	  HEALTH -= damage;
	  if(HEALTH < 1)
	  {
	  	System.out.println("Creep Dead at row: " + row + " col: " + col);
	  	controlPanel.setMoney(MONEY_VALUE);
	  	isDead = true;
	  	IntMatrix.grid[row-1][col-1] = -1;
	  	time.stop();
		CreepLine.CreepArray[count] = null;
	  }
	}

	// Called every few seconds by Timer
	public void actionPerformed(ActionEvent e)
	{
		if(!isDead && controlPanel.getLives() > 0)
		{
		  if(start)
		  {
			IntMatrix.grid[0][3] = -2;
			start = false;
		  }
		  else
		    this.move();
		}

	}

	// Moves the creep ahead one square
	// It sets the current space to path, then the next space to creep
	// Note: creep = -2, path = -1, empty = 0, rTower = 1, gTower = 2, bTower = 3;
 	public void move()
    {
      IntMatrix.grid[row - 1][col -1] = -1; //current space to path
      
      // Down movement
  	  if(col == 4 && row < 4 || col == 2 && 3 < row && row < 7 ||
  			  col == 16 && 2 < row && row < 6 || col == 10 && 5 < row && row < 12 ||
  			  col == 9 && 11 < row && row < 14 || col == 2 && 13 < row && row < 17 ||
  			  col == 14 && 13 < row && row < 17)
      {
  		if(row == 3 && col == 9) //fix first space glitch  2 6
  	  	  IntMatrix.grid[0][3] = -1;
        IntMatrix.grid[row][col - 1] = -2;
  		row++;
      }
  	  // Up movement
  	  else if(col == 7 && 3 < row && row < 8 || col == 2 && 14 < row && row < 17 ||
  			  col == 12 && 14 < row && row < 18 || col == 16 && 9 < row && row < 18 ||
  			  row == 9 && col == 17)
      {
        IntMatrix.grid[row - 2][col -1] = -2;
        row--; 
      } 
  	  // Right movement
	  else if(row == 7 && 1 < col && col < 7 || row == 3 && 5 < col && col < 16 ||
			  row == 17 && 1 < col && col < 12 || row == 14 && 11 < col && col < 14 ||
			  row == 17 && 13 < col && col < 16 || row == 9 && col == 16 ||
			  row == 8 && col == 17)
	  {
		IntMatrix.grid[row - 1][col] = -2;  
		col++;
	  }
  	  // Left movement
	  else if(row == 4 && 2 < col && col < 5 || row == 6 && 10 < col && col < 17 ||
			  row == 12 && col == 10 || row == 14 && 2 < col && col < 10)
      {
    	IntMatrix.grid[row - 1][col -2] = -2;
    	col--;
      }
	  else // if it is in the last space
	  {  
		IntMatrix.grid[row - 1][col -1] = -1;
		controlPanel.setLives(-1);
		controlPanel.display.setText("You lost a life\nYou now have " + controlPanel.getLives() + " lives");
		isDead = true;
		if (controlPanel.getLives() < 1)
		  controlPanel.gameOver();
	  }
    }
}

