/**
 * 
 */
package game;

/**
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Move {
	
	private int column,popOut;
	
	public Move(int column, int popOut){
		this.column = column;
		this.popOut = popOut;
	}

	/**
	 * 
	 * @return the column for the piece to be placed
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * @param column - where the piece should go
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * 
	 * @return - 1 if the piece is getting dropped in the column, 0 if the column is getting popped out
	 */
	public int getPopOut() {
		return popOut;
	}

	/**
	 * 
	 * @param popOut - 1 if the piece is getting dropped in the column, 0 if the column is getting popped out
	 */
	public void setPopOut(int popOut) {
		this.popOut = popOut;
	}
	
	

}
