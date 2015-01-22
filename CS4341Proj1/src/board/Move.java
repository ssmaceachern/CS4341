/**
 * 
 */
package board;

/**
 * @author zparnold
 *
 */
public class Move {
	private int move;
	private boolean popOut;
	//who made this move
	private int whichPlayer;
	
	public Move(int move, int popOut, int whichPlayer) {
		this.move = move;
		this.popOut = (popOut == 0)?true:false;
		this.whichPlayer = whichPlayer;
	}
	
	public int getMove() {
		return move;
	}

	public boolean isPopOut() {
		return popOut;
	}
	
	public int getWhichPlayer(){
		return whichPlayer;
	}

}
