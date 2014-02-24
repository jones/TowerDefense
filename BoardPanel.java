// Implements a board for the Chomp program

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel
	implements MouseListener
{
  private final int creep = -2, path = -1, empty = 0, rTower = 1, gTower = 2, bTower = 3;
  private final int ROWS = 18, COLS = 18;   // board dimensions
  private final int CELLSIZE = 30;
  
  private Color pathColor;
  private Color emptyColor;
  private Color creepColor;
  private Color highlightedColor;
  private Color towerBase;
  private static int upgradeable;
   
  private ControlPanel controlPanel;
  private ChompGame game;
  public static ArrayList<Towers> towerGroup;

  // Constructor
  public BoardPanel(ControlPanel cPanel)
  {
    upgradeable = 0;
  	pathColor = Color.YELLOW;
  	emptyColor = Color.GREEN.darker().darker();
  	creepColor = Color.BLACK;
  	highlightedColor = Color.GRAY;
  	towerBase = Color.LIGHT_GRAY;
  	
  	controlPanel = cPanel;
    setBackground(Color.LIGHT_GRAY);
    addMouseListener(this);
    towerGroup = new ArrayList<Towers>(10);
  }

  // Returns the number of rows in the board
  public int numRows()
  {
    return ROWS;
  }

  // Returns the number of columns in the board
  public int numCols()
  {
    return COLS;
  }

  private class Location
  {
  	private int rowX, colX;

  	// Constructor
  	public Location(int r, int c)
  	{
    	rowX = r;
    	colX = c;
  	}

  	// Returns this location's row
  	public int getRow()
 	{
  	  return rowX;
  	}

  	// Returns this location's column
  	public int getCol()
 	{
      return colX;
  	}
  }

  // Returns the location that corresponds to the x,y-coordinates
  // of the mouse click on the board
  public Location getPos(int x, int y)
  {
    return new Location(y / CELLSIZE, x / CELLSIZE);
  }



  // Repaints the board after creeps traverse across the path or when a tower is built
  public void update(ChompGame game)
  {
    this.game = game;
    repaint();
  }
  
  // remembe that it is +1
  public int getTowerIndexAtLocation(int row, int col)
  {
  	for(int i = 0; i < towerGroup.size(); i++)
  	{
  	  if(towerGroup.get(i).row == row && towerGroup.get(i).col == col)
  	    return i;
  	}
  	return -1;
  }
  
  public void mouseReleased(MouseEvent e)
  {
  	Location pos = this.getPos(e.getX(), e.getY());
    int row = pos.getRow();
    int col = pos.getCol();
    
    int towerColor = controlPanel.towerColor;
    boolean gameOver = controlPanel.getGameOver();
    int towerCost = 0;
    
    
    IntMatrix.selRow = row + 1;
    IntMatrix.selCol = col + 1;
    
    // For debugging purposes
    System.out.println("Row: " + (row + 1) + " Col: " + (col + 1));
    
    if(towerColor > 0)
      towerCost = Towers.towerCost[towerColor - 1];

    if(!gameOver && towerColor > 0)
    {
      if(IntMatrix.grid[row][col] != empty)
      {
    	controlPanel.display.setText("You cannot build a tower there.");
    	controlPanel.towerColor = 0;
      }
      else if(controlPanel.getMoney() < towerCost)
        controlPanel.display.setText("Not enough money to build a tower.");
      else
      {
    	towerGroup.add(new Towers(towerColor, row + 1, col + 1, controlPanel));
    	System.out.println("Row: " + (row + 1) + " Col: "+ (col + 1));
    	IntMatrix.grid[row][col] = towerColor;
    	controlPanel.setMoney(-1 * towerCost);
      }
    }
    else
      controlPanel.display.setText("Please select a tower.");
  }
  
  public void mouseClicked(MouseEvent e)
  {
  	Location pos = this.getPos(e.getX(), e.getY());
    int row = pos.getRow();
    int col = pos.getCol();
    
    int count = getTowerIndexAtLocation(row + 1, col + 1);
    if(count == -1)
    	return;
    int count2 = IntMatrix.grid[row][col];
    
    if (IntMatrix.grid[row][col] == rTower)
    {
      upgradeable = rTower;
      controlPanel.display.setText("" + count);
	  if (towerGroup.get(count).getUpgrade() <= 5)
        controlPanel.display.setText("Statistics:\nLevel: " + towerGroup.get(count).getUpgrade() + "\nAttack: " + towerGroup.get(count).getAttack() + "\nRange: " + towerGroup.get(count).getRange() + "\nCost to Upgrade: $" + towerGroup.get(count).getPriceUpgr());
      else if (towerGroup.get(count).getUpgrade() >= 6)
        controlPanel.display.setText("Statistics:\nLevel: " + towerGroup.get(count).getUpgrade() + "\nAttack: " + towerGroup.get(count).getAttack() + "\nRange: " + towerGroup.get(count).getRange() + "\nCannot be upgraded anymore");
    }
    else if (IntMatrix.grid[row][col] == gTower)
    {
      upgradeable = gTower;
      controlPanel.display.setText("" + count);
      if (towerGroup.get(count).getUpgrade() <= 5)
        controlPanel.display.setText("Statistics:\nLevel: " + towerGroup.get(count).getUpgrade() + "\nAttack: " + towerGroup.get(count).getAttack() + "\nRange: " + towerGroup.get(count).getRange() + "\nCost to Upgrade: $" + towerGroup.get(count).getPriceUpgr());
      else if (towerGroup.get(count).getUpgrade() >= 6)
        controlPanel.display.setText("Statistics:\nLevel: " + towerGroup.get(count).getUpgrade() + "\nAttack: " + towerGroup.get(count).getAttack() + "\nRange: " + towerGroup.get(count).getRange() + "\nCannot be upgraded anymore");
    }
    else if (IntMatrix.grid[row][col] == bTower)
    {
      upgradeable = bTower;
      controlPanel.display.setText("" + count);
      if (towerGroup.get(count).getUpgrade() <= 5)
        controlPanel.display.setText("Statistics:\nLevel: " + towerGroup.get(count).getUpgrade() + "\nAttack: " + towerGroup.get(count).getAttack() + "\nRange: " + towerGroup.get(count).getRange() + "\nCost to Upgrade: $" + towerGroup.get(count).getPriceUpgr());
      else if (towerGroup.get(count).getUpgrade() >= 6)
        controlPanel.display.setText("Statistics:\nLevel: " + towerGroup.get(count).getUpgrade() + "\nAttack: " + towerGroup.get(count).getAttack() + "\nRange: " + towerGroup.get(count).getRange() + "\nCannot be upgraded anymore");
    }
  }
  public static void destroyTowers()
  {
	towerGroup = new ArrayList<Towers>(10);
  }
  
  // Unused Methods
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e)  {}
  public void mousePressed(MouseEvent e) {}
  
  // Displays the board after a repaint request
  // (redefines the method of the base class)
  public void paintComponent(Graphics g)
  {
  	super.paintComponent(g);
  	
	Color color;
    int value;

    for (int r = 1; r < ROWS + 1; r++)
    {
      for (int c = 1; c < COLS + 1; c++)
      {
      	value = IntMatrix.grid[r-1][c-1];
      	
        if (game.isEmpty(r - 1, c - 1))
          color = emptyColor;
        else if(game.isPath(r - 1, c-1) || game.isCreep(r - 1, c - 1))
          color = pathColor;
        else if (IntMatrix.selRow == (r) && IntMatrix.selCol == c && value > 0)
          color = highlightedColor;
        else
          color = towerBase;
          
        g.setColor(color);
        int x = (c-1) * CELLSIZE;
        int y = (r-1) * CELLSIZE;
        // Fills up each cell
        g.fillRect(x, y, CELLSIZE, CELLSIZE);
        g.setColor(Color.BLACK);
        // Draws border of each cell
        g.drawRect(x, y, CELLSIZE, CELLSIZE);
        g.drawRect(x+1, y+1, CELLSIZE-2, CELLSIZE-2);
        
        
        // Draws a creep
        if (value == creep)
        {
          g.setColor(creepColor);
          g.fillOval(CELLSIZE * (c-1) + 5, CELLSIZE * (r-1) + 5, CELLSIZE - 10, CELLSIZE - 10);
        //  g.setColor(Color.YELLOW);
         // g.fillRect(CELLSIZE * (c-1) + 2, CELLSIZE * (r-1) + 5, CELLSIZE - 2, 3);
        }
        else if(value > 0) // if it is a tower
        {
        //  g.setColor(Color.LIGHT_GRAY);
        //  g.fillRect(x, y, CELLSIZE, CELLSIZE);
          g.setColor(Color.BLACK);
          g.fillOval(CELLSIZE * (c-1) + 5, CELLSIZE * (r-1) + 9, CELLSIZE - 10, CELLSIZE - 10);
          
          if(value == rTower)
          	g.setColor(Color.RED);
          else if(value == bTower)
          	g.setColor(Color.CYAN);
          else // Green Tower
            g.setColor(Color.GREEN);
            
          g.fillOval(CELLSIZE * (c-1) + 5, CELLSIZE * (r-1) + 4, CELLSIZE - 10, CELLSIZE - 10);
          g.setColor(Color.BLACK);
          g.fillRect(CELLSIZE * (c-1) + 13, CELLSIZE * (r-1) + 1, 5, 15);
          g.drawRect(x, y, CELLSIZE, CELLSIZE);
          g.drawRect(x+1, y+1, CELLSIZE-2, CELLSIZE-2);
        }
      }
    }
    repaint();
  }
  
  public static int getUpgradeable()
  {
  	return upgradeable;
  }
}
