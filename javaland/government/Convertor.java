/**
 * The convertor of the question from Roman numerals to Arabic numerals.
 * 
 * Conversion: Roman numbers are the sum of the values of the blocks
 * of symbols they consist of.
 * 
 * Values for blocks are computed by multiplying the row heading
 * with the column heading of the cell in which the block is found:
 * 		*1		*10		*100	*1000
 * 1	I		X		C		M
 * 2	II		XX		CC
 * 3	III		XXX		CCC
 * 4	IV		XL		CD
 * 5	V		L		D
 * 6	VI		LX		DC
 * 7	VII		LXX		DCC
 * 8	VIII	LXXX	DCCC
 * 9	IX		XC		CM
 * 
 * Example:
 * MMIX = M + M + IX = 1000 + 1000 + 9 = 2009
 */

package javaland.government;

/**
 * @author Thomas Herlea
 * @date 2009-05-30
 */
public class Convertor {
	/**
	 * Mapping from a symbol to the other symbols which cause its value
	 * to change sign.
	 * 
	 * There are two ways in which a symbol is used:
	 * a) it either adds its value to the total:
	 * - V, L, D, M always
	 * - I, X, C in blocks like I, XX, LX, DCCC
	 * b) or it subtracts its value from the total:
	 * - I in IV and IX
	 * - X in XL and XC
	 * - C in CD and CM
	 * 
	 * We will call V and X "special successors" of I, because
	 * if they succeed I in the string, then I must be subtracted.
	 * The special successors of X are L and C.
	 * The special successors of C are D and M.
	 */
	private java.util.HashMap<Character, char[]> specialSuccessors;

	/**
	 * Mapping of symbol to absolute value.
	 * Considering special successors, there are just 7 symbols
	 * with 7 absolute values (that is 4 times less complex than
	 * working with the "official" 28 blocks!)
	 * I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000 
	 */
	private java.util.HashMap<Character, Integer> symbolValues;
	
	/**
	 * Constructor of the Convertor class. Initializes lookup tables used in
	 * the conversion.
	 */
	public Convertor() {
		this.specialSuccessors = new java.util.HashMap<Character, char[]>(7, 1);
		char[] x;	// auxiliary variable
		
		// special successors for 'I'
		x = new char[2]; x[0] = 'V'; x[1] = 'X';
		this.specialSuccessors.put(new Character('I'), x);
		// special successors for 'X'
		x = new char[2]; x[0] = 'L'; x[1] = 'C';
		this.specialSuccessors.put(new Character('X'), x);
		// special successors for 'C'
		x = new char[2]; x[0] = 'D'; x[1] = 'M';
		this.specialSuccessors.put(new Character('C'), x);
		
		this.symbolValues = new java.util.HashMap<Character, Integer>(7, 1);
		this.symbolValues.put('I', 1);
		this.symbolValues.put('V', 5);
		this.symbolValues.put('X', 10);
		this.symbolValues.put('L', 50);
		this.symbolValues.put('C', 100);
		this.symbolValues.put('D', 500);
		this.symbolValues.put('M', 1000);
	}
	
	/**
	 * Bring the question code within range of Oracle inputs if it is not.
	 * The specs of the Oracle say that it does not answer questions with code
	 * greater than 1023 (binary "1111111111"). If the user accidentally asks
	 * a question that is out of range, it is remapped here to a question
	 * within range. This protects the Oracle and it gives the user some
	 * useful information instead of nothing.
	 * 
	 * @param unnormalized the raw result of the Roman-to-Arabic conversion
	 * 
	 * @return a value within the range of Oracle questions 
	 */
	private int normalize(int unnormalized) {
		return unnormalized % 1024;
	}

	/**
	 * Computes the value of a number represented with Roman numerals.
	 * 
	 * Algorithm:
	 * Scan the string, identify symbols and retrieve their value.
	 * For each symbol found, if there is a special successor behind it
	 * the value becomes negative.
	 * The values are added up to obtain the value of the number.
	 *  
	 * @param roman number represented with Roman numerals
	 * 
	 * @return value of the number
	 */
	private int romanToArabic(String roman) {
		int partialSum = 0;
		char symbol = '\0';
		int symbolValue = 0;
		java.util.Set<Character> validSymbols = this.symbolValues.keySet();
		char[] specSucc = null;

		for (int i = 0; i < roman.length(); i++) {
			symbol = roman.charAt(i);
			if (validSymbols.contains(symbol)) {
				symbolValue = this.symbolValues.get(symbol);
				specSucc = this.specialSuccessors.get(symbol);
				if ( specSucc != null ) {
					if ((roman.indexOf(specSucc[0],i+1) != -1) ||
							(roman.indexOf(specSucc[1], i+1) != -1)) {
						symbolValue = -symbolValue;
					}
				}
				partialSum += symbolValue;
//				System.out.println("" + symbol + '-' + symbolValue + '-' + partialSum);
			}
		}
		return partialSum;
	}

	/**
	 * Takes care of evaluating the parameter's value and of normalising it.
	 * 
	 * @param pRoman number represented with Roman numerals
	 * 
	 * @return normalised value of the number
	 */
	public int convert(String pRoman) {
		return this.normalize(this.romanToArabic(pRoman.toUpperCase()));
	}
}
