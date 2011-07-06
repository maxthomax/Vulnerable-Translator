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
 * Test case for the Censor class
 */

package javaland.government;

import junit.framework.TestCase;

/**
 * @author Thomas Herlea
 * @date 2009-05-30
 */
public class CensorTest extends TestCase {
	/**
	 * Test fixture.
	 */
	private Censor c;
	
	/**
	 * Fixture setup.
	 */
	protected void setUp(){
		this.c = new Censor();
	}

	/**
	 * Test method for {@link javaland.government.Censor#censor(java.lang.String)} working correctly.
	 */
	public void testCensorCorrect() {
		String harmless = "blah MDCLXVI blah";
		String harmlessPrefix = "blah M";
		String harmlessSuffix = "I blah";
		String singleharmful = harmlessPrefix + this.c.blacklistedQuestion + harmlessSuffix;
		String multiharmful = this.c.blacklistedQuestion + harmlessPrefix + this.c.blacklistedQuestion +
			harmlessSuffix + this.c.blacklistedQuestion;
		String censored = harmlessPrefix + harmlessSuffix;
		assertEquals("Harmless input censorship", harmless, this.c.censor(harmless));
		assertEquals("Censorship of single blacklisted occurrence", censored, this.c.censor(singleharmful));
		assertEquals("Censorship of multiple blacklisted occurrence", censored, this.c.censor(multiharmful));
	}

	/**
	 * Test method for {@link javaland.government.Censor#censor(java.lang.String)} being vulnerable
	 * to embedding the blacklisted question within itself.
	 * 
	 * For example, censoring "XLII" out of "preXLXLIIIIpost" yields "preXLIIpost", which still contains
	 * "XLII", the string that should not have been present in the output.
	 * 
	 * This is an intentionally vulnerable toy application, so {@link javaland.government.Censor} has
	 * to be incorrect in certain ways.
	 */
	public void testCensorVulnerableToEmbedding() {
		String harmlessPart = " blah ";
		String harmfulPrefix = "XL";
		String harmfulSuffix = "II";
		String harmfulEmbedded = harmlessPart + harmfulPrefix + harmfulPrefix + harmfulSuffix + harmfulSuffix + harmlessPart;
		String incompletelyCensored = harmlessPart + this.c.blacklistedQuestion + harmlessPart;
		assertEquals("Incomplete censorship of embedded blacklisted string", incompletelyCensored,
				this.c.censor(harmfulEmbedded));
	}

}
