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

	//Search to depth five ideally
	private int Depth = 5;

	public Player() {
		setHeuristic(new Heuristic());
		Timeout = false;
	}

	/**
	 * Decide makes a choice on what column to place a disk in
	 * @param game
	 * @return a Move (both column and pop-out) for the next best step
	 */
	public Move Decide(Game game) {
		Move nextMove = new Move(0,1);
		state = new State(0, Depth, game.getBoard(), this);
		state.GenerateMoves(0);
		boolean myTurn = true;

		//Create a stack of feasible moves to use
		ArrayList<Move> stack = new ArrayList<Move>();
		while (true) {
			//the next board state given by minimax on my turn
			State next = state.Minimax(myTurn);
			if (Timeout || next == null) {
				break;
			}
			state = next;
			myTurn = !myTurn;
			
			stack.add(state.getMove());
		}


		nextMove = stack.get(0);
		
		
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
