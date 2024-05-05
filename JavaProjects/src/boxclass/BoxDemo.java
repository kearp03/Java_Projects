package boxclass;
import java.util.Scanner;

public class BoxDemo {
	//Declares variables used throughout the classes methods
	private static Scanner scan = new Scanner(System.in);
	private static Box[] list;
	private static int n;
	
	//main method
	public static void main(String[] args) {
		//reads how many boxes the user wants to create
		System.out.print("Enter how many boxes do you want to create: ");
		n = scan.nextInt();
		
		//initializes an array to hold n many of boxes
		list = new Box[n];
		
		//calls buildList() function to create Box objects for each entry in list
		buildList();
		
		//loops through menu options and operations until loop is changed to false in printMenu()
		boolean loop = true;
		while(loop) {
			loop = printMenu();
		}
	}
	
	//method to create box objects for each entry in list
	public static void buildList() {
		//loops n number of times
		for(int i = 0; i < n; i++) {
			//prints out options and reads users choice
			System.out.print("1) Default box\n"
					+ "2) Cube\n"
					+ "3) Rectangular Box\n"
					+ "4) Copy another box\n"
					+ ":");
			int option = scan.nextInt();
			//if else ladder to check user input
			if(option == 1) {
				//creates default box
				list[i] = new Box();
			}
			else if(option == 2) {
				//creates a box that is a cube
				System.out.print("Enter the width: ");
				double width = scan.nextDouble();
				list[i] = new Box(width);
			}
			else if(option == 3) {
				//creates a box that is a rectangular prism
				System.out.print("Enter the width: ");
				double width = scan.nextDouble();
				System.out.print("Enter the height: ");
				double height = scan.nextDouble();
				System.out.print("Enter the depth: ");
				double depth = scan.nextDouble();
				list[i] = new Box(width, height, depth);
			}
			else if(option == 4) {
				//lets user copy an already made box, corrects for array indices
				System.out.print("Enter the box you would like to copy (1 - " + (i) + "): ");
				int copy = scan.nextInt() - 1;
				list[i] = new Box(list[copy]);
			}
			else {
				System.out.println("Error: Option not recognized, enter again");
				i--;
			}
		}
	}
	
	//method to print menu and loop through until a valid option is chosen
	public static boolean printMenu() {
		boolean loop = true;
		while(loop) {	
			//prints menu and reads user input
			System.out.print("Menu\n"
					+ "a) Print all Boxes\n"
					+ "b) Manipulate a box\n"
					+ "c) Check two boxes for equality\n"
					+ "q) Quit\n"
					+ ": ");
			char choice = scan.next().charAt(0);
			//switch statement to check user input
			switch(choice) {
			case 'a':
				//prints all boxes, breaks loop and switch
				printAll(); 
				loop = false;
				break;
				
			case 'b':
				//allows user to manipulate a box, breaks loop and switch
				manipulateBox();
				loop = false;
				break;
				
			case 'c':
				//allows user to compare two boxes, breaks loop and switch
				compareBoxes();
				loop = false;
				break;
			
			case 'q':
				//exit case
				return false;
				
			default:
				//error message
				System.out.println("ERROR: Option not recognized enter again\n");
			}
		}
		return true;
	}
	
	//method to print all boxes
	public static void printAll() {
		for(int i = 0; i < n; i++) {
			System.out.println("Box " + (i+1));
			System.out.println(list[i] + "\n");
		}
	}
	
	//method that lets user manipulate a single box
	public static void manipulateBox() {
		//asks which box and reads input, corrects for array indices
		System.out.print("Enter which box you would like to manipulate (1 - " + n + "): ");
		int box = scan.nextInt() - 1;
		System.out.println("Box " + (box+1));
		//loops while variable loop is true
		boolean loop = true;
		while(loop) {
			//prints menu and reads user input
			System.out.print("a) Change width"
					+ "b) Change height\n"
					+ "c) Change depth\n"
					+ "d) Print the width\n"
					+ "e) Print the height\n"
					+ "f) Print the depth\n"
					+ "g) Print the volume\n"
					+ "h) Print the Box\n"
					+ "q) Quit\n"
					+ ": ");
			char choice = scan.next().charAt(0);
			//switch statement to check user input
			switch(choice) {
			case 'a':
				//lets user change the width
				System.out.print("Enter the new width: ");
				list[box].setWidth(scan.nextDouble());
				break;
			
			case 'b':
				//lets user change the height
				System.out.print("Enter the new height: ");
				list[box].setWidth(scan.nextDouble());
				break;
			
			case 'c':
				//lets user change the depth
				System.out.print("Enter the new depth: ");
				list[box].setWidth(scan.nextDouble());
				break;
			
			case 'd':
				//prints the width
				System.out.println("Width: " + list[box].getWidth());
				break;
			
			case 'e':
				//prints the height
				System.out.println("Height: " + list[box].getHeight());
				break;
			
			case 'f':
				//prints the depth
				System.out.println("Depth: " + list[box].getDepth());
				break;
			
			case 'g':
				//prints the volume
				System.out.println("Volume: " + list[box].getVolume());
				break;
			
			case 'h':
				//prints the whole box
				System.out.println(list[box]);
				break;
			
			case 'q':
				//quit case, breaks the loop
				loop = false;
			}
		}
	}
	
	//method that lets user compare two boxes
	public static void compareBoxes() {
		//prompts and reads which two boxes to compare, corrects for array indices
		System.out.println("Enter two boxes to compare (1 - "+ n + "):");
		System.out.print("First box: ");
		int box1 = scan.nextInt() - 1;
		System.out.print("Box 2: ");
		int box2 = scan.nextInt() - 1;
		//checks for equality and prints the result
		if(list[box1].equals(list[box2])) System.out.println("The two boxes are the same");
		else System.out.println("The two boxes are different");
	}
}