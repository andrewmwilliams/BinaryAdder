/*Andrew Williams 11/25/14
 * 
 * Solution for the ACM ICPC problem given here
 * http://acmgnyr.org/year2005/a.pdf
 * 
 * Adds together binary numbers from an input file and prints result
 */

package BinaryAdder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinaryAddition {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("a.in"));
		String firstOp, secondOp, res;
		int numSums = in.nextInt();
		
		//Read each line from the data file and print the result
		for(int currSum = 0; currSum < numSums; currSum++) {
			firstOp = in.next();
			secondOp = in.next();
			res = add(firstOp, secondOp);
			System.out.println((currSum + 1) + ": " + res);
		}
	}
	
	public static String add(String firstOp, String secondOp) {
		//It's easier to solve this problem if the strings are the same length, so
		//we start by padding the shorter one if they aren't equal
		if(firstOp.length() != secondOp.length()) {
			int firLen = firstOp.length();
			int secLen = secondOp.length();
			if(firLen < secLen) {
				for(int i = 0; i < (secLen - firLen); i++)
					firstOp = "0" + firstOp;
			} else {
				for(int i = 0; i < (firLen - secLen); i++)
					secondOp = "0" + secondOp;
			}
		}//end IF operands are of differing sizes
		
		return rAdd(firstOp, secondOp, '0');
	}
	
	public static String rAdd(String firstOp, String secondOp, char cbit) {
		//Get the bits we're actually working with
		int len = firstOp.length();
		char fbit = firstOp.charAt(len - 1);
		char sbit = secondOp.charAt(len - 1);
		
		//Base case - we just have the left most bits
		if (len == 1) {
			if(fbit == '0' && sbit == '0' && cbit == '0')
				return "0";
			else if(fbit == '0' && sbit == '0' && cbit == '1')
				return "1";
			else if(fbit == '0' && sbit == '1' && cbit == '0')
				return "1";
			else if(fbit == '0' && sbit == '1' && cbit == '1')
				return "10";
			else if(fbit == '1' && sbit == '0' && cbit == '0')
				return "1";
			else if(fbit == '1' && sbit == '0' && cbit == '1')
				return "10";
			else if(fbit == '1' && sbit == '1' && cbit == '0')
				return "10";
			else if(fbit == '1' && sbit == '1' && cbit == '1')
				return "11";
		}//end if comparing left most bit
		
		
		//If we have more than the left most bits, recurse
		if(fbit == '0' && sbit == '0' && cbit == '0')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '0') + '0';
		else if(fbit == '0' && sbit == '0' && cbit == '1')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '0') + '1';
		else if(fbit == '0' && sbit == '1' && cbit == '0')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '0') + '1';
		else if(fbit == '0' && sbit == '1' && cbit == '1')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '1') + '0';
		else if(fbit == '1' && sbit == '0' && cbit == '0')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '0') + '1';
		else if(fbit == '1' && sbit == '0' && cbit == '1')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '1') + '0';
		else if(fbit == '1' && sbit == '1' && cbit == '0')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '1') + '0';
		else if(fbit == '1' && sbit == '1' && cbit == '1')
			return rAdd(firstOp.substring(0, len - 1), secondOp.substring(0, len - 1), '1') + '1';
	
		return null;  //I believe (and sincerely hope) this will never happen
	}
	
	
}
