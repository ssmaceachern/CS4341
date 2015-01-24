package game;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Board {
	private int numToWin, playerNum, TimeLimit, height, width;
	private boolean isMyTurn;
	private Piece pieces[][];

	/**
	 *  Constructor for the Board class
	 * @param height of the board
	 * @param width of the board
	 * @param numberToWin number of consecutive pieces needed to win
	 * @param playerNumber first or second player to move
	 * @param timeLimit amount of time for each move
	 */
	public Board(int height, int width, int numberToWin, int playerNumber,
			int timeLimit) {
		setNumToWin(numberToWin);
		setPlayerNum(playerNumber);
		TimeLimit = timeLimit;
		setMyTurn(getPlayerNum() == 0);
		this.height = height;
		this.width = width;
		setPieces(new Piece[width][height]);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				getPieces()[i][j] = null;
			}
		}
	}

	/**
	 * Secondary constructor for the board class (this time when it's initialized with a board.) 
	 * Copy constructor basically
	 * @param board the game board to copy in
	 */
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

	/**
	 * 
	 * @return the number of open columns on the board
	 */
	public int NumberOfOpenColumns() {
		int count = 0;

		for (int i = 0; i < getWidth(); i++) {
			//if the pieces at row i isn't full...increase the count
			if (getPieces()[i][getHeight() - 1] == null) {
				count++;
			}
		}

		return count;
	}

	/**
	 * 
	 * @return an array of the open columns currently playable on the board
	 */
	public int[] GetOpenColumns() {
		int openColumns[] = new int[NumberOfOpenColumns()];
		
		//similarly to the number of open columns, this time just record the column number in the array
		for (int i = 0, j = 0; i < getWidth(); i++) {
			if (getPieces()[i][getHeight() - 1] == null) {
				openColumns[j] = i;
				j++;
			}
		}

		return openColumns;
	}

	/**
	 * 
	 * @param mine
	 * @param move
	 */
	public void HandleMove(boolean mine, Move move) {
		for (int i = 0; i < getHeight(); i++) {
			// TODO handle pop-out here
			if (getPieces()[move.getColumn()][i] == null) {
				getPieces()[move.getColumn()][i] = new Piece(mine);
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
