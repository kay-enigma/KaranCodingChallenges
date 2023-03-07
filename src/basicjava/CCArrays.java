package basicjava;

import java.util.Arrays;



public class CCArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void replace(char[] chars, char c, char d) {
		int len = chars.length;
		for(int x = 0; x<len ; x++) {
			char U = Character.toLowerCase(c); 									// Convertion of Character C to lower case.
			char L = Character.toUpperCase(c); 									// Convertion of Character C to upper Case.
			if(chars[x] == c || chars[x] == U || chars[x] == L) {
				chars[x] = d;  													// Replacement of char c with char d
			}
		}
		
	}

	public static void sortAlphabetic(String[] strs) {
		Arrays.sort(strs, String.CASE_INSENSITIVE_ORDER); 					// The CASE_INSENSITIVE_ORDER sorts the Array of Strings
																			//without differentiating between Upper and Lower Case
		
		
		
	}

}
