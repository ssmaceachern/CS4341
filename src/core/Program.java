package core;

import game.Game;

public class Program {
	public static final String Name = "Nighthawks";
	
	public static void main(String[] args) throws Exception {
		OutputName();
		
		String[] gameConfig = Input.Read().split(" ");
		
        int height = Integer.parseInt(gameConfig[0]);
        int width = Integer.parseInt(gameConfig[1]);
        int numberToWin = Integer.parseInt(gameConfig[2]);
        int playerNumber = Integer.parseInt(gameConfig[3]);
        int timeLimit = Integer.parseInt(gameConfig[4]);
        
        Game game = new Game(height, width, numberToWin, playerNumber, timeLimit);
        game.Play();
	}
	
	public static void OutputName() {
		Output.Write(Name);
	}
}
