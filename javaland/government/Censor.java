/*
    Part of "Vulnerable Translator", which allows anyone to experience "hacking".
    Copyright (C) 2009  Thomas Herlea

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
		/*
		 * NOTE: We used to compare the value of 'input' with the
		 * blacklisted string, but that allowed attackers to sneak
		 * the blacklisted value through by padding it with non-Roman
		 * digits: e.g. "aXLIIb". Now the blacklisted value is
		 * deleted, leaving only the non-Roman digits, which are
		 * ignored by the Oracle.
		 */
		return input.replace(blacklistedQuestion, "");
	}
}
