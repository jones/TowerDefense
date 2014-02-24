// INCOMPLETE (The "Help" and "About" windows show up blank)
// A menu bar for the Tower Defense game

import javax.swing.*;
import java.awt.event.*;

public class TDMenu extends JMenuBar
  implements ActionListener

{
  private TowerDefense game;
  private JMenu file, help, settings, level;
  private JMenuItem about, exit, newGame, howTo;
  private JRadioButtonMenuItem easy, normal, intense;
  
  public TDMenu(TowerDefense gameTD)
  {
  	game = gameTD;
  	
  	file = new JMenu("File");
  	newGame = new JMenuItem("Start New Game");
  	newGame.addActionListener(this);
  	exit = new JMenuItem("Exit");
  	exit.addActionListener(this);
  	help = new JMenu("Help");
  	howTo = new JMenuItem("How to Play");
  	howTo.addActionListener(this);
  	about = new JMenuItem("About");
  	about.addActionListener(this);
  	settings = new JMenu("Settings");
  	level = new JMenu("Select Level");
  	easy = new JRadioButtonMenuItem("Easy");
  	easy.addActionListener(this);
  	normal = new JRadioButtonMenuItem("Normal", true);
  	normal.addActionListener(this);
  	intense = new JRadioButtonMenuItem("Intense");
  	intense.addActionListener(this);
  	ButtonGroup levelGR = new ButtonGroup();
  	levelGR.add(easy);
  	levelGR.add(normal);
  	levelGR.add(intense);
  	
  	file.add(newGame);
  	file.add(exit);
  	help.add(howTo);
  	help.add(about);
  	settings.add(level);
  	level.add(easy);
  	level.add(normal);
  	level.add(intense);
  	this.add(file);
  	this.add(help);
  	this.add(settings);
  	
  	repaint();
  }
  
  // INCOMPLETE
  private class MenuHelp
  {
    public void showHow()
    {
      JOptionPane.showMessageDialog(null,
        "The object of the game is to prevent the waves of creeps from getting to\n" +
        "the castle by constructing elemental towers along the path. As you defeat each\n" +
        "line of enemies, it is replaced with a new wave spawning more money and health.\n" +
        "Use the money you earn to build more towers or upgrade existing ones.\n" +
        "\n\n" +
        "Each repitition of the game differs from the previous one; there are numerous\n" +
        "combinations of position, elements, and upgrades possible to experiment with.\n",
        "How to Play",       // Dialog title
        JOptionPane.PLAIN_MESSAGE);
    }

    public void showAbout()
    {
      JOptionPane.showMessageDialog(null,
        "This variation of Tower Defense was created by Anthony Cao and Tristan Jones.\n" +
        "It is their final project in Homestead's \"Intro to Java\" class.\n",
        "About",       // Dialog title
        JOptionPane.PLAIN_MESSAGE);
    }
  }
  
  public void actionPerformed(ActionEvent e)
  {
  	MenuHelp helpObj = new MenuHelp();
  	JMenuItem src = (JMenuItem)e.getSource();
  	if (src == newGame)
  	{
  	  game.newGame();
  	}
  	else if (src == exit)
  	{
  	  System.exit(1);
  	}
  	else if (src == howTo)
  	{
  	  helpObj.showHow();
  	}
  	else if (src == about)
  	{
  	  helpObj.showAbout();
  	}
  	else if (src == easy)
  	{
  	  // INCOMPLETE
  	}
  	else if (src == normal)
  	{
  	  // INCOMPLETE
  	}
  	else if (src == intense)
  	{
  	  // INCOMPLETE
  	}
  }
  
  public static void main(String[] args)
  {
  	TowerDefense td = new TowerDefense();
  	JFrame window = new JFrame();
  	JMenuBar menu = new TDMenu(td);
  	window.setJMenuBar(menu);
  	window.setBounds(100, 100, 500, 500);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setVisible(true);
  }   
}