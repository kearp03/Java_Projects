package linearalgebra;
import java.util.Scanner;

public interface Scanning {
	Scanner scan = new Scanner(System.in);
	
	static String readString(String msg) {
		System.out.print(msg + ": ");
		return scan.nextLine();
	}
	
	static int readInt(String msg) {
		System.out.print(msg + ": ");
		return scan.nextInt();
	}
	
	static double readDouble(String msg) {
		System.out.print(msg + ": ");
		return scan.nextDouble();
	}
}
