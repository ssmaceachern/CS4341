package game;

public class Board {
	public int NumberToWin;
	public int PlayerNumber;
	public int TimeLimit;
	public int Height;
	public int Width;
	public boolean MyTurn;
	public Piece Pieces[][];
	
	public Board(int height, int width, int numberToWin, int playerNumber, int timeLimit) {
		NumberToWin = numberToWin;
		PlayerNumber = playerNumber;
		TimeLimit = timeLimit;
		MyTurn = PlayerNumber == 0;
		Height = height;
		Width = width;
		Pieces = new Piece[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Pieces[i][j] = null;
			}
		}
	}
	
	public Board(Board board) {
		NumberToWin = board.NumberToWin;
		PlayerNumber = board.PlayerNumber;
		TimeLimit = board.TimeLimit;
		MyTurn = !board.MyTurn;
		Height = board.Height;
		Width = board.Width;
		Pieces = new Piece[Width][Height];
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				Pieces[i][j] = board.Pieces[i][j];
			}
		}
	}
	
	public int NumberOfOpenColumns() {
		int count = 0;
		
		for (int i = 0; i < Width; i++) {
			if (Pieces[i][Height - 1] == null) {
				count++;
			}
		}
		
		return count;
	}
	
	public int[] GetOpenColumns() {
		int openColumns[] = new int[NumberOfOpenColumns()];
		
		for (int i = 0, j = 0; i < Width; i++) {
			if (Pieces[i][Height - 1] == null) {
				openColumns[j] = i;
				j++;
			}
		}
		
		return openColumns;
	}
	
	public void HandleMove(boolean mine, int move) {
		for (int i = 0; i < Height; i++) {
			if (Pieces[move][i] == null) {
				Pieces[move][i] = new Piece(mine);
				break;
			}
		}
	}
}
