package basicjava;

import java.util.ArrayList;

public class RecursionExercises {

	public int countDigits(int i) {
		if(i<10)
			return 1;
		else
			return 1 + countDigits(i/ 10);

	}

	public int addDigits(int i) {
		if(i<10)
			return i;
		else
			return i%10+addDigits(i/10);
	}

	public int sum(int i, int j) {
		if(i<0||j<0)
			return 0;
		if(i>j)
			return 0;
		if(i==j)
			return i;
		else 
			return i+ sum(i+1,j);
	}

	public int sumEvenNumbers(int i) {
		if(i<=0)
			return 	0;
		if (i%2!=0) 
	        return sumEvenNumbers(i-1);
	    else
	    	return i+sumEvenNumbers(i-2);
	}

	public int countVowels(String s) {
		char c=' ';
		if(s==null)
			return 0;
		if(s.length()==0)
			return 0;
		else
			c=Character.toLowerCase(s.charAt(0));
		    int count=countVowels(s.substring(1));
		    if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u') 
	            return count + 1;
		    else 
		    	return count;
	}

	public static String removeVowels(String s) {
	    ArrayList<Character> vowels = new ArrayList(4);
	    vowels.add('a');
	    vowels.add('e');
	    vowels.add('i');
	    vowels.add('o');
	    vowels.add('u');
	    if(s==null)
	    	return s;
	    if (s.length() == 0) {
	        return s;
	    }
	    
	    char first = s.charAt(0);
	    String rest = removeVowels(s.substring(1));
	    if (vowels.contains(Character.toLowerCase(first))) {
	        return rest;
	    } else {
	        return first + rest;
	    }
	}


	public int sumOfMultiple(ArrayList<Integer> list) {
		// TODO Auto-generated method stub
		if(list==null)
			return 0;
		if(list.isEmpty())
			return 0;

		int x=list.get(0);
		ArrayList<Integer> rest = new ArrayList<>(list.subList(1, list.size()));
		if(x%5==0)
			return x+sumOfMultiple(rest);
		else
			return sumOfMultiple(rest);
	}

}
