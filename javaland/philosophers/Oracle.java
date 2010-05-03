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
 * @date 2010-05-03
 */
public class Oracle {
	private java.util.HashMap<String, String> knowledge = null;
	private java.util.Set<String> questionCodes = null;

	/**
	 * The constructor.
	 */
	public Oracle() {
		this.knowledge = new java.util.HashMap<String, String>(5,1);
		this.knowledge.put("3", "The first odd prime number is lucky.");
		this.knowledge.put("7", "The Magnificent created the world.");
		this.knowledge.put("12", "The best prophecies are self-fulfilling.");
		this.knowledge.put("21", "The weather will change.");
		this.knowledge.put("42", "The current President is corrupt!");
		this.questionCodes = this.knowledge.keySet();
	}

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
		java.io.BufferedReader reader = new java.io.BufferedReader(
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
		String questionCode = null;
		java.util.Iterator<String> questionsIterator = this.questionCodes.iterator();
		while (questionsIterator.hasNext()) {
			questionCode = questionsIterator.next();
			if (pInput.contains(questionCode)) {
				System.out.println(this.knowledge.get(questionCode));
			}
		}
		return true;
	}
}
