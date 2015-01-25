package player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import game.Game;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Main {
	public static final String teamName = "Nighthawks";

	/**
	 * This is the main program which will play connect-N artificially intelligently
	 * @param args - (to the program (from the ref.))
	 * @throws Exception - in case something breaks
	 */
	public static void main(String[] args) throws Exception {

		sendNameToReferee();

		BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				System.in));
		String[] gameConfig = streamReader.readLine().split(" ");

		int height = Integer.parseInt(gameConfig[0]);
		int width = Integer.parseInt(gameConfig[1]);
		int numberToWin = Integer.parseInt(gameConfig[2]);
		int playerNumber = Integer.parseInt(gameConfig[3]);
		int timeLimit = Integer.parseInt(gameConfig[4]);

		Game game = new Game(height, width, numberToWin, playerNumber,
				timeLimit);
		game.Play();
	}

	/**
	 * sends the teamName variable to the console
	 */
	public static void sendNameToReferee() {
		System.out.println(teamName);
		System.out.flush();
	}
}
