package player;

import java.util.ArrayList;
import java.util.Collections;

import game.Board;
import game.Piece;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Heuristic {
	
	private boolean popOutAvailable = false;

	@SuppressWarnings("unchecked")
	/**
	 * This method evaluates the state of the board using a Heuristic that 
	 * analyzes the opposing team's positions as threats and assigns values 
	 * based on their positions and potential series of moves
	 * @param board - The board to evaluate
	 * @return - An integer value for the usefulness of this board state
	 */
	public int evaluate(Board board) {
		/*
		 * FAIR WARNING: Each of these conditions we checked for contains
		 * a very similar format to all of the other previous, so there
		 * is a lot of copied code. To get a feel for our evaluation technique,
		 * I recommend picking one or two for-loops to evaluate.
		 * 
		 * YOU HAVE BEEN WARNED!!!
		 * 
		 */

		// return a low score if the board is non-existant
		if (board == null) {
			return 0;
		}

		// Evaluation function variable declarations
		ArrayList<Opponent> opposingThreats = new ArrayList<Opponent>();
		Piece[][] pieces = board.getPieces();
		boolean sign = true;
		int numOfConsecutivePieces = 0;
		int potentialConsecutivePieces = 0;
		boolean previousEmpty = false;

		// The final score (currently 0,) to return as the board state value
		int total = 0;

		// default values for empty slots on the board
		int emptyX = -1;
		int emptyY = -1;

		// Check horizontal threats
		boolean noPieceYet = true;
		for (int y = 0; y < board.getHeight(); y++) {
			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			for (int x = 0; x < board.getWidth(); x++) {
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { 
							
							//Store this threat and see whether or not I can benefit from a pop-out
							if (pieces [x][0].isMine()){
								popOutAvailable = true;
							}
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); 
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) 
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// Check diagonal threats on bottom half of board
		int y = 0;
		for (int i = 0; i < board.getWidth(); i++) {

			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			y = 0;

			for (int x = i; x < board.getWidth(); x++) {
				if (y >= board.getHeight()) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;

				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							//Store this threat and see whether or not I can benefit from a pop-out
							if (pieces [x][0].isMine()){
								popOutAvailable = true;
							}
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				y++; // move up too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// Check diagonal threats on top half of board
		int x = 0;
		for (int i = 0; i < board.getHeight(); i++) {

			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			x = 0;

			for (y = i; y < board.getHeight(); y++) {
				if (x >= board.getWidth()) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							//Store this threat and see whether or not I can benefit from a pop-out
							if (pieces [x][0].isMine()){
								popOutAvailable = true;
							}
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				x++; // move across, too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// downward diagonals
		// Check diagonal threats on the top half of board
		for (int i = board.getWidth() - 1; i >= 0; i--) {
			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			y = 0;

			for (x = i; x >= 0; x--) {
				if (y >= board.getHeight()) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							//Store this threat and see whether or not I can benefit from a pop-out
							if (pieces [x][0].isMine()){
								popOutAvailable = true;
							}
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				y++; // move up too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}
		// downward diagonals
		// Check diagonal threats on bottom half
		for (int i = 0; i < board.getHeight(); i++) {

			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			x = board.getWidth() - 1;

			for (y = i; y < board.getHeight(); y++) {
				if (x < 0) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							//Store this threat and see whether or not I can benefit from a pop-out
							if (pieces [x][0].isMine()){
								popOutAvailable = true;
							}
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				x--; // move across, too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// Check vertical threats (as they are the worst.)
		for (int x1 = 0; x1 < board.getWidth(); x1++) {

			// check to see if the column is empty...if it is, we can skip it
			if (pieces[x1][0] == null)
				continue;

			// Set up variables to loop over the spaces
			numOfConsecutivePieces = 1;
			potentialConsecutivePieces = 0;
			sign = pieces[x1][0].isMine();
			previousEmpty = false;

			for (int y1 = 1; y1 < board.getHeight(); y1++) {
				if (pieces[x1][y1] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x1;
						emptyY = y1;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (sign != pieces[x1][y1].isMine()) // we've broken the
															// streak
					{
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) {
							//Store this threat and see whether or not I can benefit from a pop-out
							if (pieces [x][0].isMine()){
								popOutAvailable = true;
							}
							// this is bad!! Store the threat in the
							// opposingThreats object
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x1][y1].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
				}
			}

			if (previousEmpty || numOfConsecutivePieces == 1) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) {
					// this is bad!! Store the threat in the opposingThreats
					// object
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign));
				}
			}
		}
		
		
				

		// finally, analyze the threat data from the heuristic evaluation
		Opponent opposition;
		ArrayList<Opponent> garbage = new ArrayList<Opponent>();
		boolean potentialWin = false;
		for (int i = 0; i < opposingThreats.size(); i++) {
			opposition = opposingThreats.get(i);
			if ((!opposition.isSign())
					&& (opposition.getStreak() == board.getNumToWin() - 1)) {
				// opponent has n-1 in a row!
				if (opposition.getY() > 0) { // can the opponent play there and
												// win?
					if (pieces[opposition.getX()][opposition.getY() - 1] != null)
						return Integer.MIN_VALUE; // lose condition!
				} else {
					return Integer.MIN_VALUE;// lose condition!
				}
			} else if ((opposition.isSign())
					&& (opposition.getStreak() == board.getNumToWin())) {
				if (opposition.getY() > 0) {
					if (pieces[opposition.getX()][opposition.getY() - 1] != null)
						potentialWin = true; // possible win, as long as we
												// don't notice a lose
				}
			}
		}
		if (potentialWin)
			return Integer.MAX_VALUE;

		// remove useless opposing team's movements from the arrayList
		Collections.sort(opposingThreats, Opponent.getComparator());
		ArrayList<Opponent> newThreats = opposingThreats;
		for (int i = (opposingThreats.size() - 1); i > 0; i--) { // if a threat
																	// is
			opposition = opposingThreats.get(i);
			Opponent t2 = opposingThreats.get(i - 1);
			if (opposition.getX() == t2.getX()) {
				if (opposition.getY() >= t2.getY()) {
					if (opposition.isSign() != t2.isSign()) {
						newThreats.remove(i);
						garbage.add(opposition);
					}
				}
			}
		}
		opposingThreats = newThreats;

		// whew! finally, calculate the total number of threats
		int val = 0;
		for (int i = 0; i < opposingThreats.size(); i++) {
			opposition = opposingThreats.get(i);

			//
			val = opposition.getStreak() * 100;
			val += opposition.getPotentialStreak() * 10;
			val += opposition.getY();

			val *= (opposition.getThreat() == board.getPlayerNum()) ? 1.5 : 1;
			// upweight this value is this is their turn

			if (!opposition.isSign()) { // opponent threat
				val *= -1;
				val--; // play defensively
			}

			total += val;
		}

		return total;
	}

	/**
	 * @return whether or not a piece can be popped out with this eval popOutAvailable
	 */
	public boolean isPopOutAvailable() {
		return popOutAvailable;
	}


}
