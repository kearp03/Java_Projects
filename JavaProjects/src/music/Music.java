package music;
import java.util.Scanner;

public class Music {
	//prepares Scanner object to be used
	static final Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		musicMenu();
	}
	
	public static void musicMenu() {
		//infinite loop until System.exit(0); is reached
		while(true) {
			//prints menu and reads user input
			System.out.print("Main Menu\n"
					+ "-----------------------------------------\n"
					+ "1) Transpose a list of notes\n"
					+ "2) Find the relative minor of a major key\n"
					+ "3) Find the realtive major of a minor key\n"
					+ "4) Quit\n"
					+ ": ");
			int iChoice = scan.nextInt();
			scan.nextLine();
			//switch statement to choose right method call
			switch(iChoice) {
				//case 1: transposition method
				case(1):
					transposition();
					break;
				
				//case 2 and 3: major-minor relative keys method
				case(2): 
				case(3):
					majorminor(iChoice);
					break;
					
				//case 4: exit
				case(4):
					System.exit(0);
				
				//default case: error message
				default:
					error();
			}
			//new line at the end of each loop
			System.out.println();
		}
	}
	
	//private method to handle transposition
	private static void transposition() {
		//creates Transpose object
		Transpose transposedNotes = new Transpose();
		
		//loops until return statement is reached
		while(true) {
			//prints menu and reads user input
			System.out.print("Transpose Menu\n"
					+ "----------------------------------------------------------\n"
					+ "1) Print the notes\n"
					+ "2) Change the number of notes (you must reenter the notes)\n"
					+ "3) Reenter the notes\n"
					+ "4) Reenter a specific note\n"
					+ "5) Change the original key\n"
					+ "6) Change the transposed key\n"
					+ "7) Return to Main Menu\n"
					+ ": ");
			switch(scan.nextInt()) {
				//case 1: prints the object
				case(1):
					transposedNotes.print();
					break;
					
				//case 2: resizes noteList array and makes user reenter notes
				case(2):
					transposedNotes.setNoteListLength();
				
				//case 3: lets user reenter notes
				case(3):
					scan.nextLine();
					for(int i = 0; i < transposedNotes.getNotesLength(); i++) {
						transposedNotes.setNote(i);
					}
					break;
					
				//case 4: lets user reenter a specific note
				case(4):
					int index;
					do {
						System.out.print("Enter the index of the note you wish to change: ");
						index = scan.nextInt();
					} while(index < transposedNotes.getNotesLength()-1 && index >=0);
					transposedNotes.setNote(index);
					break;
					
				//case 5: lets user change original key
				case(5):
					transposedNotes.setOriginalKey();
					break;
					
				//case 6: lets user change transposed key
				case(6):
					transposedNotes.setTransposeKey();
					break;
					
				//case 7: returns to main menu
				case(7):
					return;
				
				//default case: prints error message
				default:
					error();
			}
			//new line at the end of each loop
			System.out.println();
		}
	}
	
	//private method to print relative major-minor keys
	private static void majorminor(int iChoice) {
		switch(iChoice) {
			//case 2: calls majorToMinor method
			case(2):
				/* prompts and reads key from user, then calls majorToMinor from 
				 * MajorMinor class to print the relative minor of the entered key
				 */
				System.out.print("Enter the major key: ");
				MajorMinor.majorToMinor(scan.nextLine());
				break;
			
			//case 3: calls minorToMajor method
			case(3):
				/* prompts and reads key from user, then calls minorToMajor from 
				 * MajorMinor class to print the relative major of the entered key
				 */
				System.out.print("Enter the minor key: ");
				MajorMinor.minorToMajor(scan.nextLine());
				break;
		}
	}
	
	private static void error() {
		System.out.println("ERROR: Entry not recognized\n");
	}
}