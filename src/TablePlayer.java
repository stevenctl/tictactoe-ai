public class TablePlayer extends Player {

	
	static final int[] pref = {4,0,6,2,8,3,1,7,5};
	
	public TablePlayer(int t) {
		super(t);
		
	}

	@Override
	public void play(int[][] board) {
		
		for(int n : pref){
			
			if(attempt(n,board))break;
		}
	}

	private boolean attempt(int n, int[][] board){
		if(board[TicTacToe.spots[n][0]]
		        [TicTacToe.spots[n][1]] == 0){
			board[TicTacToe.spots[n][0]]
			     [TicTacToe.spots[n][1]] = type;
			return true;
		}
		return false;
	}
	
}
