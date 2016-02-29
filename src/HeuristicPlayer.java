public class HeuristicPlayer extends Player {

	
	
	public HeuristicPlayer(int t) {
		super(t);
		
	}

	@Override
	public void play(int[][] board) {
		int best = -1;
		int bestsum = -Integer.MIN_VALUE;
		for(int i = 0; i < TicTacToe.spots.length; i++){
			int[] s =  TicTacToe.spots[i];
			if(board[s[0]][s[1]] == 0){
				int v = eval(s,board);
				if(v > bestsum){
					bestsum = v;
					best = i;
				}
			}
		}
		System.out.println(best);
		attempt(best, board);
	}

	int[][] lines = {{0,1,2},{3,4,5},{6,7,8},
					 {0,3,6},{1,4,7},{2,5,8},
					  {0,4,8},{6,4,2}};
	private int eval(int[] s, int[][] board) {
		int[][] b = clone(board);
		b[s[0]][s[1]] = type;
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

	private boolean attempt(int n, int[][] board){
		if(board[TicTacToe.spots[n][0]]
		        [TicTacToe.spots[n][1]] == 0){
			board[TicTacToe.spots[n][0]]
			     [TicTacToe.spots[n][1]] = type;
			return true;
		}
		return false;
	}
	
	int[][] clone(int[][] n){
		int[][] b = new int[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				b[i][j] = n[i][j];
		return b;
	}
	
}
