
public abstract class Player {

	int type;
	
	public abstract void play(int[][] board);
	
	public Player(int t){
		this.type = t;
	}
	
	public Player setType(int t){
		type = t;
		return this;
	}
	
	boolean attempt(int i, int j, int[][] board) {
		  
		  if(board[i][j] == 0){
				board[i]
				     [j] = type;
				return true;
			}
			return false;
		}
	
	public boolean click(int x, int y){
		return false;
	}
}
