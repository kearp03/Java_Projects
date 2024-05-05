package palindromecount;

import java.util.Scanner;

public class PalindromeCount {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		//reads number from user
		int n = intRead("Enter how many numbers you want checked");
		//initializes count to 0
		int count = 0;
		
		//loops as many times as the user input
		for(int i = 1; i <= n; i++) {
			if(checkPalindrome(i)) count++;
			System.out.print("(" + i + "," + count + ") ");
			if(i%10 == 0) System.out.println();
		}
	}
	
	//static member function that returns a read int value
	static int intRead(String prompt) {
		System.out.print(prompt + ": ");
		return scan.nextInt();
	}
	
	//static member function to reverse a number and checks if it's a palindrome
	static boolean checkPalindrome(int iNumber) {
		boolean check = false;
		int[] temp_reverse = {iNumber, 0};
		while(temp_reverse[0] != 0) {
			reverseNumber(temp_reverse);
		}
		if(iNumber == temp_reverse[1]) {
			check = true;
		}
		return check;
	}
	
	//static member function that builds a reverse
	static void reverseNumber(int[] a) {
		a[1] = buildReverse(a[1], a[0]%10);
		a[0] /= 10;
	}
	
	//static member function to mathematically build the reverse number
	static int buildReverse(int reverse, int n) {
		return reverse*10 + n;
	}
}