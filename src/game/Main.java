package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Main {
	public static final String teamName = "Nighthawks";

	/**
	 * This is the main program which will play connect-N artificially
	 * intelligently
	 * 
	 * @param args
	 *            - (to the program (from the ref.))
	 * @throws Exception
	 *             - in case something breaks
	 */
	public static void main(String[] args) throws Exception {

		sendNameToReferee();

		BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				System.in));

		String s = streamReader.readLine();
		List<String> gameConfig = Arrays.asList(s.split(" "));

		while (true) {
			// erroneous lines
			if (gameConfig.size() != 5) {
				s = streamReader.readLine();
				gameConfig = Arrays.asList(s.split(" "));
			} else {
				// ls contains game info
				int height = Integer.parseInt(gameConfig.get(0));
				int width = Integer.parseInt(gameConfig.get(1));
				int numberToWin = Integer.parseInt(gameConfig.get(2));
				int playerNumber = Integer.parseInt(gameConfig.get(3));
				int timeLimit = Integer.parseInt(gameConfig.get(4));

				Game game = new Game(height, width, numberToWin, playerNumber,
						timeLimit);
				game.Play();
			}
		}

	}

	/**
	 * sends the teamName variable to the console
	 */
	public static void sendNameToReferee() {
		System.out.println(teamName);
		System.out.flush();
	}
}
