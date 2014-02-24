// Implements a Chomp game

public class ChompGame extends IntMatrix
{
  private BoardPanel board;

  // Constructor
  public ChompGame(BoardPanel board)
  {
    super(board.numRows(), board.numCols());
    this.board = board;
    board.update(this);  
  }
  

}
