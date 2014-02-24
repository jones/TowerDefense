// Implements a 2-D array of integers
// From the Chomp game

public class IntMatrix
{
  private final int creep = -2, path = -1, empty = 0, rTower = 1, gTower = 2, bTower = 3;
  public static int[][] grid;
  public static int selRow;
  public static int selCol;
  
  // Creates a grid of integers; the "guts" underneath the Layout
  public IntMatrix(int rows, int cols)
  {
    grid = new int[rows][cols];
    
    selRow = 0;
    selCol = 0;
    
    // Fills the array with empty
    for(int r = 0; r < rows; r++)
    {
      for(int c = 0; c < cols; c++)
      {
      	grid[r][c] = empty;
      }
    }
   
    // Sets the path
	for(int i = 1; i < 5; i++)
	  IntMatrix.grid[i - 1][3] = path; // Col 4
	for(int j = 4; j > 1; j--)
	  IntMatrix.grid[3][j - 1] = path; // Row 4
	for(int k = 4; k < 8; k++)
	  IntMatrix.grid[k - 1][1] = path; // Col 2
	for(int l = 2; l < 7; l++)
	  IntMatrix.grid[6][l - 1] = path; // Row 7
	for(int m = 7; m > 3; m--)
	  IntMatrix.grid[m - 1][6] = path; // Col 3
	for(int n = 7; n < 16; n++)
	  IntMatrix.grid[2][n - 1] = path; // Row 3
	for(int o = 3; o < 7; o++)
	  IntMatrix.grid[o - 1][15] = path; // Col 16
	for(int p = 10; p < 16; p++)
	  IntMatrix.grid[5][p - 1] = path; // Row 6
	for(int q = 6; q < 13; q++)
	  IntMatrix.grid[q - 1][9] = path; // Col 10
	for(int r = 9; r < 10; r++)
	  IntMatrix.grid[11][r - 1] = path; // Row 12
	for(int s = 12; s < 15; s++)
	  IntMatrix.grid[s - 1][8] = path; // Col 9
	for(int t = 2; t < 9; t++)
	  IntMatrix.grid[13][t - 1] = path; // Row 14
	for(int u = 14; u < 17; u++)
	  IntMatrix.grid[u - 1][1] = path; // Col 2
	for(int v = 2; v < 13; v++)
	  IntMatrix.grid[16][v - 1] = path; // Row 17
	for(int w = 14; w < 17; w++)
	  IntMatrix.grid[w - 1][11] = path; // Col 12
	for(int x = 12; x < 14; x++)
	  IntMatrix.grid[13][x - 1] = path; // Row 14
	for(int y = 14; y < 17; y++)
	  IntMatrix.grid[y - 1][13] = path; // Col 14
	for(int z = 14; z < 17; z++)
	  IntMatrix.grid[16][z - 1] = path; // Row 17
	for(int a = 9; a < 17; a++)
	  IntMatrix.grid[a - 1][15] = path; // Col 16
	for(int b = 16; b < 18; b++)
	  IntMatrix.grid[8][b - 1] = path; // Row 9
	for(int c = 17; c < 19; c++)
	  IntMatrix.grid[7][c - 1] = path; // Row 8
  }

  // Returns the number of rows in grid
  public int numRows()
  {
    return grid.length;
  }

  // Returns the number of columns in grid
  public int numCols()
  {
    return grid[0].length;  
  }

  // Returns the integer at row, col location
  public int intAt(int row, int col)
  {
    return grid[row][col];
  }
  
	// Determines whether the "panel" at row, col is empty 
  public boolean isEmpty(int row, int col)
  {
    return grid[row][col] == empty;
  }
  
  // Determines whether the "panel" at row, col contains a creep
  public boolean isCreep(int row, int col)
  {
  	return grid[row][col] == creep;
  }
	
  // Determines whether the "panel" at row, col is a Creep path
  public boolean isPath(int row, int col)
  {
  	return grid[row][col] == path;
  }
   
  public int getItemAt(int row, int col)
  {
  	return grid[row][col];
  }
  
  //Lets you set an item at a location
  //Returns TRUE if it happened, FALSE if it failed
  public boolean setItemAt(int row, int col, int object)
  {
  	int item = grid[row][col];
  	
  	if(object == creep)
  	{
  	  if(item == path)
  	  {
  	  	grid[row][col] = object;
  	  	return true;
  	  }
  	  else
  	  {
  	  	ControlPanel.display.setText("Cannot put creep off path");
  	  	return false;
  	  }
  	}
  	else if (object == path)
  	{
  	  if (item > -1)
  	  {
  	  	System.out.println("Removing item at " + row + " " + col);
  	  	return false;
  	  }
  	  else
  	  {
  	  	grid[row][col] = object;
  	  	return true;
  	  }
  	}
  	else if(object == empty)
  	{
  	  if(item != empty)
  	  	return false;
  	  else
  	  {
  	  	grid[row][col] = object;
  	  	return true;
  	  }
      
  	}
  	else // if(object > 0) // If it is a tower
  	{
  	  if(item == path || item == creep)
  	  {
  	  	ControlPanel.display.setText("Cannot build on path");
  	  	return false;
  	  }
  	  else
  	  {
  	  	grid[row][col] = object;
  	  	return true;
  	  }
   	}
  }

}
