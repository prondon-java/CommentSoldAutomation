package TestCases;

import java.util.Arrays;

public class Increment {

	public static void main(String[] args) {
		
		int input = 2790;
		
		int[] output = addOne(intToIntArray(input));
		
		//Print Result
		System.out.println(Arrays.toString(output));
	}
	
	public static int[] intToIntArray(int number) {
		
		String str = Integer.toString(number); 

		int[] intArray = new int[str.length()]; 


		for(int i=0; i<str.length(); i++){
		    intArray[i] = Character.getNumericValue(str.charAt(i));
		}
		
		return intArray;
	}
	
	public static int[] addOne(int[] array) {
		
		int lastDigit = array.length - 1;
		
		while(lastDigit >= 0) {
			if(array[lastDigit] != 9) {
				array[lastDigit] = ++array[lastDigit];
				return array;
			}
			
			array[lastDigit] = 0;
			lastDigit--;
		}
		
		int[] array2 = new int[array.length + 1];
		array2[0] = 1;
		return array2;
	}
	
	public static int intArrayToInt(int[] array) {
		
		StringBuilder s = new StringBuilder(); 

	    for (int i : array)
	    {
	         s.append(i);
	    }

	    return Integer.valueOf(s.toString());
	}
}
