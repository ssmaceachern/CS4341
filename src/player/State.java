package player;

import game.Board;
import game.Move;

public class State {
	public Move Move;
	public int MaxDepth;
	public int Score;
	public Board Board;
	public Player Player;
	public State Moves[];
	private boolean Timeout;

	private static int alpha;
	private static int beta;
	private static boolean max;

	//TODO I think this is where we handle the pop-out 
	public State(int column, int maxDepth, Board board, Player player) {
		Move.setColumn(column);
		MaxDepth = maxDepth;
		Score = player.Heuristic.Evaluate(board);
		Board = board;
		Player = player;
	}

	public void GenerateMoves(int level) {
		if (level == 0) {
			alpha = Integer.MIN_VALUE; // best move yet
			beta = Integer.MAX_VALUE; // worst move yet
			max = true; // maximize first
		} else {
			max = !max;
		}

		int[] openColumns = Board.GetOpenColumns();
		Moves = new State[openColumns.length];

		for (int i = 0; i < openColumns.length; i++) {
			Board board = new Board(Board);
			Move.setColumn(openColumns[i]);
			board.HandleMove(Board.isMyTurn(), Move);
			Moves[i] = new State(Move.getColumn(), MaxDepth, board, Player);
			if (max) {
				if (Moves[i].Score > alpha)
					alpha = Moves[i].Score;
			} else {
				if (Moves[i].Score < beta)
					beta = Moves[i].Score;
			}
			if (level <= MaxDepth && !Timeout) {
				// do not level++ here
				if (IsBetterAlphaBeta(Moves[i].Score, alpha, beta, max))
					Moves[i].GenerateMoves(level + 1);
			}
			Score += Moves[i].Score;
		}
	}

	public State Minimax(boolean maximize) {
		int score = maximize ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		State maximized = null;

		if (Moves == null || Moves.length == 0) {
			return null;
		}

		for (int i = 0; i < Moves.length; i++) {
			State state = Moves[i];
			if (IsBetter(state.Score, score, maximize)) {
				score = state.Score;
				maximized = state;
			}
		}

		return maximized;
	}

	public void Stop() {
		Timeout = true;
	}

	private boolean IsBetter(int score, int best, boolean maximize) {
		return maximize ? score > best : score < best;
	}

	private boolean IsBetterAlphaBeta(int score, int alpha, int beta,
			boolean maximize) {
		if (maximize)
			return score > alpha;
		else
			return score < beta;
	}
}
