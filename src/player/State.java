package player;

import game.Board;
import game.Move;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class State {
	private Move Move;
	private int MaxDepth, Score;
	private Board Board;
	private Player Player;
	private State Moves[];
	private boolean Timeout, popOutAvailable;

	private static int alpha;
	private static int beta;
	private static boolean max;

	/**
	 *  Constructor for the State class
	 * @param column
	 * @param maxDepth
	 * @param board
	 * @param player
	 */
	public State(int column, int maxDepth, Board board, Player player) {

		Move = new Move(column,1);
		MaxDepth = maxDepth;
		Score = player.getHeuristic().evaluate(board);
		Move.setPopOut(player.getHeuristic().isPopOutAvailable()==true?0:1);
		Board = board;
		Player = player;
	}

	/**
	 * Generates moves using alpha-beta pruning to the depth of parameter (level)
	 * @param level
	 */
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
			getMove().setColumn(openColumns[i]);
			board.HandleMove(Board.isMyTurn(), getMove());
			Moves[i] = new State(getMove().getColumn(), MaxDepth, board, Player);
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

	/**
	 * Rudimentary version of minimax implemented for use in this search
	 * @param maximize
	 * @return
	 */
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

	/**
	 * 
	 */
	public void Stop() {
		Timeout = true;
	}

	/**
	 * 
	 * @param score
	 * @param best
	 * @param maximize
	 * @return
	 */
	private boolean IsBetter(int score, int best, boolean maximize) {
		return maximize ? score > best : score < best;
	}

	/**
	 * 
	 * @param score
	 * @param alpha
	 * @param beta
	 * @param maximize
	 * @return
	 */
	private boolean IsBetterAlphaBeta(int score, int alpha, int beta,
			boolean maximize) {
		if (maximize)
			return score > alpha;
		else
			return score < beta;
	}

	/**
	 * @return the move
	 */
	public Move getMove() {
		return Move;
	}

	/**
	 * @param move the move to set
	 */
	public void setMove(Move move) {
		Move = move;
	}



}
