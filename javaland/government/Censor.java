/**
 * The censor of the questions. In an attempt to prevent citizens from asking
 * the Oracle the question whether the President is corrupt (code 42), this
 * class contains functionality to remove the question (XLII, with Roman
 * numerals) from the input. In anticipation of a wider list of forbidden
 * questions, the blacklist can be controlled with system properties.
 */

package javaland.government;

/**
 * @author hthomas
 * @date 2009-05-30
 */
public class Censor {
	
	/**
	 * The hardcoded question to censor out of questions.
	 */
	String blacklistedQuestion = "XLII";
	
	/**
	 * Removes the forbidden question from the input string.
	 * 
	 * @param input
	 * 
	 * @return the censored input string
	 */
	public String censor(String input) {
		return input.replace(blacklistedQuestion, "");
	}
}
