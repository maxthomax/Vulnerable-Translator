/**
 * The class simulating the Oracle of Javaland.
 * It will answer when asked about the weather forecast (Code 21).
 * It will reveal whether the President is corrupt (Code 42).
 * It will exit if it receives an empty input.
 * The real Oracle does not answer questions with Code greater than 1023. 
 */

package javaland.philosophers;

/**
 * @author Thomas Herlea
 * @date 2009-05-30
 */
public class Oracle {
	private static String questionWeatherForecast = "21";
	private static String questionIsPresidentCorrupt = "42";

	/**
	 * The program's entry point.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Oracle me = new Oracle();
		me.run();
	}

	/**
	 * The program's main loop.
	 */
	private void run() {
		java.io.BufferedReader reader =new java.io.BufferedReader(
				new java.io.InputStreamReader(System.in));
		String input = null;
		boolean keepRunning = false;
		do {
			try {
				input = reader.readLine();
				keepRunning = this.execute(input);
			} catch (java.io.IOException e) {
				e.printStackTrace();
				keepRunning = false;
			}
		} while (keepRunning);
	}
	
	/**
	 * The Oracle's responder. If the code of any question is anywhere
	 * in the line, the Oracle will answer it.
	 * 
	 * @param pInput one line of input to "execute"
	 * 
	 * @return false if the input was null
	 * @return true otherwise
	 */
	private boolean execute(String pInput) {
		if (pInput == null) {
			return false;
		}
		if (pInput.contains(questionWeatherForecast)) {
			System.out.println("The weather will improve, "+
					"then it will worsen,... " +
					"then it will improve again,...");
		}
		if (pInput.contains(questionIsPresidentCorrupt)) {
			System.out.println("The current President is corrupt!");
		}
		return true;
	}
}
