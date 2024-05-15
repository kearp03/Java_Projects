package music;
import java.util.Scanner;

public class Music {
	//prepares Scanner object to be used
	private static final Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		musicMenu();
	}
	
	public static void musicMenu() {
		//infinite loop until System.exit(0); is reached
		while(true) {
			//prints menu and reads user input
			System.out.print("Main Menu\n"
					+ "-----------------------------------------\n"
					+ "1) Transpose notes\n"
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
					+ "1) Enter notes\n"
					+ "2) Change the original key\n"
					+ "3) Change the transposed key\n"
					+ "4) Print information\n"
					+ "5) Return to Main Menu\n"
					+ ": ");
			switch(scan.nextInt()) {
				//case 1: lets user enter notes
				case(1):
					System.out.println("Enter notes\nEnter \'Quit\' to exit");
					int stop = 0;
					do{
						stop = transposedNotes.enterNote();
					}while(stop == 0);
					break;
				
				//case 2: lets user change original key
				case(2):
					transposedNotes.setOriginalKey();
					break;
					
				//case 3: lets user change transposed key
				case(3):
					transposedNotes.setTransposeKey();
					break;
				
				//case 4: prints out information about the transposition object
				case(4):
					transposedNotes.print();
					break;
				
				//case 5: returns to main menu
				case(5):
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