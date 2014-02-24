// This class controls an array of Creeps
// They are sent when the "next wave" button is pressed
// 

import java.awt.event.*;
import javax.swing.*;

public class CreepLine
	implements ActionListener
{
	public static final int WAVE_SIZE = 10;
	public static Creep[] CreepArray;
	public int CREEP_DELAY;
	
	private ControlPanel controlPanel;
	private Timer time;
	private static int count;
	
	public CreepLine(ControlPanel cPanel)
	{
	  controlPanel = cPanel;
	  CreepArray = new Creep[WAVE_SIZE];
	  
	  // First - harder but less consistent performance
	  // Second - stable but easier
	  // CREEP_DELAY = (int)(Creeps.TIMER_DELAY * 1.01);
	  CREEP_DELAY = (int)(Creep.TIMER_DELAY * 2.01);
	  
	  time = new Timer(CREEP_DELAY, this);
	  time.start();
	  
      count = 0;
	}
	
	// Removes a creep
	public void removeCreep(int num)
	{
	  CreepArray[num] = null;
	}
	
	// Destroys the entire wave (in making new game)
	public static void destroyWave()
	{
	  CreepArray = null;
	  CreepArray = new Creep[WAVE_SIZE];
	}
	
	public static int getCreepAt(int row, int col)
	{
	  for(int i = 0; i < count; i++)
	  {
		if(CreepArray == null)
		{
			System.out.println(-2)	;
		    return -2;
		}
		else if (CreepArray[i] != null && CreepArray[i].row == row && CreepArray[i].col == col)
		{
			System.out.println(i);
			return i;
		}
   		  
	  }
	  System.out.println(-1);
	  return -1;
	}

	//Called every few seconds by fireTimer
	public void actionPerformed(ActionEvent e)
	{
	  // Makes the creeps in the wave
	  if(count < WAVE_SIZE && CreepArray != null)
	  {
	    CreepArray[count] = new Creep(controlPanel, count);
		count++;
	  }
	  else if(count == WAVE_SIZE)
	    time.stop();
	}
}