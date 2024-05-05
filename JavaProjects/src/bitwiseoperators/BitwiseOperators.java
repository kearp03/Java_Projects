package bitwiseoperators;

public class BitwiseOperators {
	public static void main(String[] args) {
		int b1 = 0b001001;
		int b2 = 0b010010;
		System.out.println(b1 + ", " + b2);
		int b3 = (~b1 & b2);
		System.out.print(b3);
	}
}
