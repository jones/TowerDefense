// This class represents each individual square panel making up the Tower Defense "field"

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel
{
  private int towerPlaced; // Color Scheme: 0 = none; 1 = red; 2 = green; 3 =blue
  private boolean isCreep; //false = no creep, true = creep
  private boolean isPath; // Determines if the panel is part of the enemy path
  private int position = 0; // Color Scheme: 0 = horizontal; 1 = vertical;
  
  //
  public GridPanel(boolean path)
  {
  	towerPlaced = 0;
  	isPath = path;
  	position = 0; ///////////// CHANGE LATER
  	if (isPath)
  	{
  	  setBackground(Color.WHITE);
  	}
  	else if (!isPath)
  	{
  	  setBackground(Color.GRAY);
  	}
  }
  
  public void paintComponent(Graphics g)
  {
  	int w = getWidth();
  	int h = getHeight();
  	super.paintComponent(g);
  	
  	if (isPath)
  	{
  	  g.setColor(Color.red);
  	  
  	  if (position == 0) //Horizontal squares
  	  {
  	    g.drawLine(0, 0, w, 0);
  	    g.drawLine(0, h, w, h);
  	  }
  	  else if (position == 1) //Vertical squares
  	  {
  	    g.drawLine(0, 0, 0, h);
  	    g.drawLine(w, 0, w, h);
  	  }
  	  else if(isCreep) //Creep
  	  {
  	  	g.setColor(Color.blue);
  	  	g.fillOval(w / 4, h / 4, 3*w / 4, 3*h / 4);
  	  }
    }
    else if(!isPath)
    {
      if(towerPlaced > 0)
      {
      	if(towerPlaced == 1) //Red
      	{
      	  g.setColor(Color.red);
      	  g.fillRect(0, 0, w, h);
      	}
      	else if(towerPlaced == 2) //Green
      	{
      	  g.setColor(Color.red);
      	  g.fillRect(0, 0, w, h);
      	}
      	else // Blue
      	{
      	  g.setColor(Color.blue);
      	  g.fillRect(0, 0, w, h);
      	}
      }
      else
      {
      	setBackground(Color.gray);
      }
    }
  }
  
  //Accessor methods
  public int getTowerPlaced()
  {
  	return towerPlaced;
  }
  
  public int getPosition()
  {
  	return position;
  }
  
  public boolean getPath()
  {
  	return isPath;
  }
   
  public boolean isCreep()
  {
  	return isCreep;
  }
  
  // This lets each item in GridPanel be changed
  // Code: -2 = creep, -1 = path, 0 = no tower, 1 = red, 2 = green, 3 = blue
  public void setItem(int value)
  {
  	
  	if(value == -2) // Creep
  	{
  	  if(!isPath)
  	    System.out.println("Error! Creep is on path!");
  	  isCreep = true;
  	  isPath = true;
  	  towerPlaced = 0;
  	} 
  	else if(value == -1) // Path
  	{
  	  towerPlaced = 0;
  	  isPath = true;
  	} 
  	else if(value == 0) // no tower
  	{
  	  //For deleting towers (not implemented yet)
  	}
  	else if(value > 0) // Tower
  	{
  	  if(isPath)
  	    System.out.println("Cannot build on path");
  	  else if(towerPlaced > 0)
  	    System.out.println("Cannot build tower on tower");
  	  else
  	  {
  	  	towerPlaced = value;
  	  }
  	}
  	else
  	  System.out.println("Error! (Gp105)");
  }
}