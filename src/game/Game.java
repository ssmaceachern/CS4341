package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import core.Input;
import core.Output;
import player.Player;

public class Game {
	public Timer Timer;
	public int NumberToWin;
	public int PlayerNumber;
	public int TimeLimit;
	
	public boolean MyTurn;
	public Board Board;
	public Player Player;
	  
	public Game(int height, int width, int numberToWin, int playerNumber, int timeLimit) {
		NumberToWin = numberToWin;
		PlayerNumber = playerNumber;
		TimeLimit = timeLimit;
		
		MyTurn = PlayerNumber == 0;
		Board = new Board(height, width, numberToWin, playerNumber, timeLimit);
		Player = new Player();
	}
	
	public void Play() throws Exception {
		int move;
		
		for (;;) {
			if (MyTurn) {
				move = NextMove();
				Output.Write(String.valueOf(move));
			} else {
				String[] data = Input.Read().split(" ");
				move = Integer.parseInt(data[0]);
			}
			
			switch (move) {
			case -1:
			case -2:
			case -3:
				return;
			}
			
			Board.HandleMove(MyTurn, move);
			MyTurn = !MyTurn;
		}
	}
	
	private int NextMove() {
		int move = 0;
		
		StartClock();
		move = Player.Decide(this);
		StopClock();
		
		return move;
	}
	
	private void StartClock() {
		int delay = (TimeLimit / 2) * 1000;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Player.Stop();
			}
		};
		Timer = new Timer(delay, taskPerformer);
		Timer.start();
	}
	
	private void StopClock() {
		Timer.stop();
	}
}