package linearalgebra;

public class LinearAlgebra {
	public static void main(String[] args) {
		question7();
	}
	
	static void question7() {
		Vector y = new Vector(3, -1, 1, 13);
		
		Vector W[] = {new Vector(1, -2, -1, 2), new Vector(-4, 1, 0, 3)};
		
		Vector yHat = y.orthoProjection(W);
		
		System.out.println("yHat: " + yHat);
		System.out.println("Distance between y and the subspace W: " + y.vectorSub(yHat).getVectorLength() + " units");
	}
}