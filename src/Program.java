

import java.io.BufferedReader;
import java.io.InputStreamReader;

import game.Game;

public class Program {
	public static final String teamName = "Nighthawks";
	
	public static void main(String[] args) throws Exception {
		
		sendNameToReferee();
		
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(System.in));
		String[] gameConfig = streamReader.readLine().split(" ");
		
        int height = Integer.parseInt(gameConfig[0]);
        int width = Integer.parseInt(gameConfig[1]);
        int numberToWin = Integer.parseInt(gameConfig[2]);
        int playerNumber = Integer.parseInt(gameConfig[3]);
        int timeLimit = Integer.parseInt(gameConfig[4]);
        
        Game game = new Game(height, width, numberToWin, playerNumber, timeLimit);
        game.Play();
	}
	
	public static void sendNameToReferee() {
		System.out.println(teamName);
		System.out.flush();
	}
}
