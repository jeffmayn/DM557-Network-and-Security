package dk.sdu.imada.rsa;

import java.math.BigInteger;
import java.util.ArrayList;

public class AlphabetConversion {

	/**
	 * Converts a char to a BigInteger according to the alphabet conversion in the assignment
	 * @param c
	 * @return
	 */
	private static BigInteger charToNumber(char c) {
		BigInteger number = BigInteger.valueOf(0);
		for (int i = 'a'; i <= 'z'; i++){
			if (c == i){
				number = BigInteger.valueOf(i - 'a' + 1);
			}
		}
	return number;
	}

	/**
	 * Converts a number in the interval [0:26] to the corresponding char
	 * @param number The number to convert
	 * @return The converted char
	 */
	private static char numberToChar(BigInteger number) {
		if(number.intValue() == -1){
			throw new IllegalArgumentException("-1");
		}
		if(number.intValue() == 27){
			throw new IllegalArgumentException("27");
		}

		int offset = 96;
		char ch = ' ';
		for (int i = 1; i <= 26; i++){
			if (number.intValue() == i){
				ch = ((char)(i + offset));
			}
		}
		return ch;
	}

	// decode number: n = group cardinality, firstRun = first calculation is special
	public static String decode(int n, BigInteger number, ArrayList list, boolean firstRun){
		String ourString = "";
		if (n > 0){
				StringBuilder str = new StringBuilder(list.size());
				BigInteger firstMod = number.mod(BigInteger.valueOf(27));	// calculate first character value
				BigInteger div = number.divide(BigInteger.valueOf(27)); 	// calculate next sum to division
				BigInteger mod = div.mod(BigInteger.valueOf(27));					// calculate character value

				if (firstRun = true){
					list.add(numberToChar(firstMod));
					firstRun = false;
				} else {
					list.add(numberToChar(mod));
				}
				decode(n-1, div, list, firstRun);

				for (Object i : list){
					str.append(i);
				}
				ourString = str.reverse().toString();
		}
		return ourString;
	}

	/**
	 * Converts a number to a string, according to the alphabet conversion rules of the assignment
	 * @param number
	 * @return
	 */
	public static String numberToString(BigInteger number) {
		ArrayList<Character> list = new ArrayList<>();
		String ourString = decode(5, number, list, true);
		return ourString;
	}

	/**
	 * Convert a string of length 5 to a BigInteger number
	 * @param string The string to convert
	 * @return The converted number
	 */
	public static BigInteger stringToNumber(String string) {
		ArrayList<BigInteger> characterList = new ArrayList<>();
		createList(characterList, string, true);
	//	System.out.println("|-- block --> '"+characterList+"'");

		int n = 4;
		int sum = 0;
		for (int i = 0; i < 5; i++){
			BigInteger hm = characterList.get(i);
			sum += CalculateCharacterConversion(characterList, hm, n);
			n--;
		}
		return BigInteger.valueOf(sum);
	}

	// from a string, the individual character's decimal representation is created.
	public static ArrayList createList(ArrayList list, String string, boolean isLetters){
		int size = string.length()-1;

		if(isLetters == true){
			for (int i = 0; i <= size; i++){
				list.add(charToNumber(string.charAt(i)));

			} return list;
		} else {
			for (int i = 0; i <= size; i++){
				list.add(string.charAt(i));
			} return list;
		}
	}

	// computes the plaintext number
	public static int CalculateCharacterConversion(ArrayList a, BigInteger i, int powerOf){
		int sum = i.intValue() * (int)Math.pow(27, powerOf);
	//	System.out.println("\n############ CalculateCharacterConversion() ############");
	//	System.out.println("pow[" + powerOf +"] --> '"+sum+"'");
		return sum;
	}
}
