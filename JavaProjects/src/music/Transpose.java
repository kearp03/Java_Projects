package music;
import java.util.Scanner;

public class Transpose {
	/*The Transpose class transposes lists of notes entered as Strings between an original key and the desired transposed key*/
	
	/* 
	 * arrays initialized as length 2 hold data for original and transposed notes
	 * [0] => original key
	 * [1] => transposed key
	 */
	
	/********************************************************************************/
	/*Data Members*/
	
	//array of notes in chromatic order
	private static final String[] transposeList = 
		{"C", "C# Db", "D", "D# Eb", "E", "F", "F# Gb", "G", "G# Ab", "A", "A# Bb", "B"};
	
	//array to hold entered keys as Strings, and 2D array to hold entered notes and their transposition
	private String[] sKeys = new String[2];
	
	//interval between the two keys, and an int array to hold transposeList array indices of starting and ending keys
	private int interval, iKeys[] = new int[2];
	
	//boolean to determine whether transposition should be in sharps or flats
	private boolean sharp;
	
	//Scanner object to handle user input
	private static final Scanner scan = new Scanner(System.in);
	
	
	/********************************************************************************/
	/*Constructor*/
	
	//no-parameter constructor
	public Transpose() {
		/* 
		 * reads the original and transposed keys and stores the Strings in sKeys[]
		 *  and the ints representing the index in transposeList in iKeys[]
		 */
		initalizeKey("original", 0);
		initalizeKey("transposed", 1);
		//determines the sharps boolean based on the entered transposed key
		sharpsOrFlats();
		//calculates the interval
		setInterval();
	}
	
	
	/********************************************************************************/
	/*Private Methods*/
	
	//private method to read the key and assign the index to iKeys[pos]
	private void initalizeKey(String msg, int pos) {
		//do while loop to make sure a valid key is entered
		do {
			//prompts, reads key, and determines the index of the key
			keyNumber(readString("Enter the " + msg + " key"), pos);
		} while(iKeys[pos] == -1); //loops until iKeys[pos] does not equal to -1
	}
	
	//private method to assign a int value for a given String key
	private void keyNumber(String key, int pos) {
		sKeys[pos] = key;
		//loops through transposeList
		for(int i = 0; i < transposeList.length; i++) {
			//sets iKeys[pos] = i if key is equal to current element of transposeList
			if(equals(key, transposeList[i])) {
				iKeys[pos] = i;
				//terminates method
				return;
			}
		}
		//sets iKeys[pos] = -1 if loop is completed before a return statement is reached
		iKeys[pos] = -1;
	}
	
	/* 
	 * private method to determine if sharps or flats should be used based on the key
	 * true = use sharps
	 * false = use flats
	 */
	private void sharpsOrFlats() {
		//first checks to see if sKey[1] length is greater than 1
		if(sKeys[1].length() > 1) {
			//assigns true to sharp if the transposed key is a key that uses sharps
			sharp = sKeys[1].substring(0,2).equalsIgnoreCase("C#") || sKeys[1].substring(0,2).equalsIgnoreCase("D#") 
					|| sKeys[1].substring(0,2).equalsIgnoreCase("F#") || sKeys[1].substring(0,2).equalsIgnoreCase("G#") 
					|| sKeys[1].substring(0,2).equalsIgnoreCase("A#");
		}
		else {
			//assigns true to sharp if the transposed key is a key that uses sharps
			sharp = sKeys[1].equalsIgnoreCase("C") || sKeys[1].equalsIgnoreCase("D") || sKeys[1].equalsIgnoreCase("E") 
					|| sKeys[1].equalsIgnoreCase("G") || sKeys[1].equalsIgnoreCase("A") || sKeys[1].equalsIgnoreCase("B");
		}
	}
	
	/* 
	 * private method to determine the interval,
	 * ensures the interval is a positive number between 0 and 11
	 */
	private void setInterval() {
		/* 
		 * calculates the interval
		 * if the interval is already positive, then the +12 and %12 do nothing
		 * but if the interval is negative, then the +12 and %12 
		 * ensures that the interval is a positive number between 0 - 11
		 */
		interval = (iKeys[1] - iKeys[0] + 12) % 12;
	}
	
	/* 
	 * private method to set the index i of transposedNotes to the 
	 * correct note based on the entered note and interval
	 */
	private String findTransposedNote(String note) {
		//loops through transposeList looking for the note equal to the entered note
		for(int j = 0; j < transposeList.length; j++) {
			//checks to see if the current note equals the original note
			if(equals(note, transposeList[j])) {
				/* 
				 * sets the i index of notes[1] equal to the (j+interval)%12 index of transposeList
				 * (j+interval)%12 is the index of the note that is the correct interval away from the entered note
				 * the %12 ensures the index is between 0-11
				 * if the transposed note's length is not 1, it sets it equal 
				 * to either the sharp or flat version based on the boolean sharps
				 */
				int newIndex = (j+interval) % 12;
				return 
					transposeList[newIndex].length() == 1 
						? transposeList[newIndex]
						: sharp
							? transposeList[newIndex].substring(0,2)
							: transposeList[newIndex].substring(3);
			}
		}
		//if the current index did not get set to anything, sets the Note to an empty String
		return "";
	}
	
	/* 
	 * private method to check the equality of two note Strings, taking into account 
	 * the possibility that the note1 String might be equal to the sharp or flat version of the note2 String
	 */
	private static boolean equals(String note1, String note2) {
		//checks to see if the two Strings' length is greater than 1
		if(note1.length() > 1 && note2.length() > 1) {
			return note1.equalsIgnoreCase(note2.substring(0,2)) || note1.equalsIgnoreCase(note2.substring(3));
		}

		//checks to see if the two Strings' length are both 1
		else if(note1.length() == 1 && note2.length() == 1) {
			return note1.equalsIgnoreCase(note2);
		}
		
		//returns false otherwise
		return false;
	}
	
	private static String readString(String msg) {
		System.out.print(msg + ": ");
		return scan.nextLine();
	}

	
	/********************************************************************************/
	/*Public Methods*/
	
	/* 
	 * public method to print the contents of an object
	 * prints the original notes in a list
	 * then prints the transposed notes in a list
	 */
	public void print() {
		//arrays to hold the two types of keys and arrays of notes
		String[] keyType = {"Original", "Transposed"};
		//loops through types of keys
		for(int i = 0; i < keyType.length; i++) {
			//prints information about the keys
			System.out.printf("%-10s: %-2s\n", keyType[i], sKeys[i]);
		}
		System.out.printf("%-10s: %-2d\n", "Interval", interval);
	}
	
	/* 
	 * public method to change the original key
	 * updates interval
	 */
	public void setOriginalKey() {
		initalizeKey("new original", 0);
		setInterval();
	}
	
	/* 
	 * public method to change the transposed key
	 * updates interval and the sharps boolean
	 */
	public void setTransposeKey() {
		initalizeKey("new transposed", 1);
		sharpsOrFlats();
		setInterval();
	}
	
	//public method to enter notes
	public int enterNote() {
		//do while loop to ensure note entered is valid
		String tnote = "initialized";
		do {
			if(tnote.equals("")) System.out.println("Error: Note not found");
			System.out.print("\nNote: ");
			String note = scan.nextLine();
			if(note.toLowerCase().equals("quit")) return -1;
			//calls method to determine the transposed note for the entered note
			tnote = findTransposedNote(note);
		} while(tnote.equals(""));
		System.out.printf("New note: %-2s", tnote);
		return 0;
	}
}