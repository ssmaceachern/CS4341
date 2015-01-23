package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.Timer;

import player.Player;

public class Game {
	/*
	 * Class declaration variables
	 */
	public Timer timer;
	public int numToWin,playerNum,TimeLimit;
	
	public boolean isMyTurn, isDropUsed;
	public Board board;
	public Player player;
	  
	/**
	 * 
	 * @param height - number of rows
	 * @param width - number of columns
	 * @param numberToWin - connectN needed to win
	 * @param playerNumber - whether or not I'm first
	 * @param timeLimit - time limit for each turn
	 */
	public Game(int height, int width, int numberToWin, int playerNumber, int timeLimit) {
		numToWin = numberToWin;
		playerNum = playerNumber;
		TimeLimit = timeLimit;
		
		isMyTurn = playerNum == 0;
		board = new Board(height, width, numberToWin, playerNumber, timeLimit);
		player = new Player();
	}
	
	public void Play() throws Exception {
		int move;
		
		while (true) {
			if (isMyTurn) {
				move = NextMove();
				System.out.println(String.valueOf(move));
				System.out.flush();
			} else {
				BufferedReader streamReader = new BufferedReader(new InputStreamReader(System.in));
				String[] data = streamReader.readLine().split(" ");
				move = Integer.parseInt(data[0]);
			}
			
			switch (move) {
			case -1:
			case -2:
			case -3:
				return;
			}
			
			board.HandleMove(isMyTurn, move);
			isMyTurn = !isMyTurn;
		}
	}
	
	private int NextMove() {
		int move = 0;
		
		StartClock();
		move = player.Decide(this);
		StopClock();
		
		return move;
	}
	
	private void StartClock() {
		int delay = (TimeLimit / 2) * 1000;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				player.Stop();
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();
	}
	
	private void StopClock() {
		timer.stop();
	}
}