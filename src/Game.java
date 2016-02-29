import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener{

	public static void main(String... args){
		JFrame frame = new JFrame("Tic-Tac-Toe AI Demo - Steven Landow");
		frame.setSize(WIDTH + 200,HEIGHT + 30);
		Game g  = new Game();
		frame.setContentPane(g);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(g);
		frame.addMouseListener(g);
	}
	
	public static final int WIDTH = 480, HEIGHT = 480;
	TicTacToe game;
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_R)startgame();
		else
		game.go();
	}
	
	
	void startgame(){
		String[] options = {"Table","Heuristic","Minimax","Human"};
		Player a,b;
		int va = JOptionPane.showOptionDialog(null, "Player A:", "Player Selection", JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]);
		int vb =  JOptionPane.showOptionDialog(null, "Player B:", "Player Selection", JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]);
		a= getPlayer(va);
		b= getPlayer(vb);
		System.out.println(a.getClass());
		System.out.println(b.getClass());
		game = new TicTacToe(a,b);
	}
	public Game(){
		startgame();
		new Thread(this).start();
	}
	
	
	public static Player getPlayer(int k){
		switch(k){
		case 0: return new TablePlayer(0);
		case 1: return new HeuristicPlayer(0);
		case 2: return new MinmaxPlayer(0);
		case 3: return new HumanPlayer(0);
		default: throw new IndexOutOfBoundsException("No player at index " + k);
		}
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH * 2, HEIGHT);
		for(int i = 0; i < TicTacToe.spots.length; i++){
			int x = TicTacToe.spots[i][0];
			int y = TicTacToe.spots[i][1];
			if(game.board[x][y] == 1){
				g.setColor(Color.red);
			}else if(game.board[x][y] == 2){
				g.setColor(Color.blue);
			}else{
				continue;
			}
			g.fillRect((WIDTH / 3) * x + 1, (HEIGHT / 3) * y + 1, WIDTH / 3 - 2, HEIGHT / 3 - 2);
			g.setColor(Color.white);
			
		}
		int n = 0;
		
		try{game.log = game.log.replace("null", "");for(String l : game.log.split("\n")){
			g.drawString(l, WIDTH, 20 * n + 20);
			n++;
		}}catch(Exception e){}
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		game.mousePressed(e);
		System.out.println(e.getX() + ", " + e.getY());
	}
	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
