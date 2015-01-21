import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import board.Move;

/**
 * 
 */

/**
 * @author zparnold
 *
 */
public class Main {
	public final static String teamName = "zparnold";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// setup class variables which contain the values of board and player
		// positions and such
		int width, height, connectN, timeLimit;
		boolean amIFirst;

		// Set up to Read the incoming arguments (game config)
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));

		// Tell the ref who I am
		returnNameToRef();

		// read game config
		String[] gameConfig = input.readLine().split(" ");
		height = Integer.parseInt(gameConfig[0]);
		width = Integer.parseInt(gameConfig[1]);
		connectN = Integer.parseInt(gameConfig[2]);

		// set boolean value for who is first
		amIFirst = (Integer.parseInt(gameConfig[3]) == 1) ? true : false;

		timeLimit = Integer.parseInt(gameConfig[4]);

		
		//If I'm first, make the first move
		while (true) {
            if (amIFirst) {
                // TODO: use a mechanism for timeout(threads, java.util.Timer, ..)

                // call alpha-beta algorithm to get the move
                Move move = getNextBestMove();

                // send move
                System.out.println(String.valueOf(move.getMove()));
                System.out.flush();
            } else {
                // read move
            	String[] moveInfo = input.readLine().split(" ");
                Move move = new Move(Integer.parseInt(moveInfo[0]),Integer.parseInt(moveInfo[1]));

                // check for end
                if (move.getMove() < 0)
                    break;
            }

        }
    }
	
	private static Move getNextBestMove() {
		// TODO Auto-generated method stub
		return null;
	}



	private static void returnNameToRef() {
		// send player name
		System.out.println(teamName);
		System.out.flush();
	}

}
