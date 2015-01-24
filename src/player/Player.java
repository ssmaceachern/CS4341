package player;

import java.util.ArrayList;

import game.Game;

public class Player {
	public Heuristic Heuristic;
	private State state;
	private boolean Timeout;

	private int Depth = 2;

	public Player() {
		Heuristic = new Heuristic();
		Timeout = false;
	}

	public int Decide(Game game) {
		state = new State(0, Depth, game.board, this);
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
			stack.add(state.Move.getColumn());
		}

		int decision = 0;

		// try {
		decision = stack.get(0);
		// } catch (Exception e) {}

		return decision;
	}

	public void Stop() {
		Timeout = true;
		if (state != null) {
			state.Stop();
		}
	}
}
