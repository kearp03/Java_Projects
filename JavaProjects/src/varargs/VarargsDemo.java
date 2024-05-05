package varargs;

public class VarargsDemo {
	public static void main(String[] args) {
		vTest();
		vTest(1, 2, 3);
		vTest(true, false, true, true);
	}
	
	static void vTest() {
		vTest(0);
		vTest(false);
	}
	
	static void vTest(int ...v) {
		System.out.println("Number of parameters: " + v.length);
		for(int var : v) System.out.println(": " + var);
	}
	
	static void vTest(boolean ...v) {
		System.out.println("Number of parameters: " + v.length);
		for(boolean check : v) {
			System.out.println("This is " + check);
		}
	}
}