package game;

public class Board {
	private int numToWin, playerNum, TimeLimit, height, width;
	private boolean isMyTurn;
	private Piece pieces[][];

	public Board(int height, int width, int numberToWin, int playerNumber,
			int timeLimit) {
		setNumToWin(numberToWin);
		setPlayerNum(playerNumber);
		TimeLimit = timeLimit;
		setMyTurn(getPlayerNum() == 0);
		height = height;
		width = width;
		setPieces(new Piece[width][height]);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				getPieces()[i][j] = null;
			}
		}
	}

	public Board(Board board) {
		setNumToWin(board.getNumToWin());
		setPlayerNum(board.getPlayerNum());
		TimeLimit = board.TimeLimit;
		setMyTurn(!board.isMyTurn());
		setHeight(board.getHeight());
		setWidth(board.getWidth());
		setPieces(new Piece[getWidth()][getHeight()]);
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				getPieces()[i][j] = board.getPieces()[i][j];
			}
		}
	}

	public int NumberOfOpenColumns() {
		int count = 0;

		for (int i = 0; i < getWidth(); i++) {
			if (getPieces()[i][getHeight() - 1] == null) {
				count++;
			}
		}

		return count;
	}

	public int[] GetOpenColumns() {
		int openColumns[] = new int[NumberOfOpenColumns()];

		for (int i = 0, j = 0; i < getWidth(); i++) {
			if (getPieces()[i][getHeight() - 1] == null) {
				openColumns[j] = i;
				j++;
			}
		}

		return openColumns;
	}

	public void HandleMove(boolean mine, int move) {
		for (int i = 0; i < getHeight(); i++) {
			if (getPieces()[move][i] == null) {
				getPieces()[move][i] = new Piece(mine);
				break;
			}
		}
	}

	/**
	 * @return the isMyTurn
	 */
	public boolean isMyTurn() {
		return isMyTurn;
	}

	/**
	 * @param isMyTurn
	 *            the isMyTurn to set
	 */
	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}

	/**
	 * @return the pieces
	 */
	public Piece[][] getPieces() {
		return pieces;
	}

	/**
	 * @param pieces
	 *            the pieces to set
	 */
	public void setPieces(Piece pieces[][]) {
		this.pieces = pieces;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the numToWin
	 */
	public int getNumToWin() {
		return numToWin;
	}

	/**
	 * @param numToWin
	 *            the numToWin to set
	 */
	public void setNumToWin(int numToWin) {
		this.numToWin = numToWin;
	}

	/**
	 * @return the playerNum
	 */
	public int getPlayerNum() {
		return playerNum;
	}

	/**
	 * @param playerNum
	 *            the playerNum to set
	 */
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
}
