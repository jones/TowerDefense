import java.awt.*;
import javax.swing.*;

public class TowerDefense extends JFrame
{
	private ChompGame game;
	private ControlPanel controlPanel;
	private BoardPanel board;

	public TowerDefense()
	{
	  super("Tower Defense");
	  Container c = getContentPane();

   // Initializes ControlPanel
	  controlPanel = new ControlPanel();
	  c.add(controlPanel, BorderLayout.EAST);
	  
	// Initializes Layout
      board = new BoardPanel(controlPanel);
      c.add(board, BorderLayout.CENTER);

      game = new ChompGame(board);
	}
	
	public void newGame()
	{
	  controlPanel.startNewGame();
	  CreepLine.destroyWave();
	  BoardPanel.destroyTowers();
	  game = new ChompGame(board);
	}
	
	public static void main(String[] args)
    {
      TowerDefense window = new TowerDefense();
      JMenuBar menuBar = new TDMenu(window);
	  window.setJMenuBar(menuBar);
      window.setBounds(450, 5, 825, 600); // Default is 100,100,825,600
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);
      window.setVisible(true);
    }
}