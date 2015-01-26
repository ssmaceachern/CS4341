package player;

import java.util.ArrayList;

import game.Game;
import game.Move;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Player {
	private Heuristic Heuristic;
	private State state;
	private boolean Timeout;

	private int Depth = 2;

	public Player() {
		setHeuristic(new Heuristic());
		Timeout = false;
	}

	/**
	 * 
	 * @param game
	 * @return
	 */
	public Move Decide(Game game) {
		Move nextMove = new Move(0,1);
		state = new State(0, Depth, game.getBoard(), this);
		state.GenerateMoves(0);
		boolean myTurn = true;

		ArrayList<Integer> stack = new ArrayList<Integer>();
		while (true) {
			State next = state.Minimax(myTurn);
			if (Timeout || next == null) {
				break;
			}
			state = next;
			myTurn = !myTurn;
			// unfortunately, this will auto-box our integer
			stack.add(state.getMove().getColumn());
		}

		int decision = 0;

		decision = stack.get(0);

		nextMove.setColumn(decision);
		
		return nextMove;
	}

	/**
	 * 
	 */
	public void Stop() {
		Timeout = true;
		if (state != null) {
			state.Stop();
		}
	}
	

	/**
	 * @return the heuristic
	 */
	public Heuristic getHeuristic() {
		return Heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(Heuristic heuristic) {
		Heuristic = heuristic;
	}

	
}
