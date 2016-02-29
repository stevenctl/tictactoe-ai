import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TicTacToe {

	int[][] board;
	String log;
	public TicTacToe(Player a, Player b){
		board = new int[3][3];
		o  = a.setType(O);
		x = b.setType(X);
		
		if(x instanceof HumanPlayer)((HumanPlayer)x).board = board;
		if(o instanceof HumanPlayer)((HumanPlayer)o).board = board;
	}
	
	static int[][] spots = {
			{0,0},{1,0},{2,0},
			{0,1},{1,1},{2,1},
			{0,2},{1,2},{2,2}
	};
	
	//0 = empty, 1 = X, 2 = O
	
	static final int X = 1, O = 2;
	
	int turn =X;
	Player x;
	Player o; 
	
	public double quadratic(float a, float b, float c){
		return (-1 * b + Math.sqrt(Math.pow(b, 2) - 4  * a * c)) / 2 * a;
	}
	boolean gg;
	public void go(){
		if(gg)return;
		int[][] ob = clone(board);
		
		if(turn == 1){
			if(x instanceof HumanPlayer)return;
			log += "A ("  + (x.getClass().toString().split(" ")[1]) + ") at ";
			x.play(board);
			turn = 2;
			
			
		}else{
			if(o instanceof HumanPlayer)return;
			log += "B ("  + (o.getClass().toString().split(" ")[1]) + ") at ";
			o.play(board);
			turn = 1;
			
			
		}
		log += Arrays.toString(diff(board, ob)) + "\n";
		if(hasWon(X)){
			gg = true;
			log += "A won!";
		}if(hasWon(O)){
			log += "B won!";
			gg = true;
		}
	}
	
	int[] diff(int[][] a, int[][] b){
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				if(a[i][j] != b[i][j])return new int[]{i,j};
		return new int[]{-1,-1};
	}
	
	int[][] clone(int[][] n){
		int[][] b = new int[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				b[i][j] = n[i][j];
		return b;
	}
	
	
	int[][] lines = {{0,1,2},{3,4,5},{6,7,8},
			 {0,3,6},{1,4,7},{2,5,8},
			  {0,4,8},{6,4,2}};
	private boolean hasWon(int type) {
		for(int[] line : lines){
			int c = 0;
			for(int spot : line){
				int x= TicTacToe.spots[spot][0];
				int y= TicTacToe.spots[spot][1];
				if(board[x][y] == type)c++;
			}
			
			if(c == 3)return true;
		}
		return false;
	}

	public void mousePressed(MouseEvent e) {
		if(gg)return;
		int[][] ob = clone(board);
		if(turn == X){
			if(x.click(e.getX(), e.getY()) && x instanceof HumanPlayer){
				log += "A ("  + (x.getClass().toString().split(" ")[1]) + ") at ";
				log += Arrays.toString(diff(board, ob)) + "\n";
				turn = 2;
				if(hasWon(X)){
					gg = true;
					log += "A won!";
				}
			}
			
		}else{
			if(o.click(e.getX(), e.getY()) && o instanceof HumanPlayer){
				log += "B ("  + (o.getClass().toString().split(" ")[1]) + ") at ";
				log += Arrays.toString(diff(board, ob)) + "\n";
				turn = 1;
				if(hasWon(O)){
					gg = true;
					log += "B won!";
				}
			}
		}
	}
}
