// The control panel for the Tower Defense game

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import javax.swing.event.*;  [ChangeListener]

public class ControlPanel extends JPanel
	implements ActionListener, MouseListener

{
  private boolean gameIsOver = false;
  private int empty = 0, path = -1, creep = -2;
  public static JTextArea display, creepStatus; // displays tower status or error message, shows status of current Creeps
  public JButton redTower, greenTower, blueTower, nextWave, upgradeT;
  public JLabel dollarSign, lifeLabel, waveLabel;
  public JTextField moneyField, lifeField, waveField;
  public int money, lives, wave;

  public CreepLine creepLine;
  public int towerColor;
  
  public ControlPanel()
  {  	  	
	money = 5000;
	lives = 10;
	wave = 0;
	towerColor = 0;
	
	JPanel towerPanel = new JPanel();
  	towerPanel.setLayout(new GridLayout(3, 1, 1, 1));
  	redTower = new JButton("Red Tower", null);
  	redTower.addActionListener(this);
  	redTower.addMouseListener(this);
  	greenTower = new JButton("Green Tower", null);
  	greenTower.addActionListener(this);
  	greenTower.addMouseListener(this);
  	blueTower = new JButton("Blue Tower", null);
  	blueTower.addActionListener(this);
  	blueTower.addMouseListener(this);
  	towerPanel.add(redTower);
  	towerPanel.add(greenTower);
  	towerPanel.add(blueTower);
  	
	JPanel statusPanel = new JPanel();
  	statusPanel.setLayout(new GridLayout(3, 2, 1, 1));
   	
  	dollarSign = new JLabel("$ = ");
  	moneyField = new JTextField("" + money, 10);
  	moneyField.setEditable(false);
  	statusPanel.add(dollarSign);
  	statusPanel.add(moneyField);
  	
  	lifeLabel = new JLabel("Lives = ");
  	lifeField = new JTextField("" + lives, 10);
  	lifeField.setEditable(false);
  	statusPanel.add(lifeLabel);
  	statusPanel.add(lifeField);
  	
  	waveLabel = new JLabel("Wave = ");
  	waveField = new JTextField("" + wave, 10);
  	waveField.setEditable(false);
  	statusPanel.add(waveLabel);
  	statusPanel.add(waveField);
  	
  	JPanel wavePanel = new JPanel();
  	nextWave = new JButton("Send the Next Wave");
  	nextWave.addActionListener(this);
  	upgradeT = new JButton("Upgrade");
  	upgradeT.addActionListener(this);
  	upgradeT.addMouseListener(this);
  	wavePanel.add(upgradeT);
  	wavePanel.add(nextWave);
  	
  	display = new JTextArea("<Display>", 10, 10);
  	display.setEditable(false);
  	
  	creepStatus = new JTextArea("Creep Status", 6, 6);
  	creepStatus.setEditable(false);
 		
  	Box panelBox = Box.createVerticalBox();
  	panelBox.add(towerPanel);
  	panelBox.add(display);
  	panelBox.add(statusPanel);
  	panelBox.add(wavePanel);
  	panelBox.add(Box.createVerticalStrut(20));
  	panelBox.add(creepStatus);
  	add(panelBox);
  }
  
  public void startNewGame()
  {
  	gameIsOver = false;	
  	
  	// Resets the ControlPanel
  	money = 50;
  	moneyField.setText("" + money);
  	lives = 10;
  	lifeField.setText("" + lives);
  	wave = 0;
  	waveField.setText("" + wave);
  	redTower.setEnabled(true);
  	blueTower.setEnabled(true);
  	greenTower.setEnabled(true);
  	nextWave.setEnabled(true);
  	upgradeT.setEnabled(true);
  	display.setText("<Display>");
  	creepStatus.setText("Creep Status");
  }
  
  public void gameOver()
  {
  	gameIsOver = true;
  	redTower.setEnabled(false);
  	blueTower.setEnabled(false);
  	greenTower.setEnabled(false);
  	nextWave.setEnabled(false);
  	upgradeT.setEnabled(false);
  	display.setText("Game Over!\n\nYour Score: " + getWave());
  }
  
  // Set the money in the ControlPanel's moneyField. The given int in the parameter is added to the current money in the
  // moneyField. To subtract values, type in a negative number as the parameter
  public void setMoney(int amtMoney)
  {
  	String str = moneyField.getText();
  	money = Integer.parseInt(str);
  	money += amtMoney;
  	moneyField.setText("" + money);
  }

  // Set the number of lives in the ControlPanel's lifeField. The principles are the same as in setMoney
  public void setLives(int update)
  {
  	String str = lifeField.getText();
  	lives = Integer.parseInt(str);
    lives += update;
  	lifeField.setText("" + lives);
  }


	// Access Methods
  public int getMoney()
  {
  	return money;
  }
		
  public int getLives()
  {
	return lives;
  }
		
  public int getWave()
  {
	return wave;
  }
  
  public int getTower()
  {
  	return towerColor;
  }
  
  public boolean getGameOver()
  {
  	return gameIsOver;
  }
  
  public void actionPerformed(ActionEvent e)
  {
  	JButton src = (JButton)e.getSource();
  	boolean isUp = false;
  	
  	if (src == redTower)
  	{
  		isUp = false;
  	    towerColor = 1;
  	}
  	else if (src == greenTower)
  	{
  		isUp = false;
  	    towerColor = 2;
  	}
  	else if (src == blueTower)
  	{
  		isUp = false;
  	    towerColor = 3;
  	}
  	else if (src == upgradeT)
  	{

  	  int upgr = BoardPanel.getUpgradeable();
  	  for (int i = 0; i < BoardPanel.towerGroup.size(); i++)
  	  {
  		  if (BoardPanel.towerGroup.get(i).towerColor == upgr)
  			  BoardPanel.towerGroup.get(i).upgradeTower(upgr);
  		  	  isUp = true;
  	  }
  	  if (!isUp)
  		  display.setText("Please select a tower to upgrade");
  	}
  	else if (src == nextWave)
  	{
	  if (IntMatrix.grid[0][3] == creep || IntMatrix.grid[6][1] == creep || IntMatrix.grid[6][6] == creep || IntMatrix.grid[2][9] == creep ||
	  		IntMatrix.grid[2][15] == creep || IntMatrix.grid[5][12] == creep || IntMatrix.grid[10][9] == creep || IntMatrix.grid[13][4] == creep)
	  { 
  	  	display.setText("The current wave needs to advance first!");
 	  }
 	  else
 	  {
 	  	creepLine = new CreepLine(this);
 	 // 	creepLine.actionPerformed(e); //instead of making two methods, just call actionPerformed
 	  	
 	  	String str = waveField.getText();
 	  	wave = Integer.parseInt(str);
 	  	wave++;
 	  	waveField.setText("" + wave); 
 	  }   	  
  	}
  }
  
  public void mouseEntered(MouseEvent e) 
  {
  	JButton src = (JButton)e.getSource();
  	
  	if (src == redTower && !gameIsOver)
  	{
  	  display.setText("Red Tower\n\nCost: $12\nPower: 20\nRange: 2\nFiring Rate: SLOW");
  	  repaint();
  	}
  	else if (src == greenTower && !gameIsOver)
  	{
  	  display.setText("Green Tower\n\nCost: $8\nPower: 8\nRange: 3\nFiring Rate: FAST");
  	  repaint(); 	
  	}
  	else if (src == blueTower && !gameIsOver)
  	{
  	  display.setText("Blue Tower\n\nCost: $10\nPower: 12\nRange: 3\nFiring Rate: NORMAL");
  	  repaint();  	
  	}
  }
  
  public void mouseExited(MouseEvent e) 
  {
	JButton src = (JButton)e.getSource();
	
	if (src == redTower && !gameIsOver)
  	{
  	  display.setText(" ");
  	}
  	else if (src == greenTower && !gameIsOver)
  	{
  	  display.setText(" ");
  	}
  	else if (src == blueTower && !gameIsOver)
  	{
  	  display.setText(" ");
  	}
  }
  
  // Methods not used
  public void mousePressed(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  
 }