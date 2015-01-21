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
	
	public Move(int move, int popOut) {
		this.move = move;
		this.popOut = (popOut == 0)?true:false;
	}
	
	public int getMove() {
		return move;
	}

	public boolean isPopOut() {
		return popOut;
	}

}
