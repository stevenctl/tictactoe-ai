
public class HumanPlayer extends Player {

	public HumanPlayer(int t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	boolean think = false;
	int[][] board;
	@Override
	public void play(int[][] board) {
		if(this.board == null)this.board = board;
		
	}
	
	public boolean click(int x, int y){
		return attempt(x / (480 / 3),(y  )/ (480 / 3),board);
	}

}
