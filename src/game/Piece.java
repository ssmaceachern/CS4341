package game;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Piece {
	private boolean isMine;

	public Piece(boolean mine) {
		setMine(mine);
	}

	/**
	 * @return the isMine
	 */
	public boolean isMine() {
		return isMine;
	}

	/**
	 * @param isMine
	 *            the isMine to set
	 */
	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
}
