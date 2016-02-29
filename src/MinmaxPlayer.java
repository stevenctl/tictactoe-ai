import java.util.ArrayList;
import java.util.List;

public class MinmaxPlayer extends Player {


	int[][] cells;
	public MinmaxPlayer(int t) {
		super(t);
		
		
	}

	@Override
	public void play(int[][] board) {
		this.cells = board;
		int[] mmax = minimax(2, type);
		System.out.println(mmax);
		attempt(mmax[1],mmax[2],board);
	}

	
    
 

private int[] minimax(int depth, int player) {
     // Generate possible next moves in a List of int[2] of {row, col}.
     List<int[]> nextMoves = generateMoves();

     // type is maximizing; while type == 1 ? 2 : 1 is minimizing
     int bestScore = (player == type) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
     int currentScore;
     int bestRow = -1;
     int bestCol = -1;

     if (nextMoves.isEmpty() || depth == 0) {
        // Gameover or depth reached, evaluate score
        bestScore = eval(cells);
     } else {
        for (int[] move : nextMoves) {
           // Try this move for the current "player"
           cells[move[0]][move[1]] = player;
           if (player == type) {  // type (computer) is maximizing player
              currentScore = minimax(depth - 1, type == 1 ? 2 : 1)[0];
              if (currentScore > bestScore) {
                 bestScore = currentScore;
                 bestRow = move[0];
                 bestCol = move[1];
              }
           } else {  // type == 1 ? 2 : 1 is minimizing player
              currentScore = minimax(depth - 1, type)[0];
              if (currentScore < bestScore) {
                 bestScore = currentScore;
                 bestRow = move[0];
                 bestCol = move[1];
              }
           }
           // Undo move
           cells[move[0]][move[1]] = 0;
        }
     }
     return new int[] {bestScore, bestRow, bestCol};
  }
	
	
	
	



	int[][] lines = {{0,1,2},{3,4,5},{6,7,8},
					 {0,3,6},{1,4,7},{2,5,8},
					  {0,4,8},{6,4,2}};
	
	
	private List<int[]> generateMoves() {
	      List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List
	 
	      // If gameover, i.e., no next move
	      if (hasWon(type) || hasWon(type == 1 ? 2 : 1)) {
	         return nextMoves;   // return empty list
	      }
	 
	      // Search for empty cells and add to the List
	      for (int row = 0; row < 3; ++row) {
	         for (int col = 0; col < 3; ++col) {
	            if (cells[row][col] == 0) {
	               nextMoves.add(new int[] {row, col});
	            }
	         }
	      }
	      return nextMoves;
	   }
	
	

	private boolean hasWon(int type) {
		for(int[] line : lines){
			int c = 0;
			for(int spot : line){
				int x= TicTacToe.spots[spot][0];
				int y= TicTacToe.spots[spot][1];
				if(cells[x][y] == type)c++;
			}
			
			if(c == 3)return true;
		}
		return false;
	}

	private int eval(int[][] b){
		int sum = 0;
		for(int i = 1; i <= 2; i++){ //both players
			int mod = i == type ? 1 : -1; //neg for opponent
			for(int[] line : lines){
				int c = 0;
				for(int spot : line){
					int x= TicTacToe.spots[spot][0];
					int y= TicTacToe.spots[spot][1];
					if(b[x][y] == i)c++;
				}
				
				sum += mod * ((int) Math.round(Math.pow(10, c - 1)));
			}
		}
		return sum;
	}
	
	
	
	
	
	int[][] clone(int[][] n){
		int[][] b = new int[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				b[i][j] = n[i][j];
		return b;
	}
	
}
