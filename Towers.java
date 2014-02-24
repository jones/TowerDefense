// IMCOMPLETE
// Controls the towers in the game Tower Defense

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
public class Towers
	implements ActionListener
{
  // Color scheme: 0 = no tower, 1 = red, 2 = green, 3 = blue
  
  // Red, Green, then Blue
  public static int[] towerCost = {12, 8, 10};
  public static int[] towerAttack = {20, 8, 12};
  public static int[] towerRange = {2, 3, 3};
  public static int[] towerDelay = {3000, 800, 1800}; // Milliseconds

  public int row;
  public int col;
  
  public int upgrade;
  public int attack;
  public int range;
  private int delay;
  private int cost;
  public int towerColor = 0;
  private int priceToUpgrade;
  
  public int crow = 0;
  public int ccol = 0;
  private ControlPanel controlPanel;
  private Timer drawTimer;
  private Timer fireTimer;
  public int currentCreep;
  public boolean isSelected;
  public boolean isFiring;
  private int[][] pathLocations;


  public Towers(int towerNumber, int rows, int cols, ControlPanel c)
  {
	controlPanel = c;
  	row = rows;
  	col = cols;
  	towerColor = towerNumber;
  	isSelected = false;
  	
  	upgrade = 1;
  	cost = towerCost[towerColor - 1];
  	attack = towerAttack[towerColor - 1];
  	range = towerRange[towerColor - 1];
  	delay = towerDelay[towerColor - 1];
  	
  	pathLocations = this.findPathLocations();
  	
  	drawTimer = new Timer(delay, this);
  	drawTimer.start();
  	
  	fireTimer = new Timer(delay, this);
  	fireTimer.start();
  }
    
  // Upgrades the selected tower
  public void upgradeTower(int towerType)
  {
	  priceToUpgrade = (int)(1.5 * upgrade * cost);
	  int nextPriceToUpgrade = (int)( 1.5 * (upgrade + 1) * cost);
	  
  	if(controlPanel.getMoney() > priceToUpgrade && upgrade <= 5)
  	{
  	  if (towerType == 1)
  	  {
  		upgrade++;
  	    attack = (int)(attack * 1.5);
  	    range += 1;
  		controlPanel.display.setText("Statistics:\nLevel: " + upgrade + "\nAttack: " + attack + "\nRange: " + range + "\nCost to Uprade: $" + nextPriceToUpgrade);
  	    controlPanel.setMoney( - priceToUpgrade);
  	  }
  	  else if (towerType == 2)
  	  {
  		upgrade++;
  	  	attack += 10;
  	  	range += 1;
  		controlPanel.display.setText("Statistics:\nLevel: " + upgrade + "\nAttack: " + attack + "\nRange: " + range + "\nCost to Uprade: $" + nextPriceToUpgrade);
  	  	controlPanel.setMoney( - priceToUpgrade);
  	  }
  	  else if (towerType == 3)
  	  {
  		upgrade++;
	  	attack = (int)(attack * 1.2);
	  	range += 2;
  	  	controlPanel.display.setText("Statistics:\nLevel: " + upgrade + "\nAttack: " + attack + "\nRange: " + range + "\nCost to Uprade: $" + nextPriceToUpgrade);
	  	controlPanel.setMoney(-priceToUpgrade);
  	  }
  	}
  	else if (controlPanel.getMoney() < priceToUpgrade)
  	  controlPanel.display.setText("Not enough money to upgrade!");
  	else
  	  controlPanel.display.setText("Statistics:\nLevel: " + upgrade + "\nAttack: " + attack + "\nRange: " + range + "\nCannot be upgraded anymore");
  }
  
  // Finds the locations of path in range of the tower
  // Determines if it will make a new array
  public int[][] findPathLocations()
  {
	int[][] temp = new int[18 * 18][2];
	int[][] pathL;
	
	int count = 0;

	for(int x = row - range; x <= row + range; x++)
	{
	  for (int y = col - range; y <= col + range; y++)
	  {
		if(0 < x && x < 18 && 0 < y && y < 18 &&
		  IntMatrix.grid[x - 1][y - 1] < 0)// &&
		 // inRangeCircle(row, col, row - range, col - range, range))
		{
			  temp[count][0] = x;
			  temp[count][1] = y;
			  count++;
		}
	  }
	}
	
	System.out.println();
	pathL = new int[count][2];
	
	for(int i = 0; i < pathL.length; i++)
	{
	  pathL[i][0] = temp[i][0];
	  pathL[i][1] = temp[i][1];
	}
	
	return pathL;
  }
  
  public void attackCreep()
  {
	int x = 0;
	int y = 0;
	
	for(int i = 0; i < pathLocations.length; i++)
	{
	  x = pathLocations[i][0];
	  y = pathLocations[i][1];

	  if(IntMatrix.grid[x - 1][y - 1] == -2) ////////////
	  {
		  int j = CreepLine.getCreepAt(x, y);
		  if(j > -1)
			{
				crow = x;
	  		  ccol = y;
			  isFiring = true;
			  System.out.println("Tower at row: " + row + " col: " + col + " has shot at " + x + " " + y);
	  		  CreepLine.CreepArray[j].damage(attack);
	  		  
	  		  System.out.println(crow + "" + ccol);
	  		  
	  		  return;
			}
	  }
	}
	isFiring = false;
  }
  
  // The x and y pos of the tip of the projectile
  // and the x and y pos and the radius of the circle
  // r is the radius
  // ty keien for the method
   public boolean inRangeCircle(int pX, int pY, int cX, int cY, int r)	
	{
		if(Math.sqrt((double)Math.pow(((cY + r) - pY), 2) + (double)Math.pow(((cX + r) - pX), 2)) <= r)
			return true;
		else
			return false;
	}
   
  public void actionPerformed(ActionEvent e)
  {
 // 	(Timer)src = (Timer)e.getSource();
  	
 // 	if(src == drawTimer)
  	  this.attackCreep();
 // 	else if(src == fireTimer)
  //	  this.
  }
  
  // Accessor methods
    public int getUpgrade()
  {
  	return upgrade;
  }
  
  public int getRange()
  {
    return range;
  }
  
  public int getAttack()
  {
    return attack;
  }
  
  public int getPriceUpgr()
  {
    return priceToUpgrade;
  }
    
  public void setRow(int rowOther)
  {
  	row = rowOther;
  }
  
  public void setCol(int colOther)
  {
  	col = colOther;
  }
}