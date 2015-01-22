package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {
	private static BufferedReader Reader = new BufferedReader(new InputStreamReader(System.in));

	public static String Read() throws Exception {
		return Reader.readLine();
	}
}
