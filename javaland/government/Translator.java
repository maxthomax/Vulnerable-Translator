/**
 * The filter put in front of the Oracle by the corrupt President.
 * It will convert inputs expressed with Roman numerals into
 * outputs expressed with Arabic numerals. It is intended to prevent
 * the command 42 from reaching the Oracle. Its standard output and
 * standard error are redirected to the input of the Oracle.
 * Names are collected for future profiling of the citizen.
 */

package javaland.government;

/**
 * @author hthomas
 * @date 2009-05-30
 */
public class Translator {

	/**
	 * The program's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Translator me = new Translator();
		me.run();
	}

	/**
	 * The program's main functionality. It reads name and question, censors
	 * the question and converts it from Roman numerals to Arabic numerals and
	 * replies in a pretty way.
	 */
	private void run() {
		java.io.BufferedReader reader =new java.io.BufferedReader(
				new java.io.InputStreamReader(System.in));
		String name = null;
		String question = null;

		do {
			try {
				System.err.print("Name: ");
				System.err.flush();
				name = reader.readLine();
				
				System.err.print("Question (ONLY Roman numerals!): ");
				System.err.flush();
				question = reader.readLine();

				System.out.print(name + ", your question was "
						+ this.process(question) + "\n");
				System.out.flush();

				Thread.sleep(1000);
			} catch (java.io.IOException e) {
				e.printStackTrace();
			} catch (java.lang.InterruptedException e) {
				e.printStackTrace();
			}
		} while ( ! "".equals(name));
	}

	/**
	 * The question handling functionality of the Translator. It includes
	 * censoring the question to protect the President and converting it from
	 * Roman numerals to Arabic numerals.
	 * 
	 * @param pQuestion
	 * 
	 * @return the censored, converted question
	 */
	private int process(String pQuestion) {
		Censor ce = new Censor();
		Convertor co = new Convertor();
		return co.convert(ce.censor(pQuestion));
	}
}
