package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.Timer;

import player.Player;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Game {
	/*
	 * Class declaration variables
	 */
	private Timer timer;
	private int numToWin, playerNum, TimeLimit;

	private boolean isMyTurn, isDropUsed;
	private Board board;
	private Player player;

	/**
	 * 
	 * @param height
	 *            - number of rows
	 * @param width
	 *            - number of columns
	 * @param numberToWin
	 *            - connectN needed to win
	 * @param playerNumber
	 *            - whether or not I'm first
	 * @param timeLimit
	 *            - time limit for each turn
	 */
	public Game(int height, int width, int numberToWin, int playerNumber,
			int timeLimit) {
		numToWin = numberToWin;
		playerNum = playerNumber;
		TimeLimit = timeLimit;

		isMyTurn = playerNum == 0;
		isDropUsed = false;
		setBoard(new Board(height, width, numberToWin, playerNumber, timeLimit));
		player = new Player();
	}

	public void Play() throws Exception {
		Move move = null;

		while (true) {
			if (isMyTurn) {
				move = nextMove();
				System.out.println(String.valueOf(move.getColumn()) + " "
						+ move.getPopOut());
				System.out.flush();
			} else {
				BufferedReader streamReader = new BufferedReader(
						new InputStreamReader(System.in));
				String[] data = streamReader.readLine().split(" ");
				move.setColumn(Integer.parseInt(data[0]));
				move.setPopOut(Integer.parseInt(data[1]));
			}

			switch (move.getColumn()) {
			case -1:
			case -2:
			case -3:
				return;
			}

			getBoard().HandleMove(isMyTurn, move);
			isMyTurn = !isMyTurn;
		}
	}

	/**
	 * next move used the Heuristic functions from the player class to make a
	 * best guess for the next move
	 * 
	 * @return the Move to be made
	 */
	private Move nextMove() {
		Move move = new Move(0, 0);

		startClock();
		move.setColumn(player.Decide(this));
		// TODO Handle popout here somehow
		stopClock();

		return move;
	}

	/**
	 * starts the clock and sets a task to stop the player before time expires
	 * with the referee
	 */
	private void startClock() {
		int delay = (TimeLimit / 2) * 1000;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				player.Stop();
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();
	}

	/**
	 * simply stops the timer
	 */
	private void stopClock() {
		timer.stop();
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
}