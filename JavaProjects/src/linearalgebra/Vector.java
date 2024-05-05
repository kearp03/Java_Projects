package linearalgebra;

public class Vector {
	/* The Vector class handles the storage of vectors and their lengths
	 * and defines vector mathematical methods, as well as orthogonalizing sets of vectors
	 */
	
	//double array to hold vector values, and double variable to hold length of the vector
	private double vector[], vectorLength;
	
	//default constructor, lets user enter the size
	public Vector() {
		this(Scanning.readInt("Size"));
	}
	
	//one-param constructor, sets the size to parameter value
	public Vector(int size) {
		vector = new double[size];
	}
	
	//varargs constructor, sets vector array to the entered values
	public Vector(double ...vals) {
		vector = vals;
		vectorLength = this.vectorLength();
	}
	
	public Vector(Vector v) {
		this(v.vector);
	}
	
	//method to enter the values of the vector
	public void setVals() {
		for(int i = 0; i < vector.length; i++) this.vector[i] = Scanning.readDouble("Row " + (i+1));
		vectorLength = this.vectorLength();
	}
	
	//method to add two vectors together, returns the sum as a new Vector object
	public Vector vectorAdd(Vector v) {
		double[] temp = new double[vector.length];
		if(lengthEqu(this, v)) for(int i = 0; i < temp.length; i++) temp[i] = this.vector[i] + v.vector[i];
		return new Vector(temp);
	}
	
	//method to subtract two vectors, returns the difference as a new Vector object
	public Vector vectorSub(Vector v) {
		return this.vectorAdd(v.scalarMult(-1));
	}
	
	//method to multiply a vectors by a scalar, returns the product as a new Vector object
	public Vector scalarMult(double c) {
		double[] temp = new double[vector.length];
		for(int i = 0; i < vector.length; i++) temp[i] = c * this.vector[i];
		return new Vector(temp);
	}
	
	//method to calculate the dot product of two vectors, returns the dot product as a double
	public double dotProduct(Vector v) {
		double sum = 0;
		if(lengthEqu(this, v)) for(int i = 0; i < this.vector.length; i++) sum += this.vector[i] * v.vector[i];
		return sum;
	}
	
	//method to calculate the length of a vector
	public double vectorLength() {
		return Math.sqrt(this.dotProduct(this));
	}
	
	//method to normalize a vector, returns the normalized vector as a new Vector object
	public Vector normalize() {		
		return new Vector(this.scalarMult(1/this.vectorLength));
	}
	
	//method to calculate the orthogonal projection of the Vector object onto u
	public Vector orthoProjection(Vector u) {
		double[] proj = new double[this.vector.length];
		if(lengthEqu(this, u)) {
			double c = this.dotProduct(u) / u.dotProduct(u);
			for(int i = 0; i < proj.length; i++) proj[i] = c*u.vector[i];
		}
		return new Vector(proj);
	}
	
	//method to calculate the orthogonal projection of the Vector object onto the subspace W, spanned by W[]
		public Vector orthoProjection(Vector[] W) {
			Vector proj = new Vector(W[0].vector.length);
			if(lengthEqu(this, W[0]) && orthongonal(W)) {
				for(int i = 0; i < W.length; i++) {
					proj = proj.vectorAdd(this.orthoProjection(W[i]));
				}
			}
			proj.vectorLength = proj.vectorLength();
			return proj;
		}
	
	//method to calculate the orthogonal decomposition of the Vector object onto u
	public Vector[] orthoDecomp(Vector u) {
		Vector[] decomp = {this.orthoProjection(u), null};
		decomp[1] = this.vectorSub(decomp[0]);
		return decomp;
	}
	
	public Vector[] orthoDecomp(Vector[] W) {
		Vector[] decomp = {this.orthoProjection(W), null};
		decomp[1] = this.vectorSub(decomp[0]);
		return decomp;
	}
	
	//method to calculate the linear combination of the Vector object in terms of an orthogonal basis
	public Vector[] orthoLinearComb(Vector[] basis) {
		Vector[] linearComb = new Vector[basis.length];
		for(int i = 0; i < linearComb.length; i++) {
			linearComb[i] = this.orthoProjection(basis[i]);
		}
		return linearComb;
	}
	
	//method to determine if two vectors have the same length
	public static boolean lengthEqu(Vector v, Vector u) {
		if(v.vector.length == u.vector.length) return true;
		else {
			System.out.println("INCOMPATABLE SIZES");
			return false;
		}
	}
	
	//method to orthongonalize a set of Vector objects
	public static Vector[] orthongonalize(Vector[] basis) {
		Vector[] orthoBasis = new Vector[basis.length];
		for(int i = 0; i < orthoBasis.length; i++) {
			orthoBasis[i] = basis[i];
			for(int j = 0; j < i; j++) {
				orthoBasis[i] = orthoBasis[i].vectorSub(orthoBasis[i].orthoProjection(basis[j]));
			}
		}
		return orthoBasis;
	}
	
	//method to orthonormalize a set of Vector objects
	public static Vector[] orthoNormalize(Vector[] basis) {
		Vector[] orthoBasis = orthongonal(basis) ? basis : orthongonalize(basis);
		Vector[] orthoNormBasis = new Vector[orthoBasis.length];
		for(int i = 0; i < orthoNormBasis.length; i++) {
			orthoNormBasis[i] = orthoBasis[i].normalize();
		}
		return orthoNormBasis;
	}
	
	//method to print Vectors as a sum
	public static String printVectorSum(Vector... v) {
		String summation = "";
		for(int i = 0; i < v.length-1; i++) {
			summation += v[i] + " + ";
		}
		return summation + v[v.length-1];
	}
	
	public static boolean orthongonal(Vector[] basis) {
		for(int i = 0; i < basis.length; i++) {
			for(int j = 0; j < basis.length; j++) {
				if(i != j && basis[i].dotProduct(basis[j]) != 0) 
					return false;
			}
		}
		return true;
	}
	
	//method to convert a Vector object to a String
	@Override
	public String toString() {
		String temp = "<";
		for(int i = 0; i < vector.length-1; i++) temp += vector[i] + ", ";
		return temp + vector[vector.length-1] + ">";
	}
	
	//getter methods
	public double[] getVector() {
		return vector;
	}
	
	public int getVectorSize() {
		return vector.length;
	}
	
	public double getVectorLength() {
		return vectorLength;
	}
	
	//setter method
	public void setVector(double... vals) {
		vector = vals;
		vectorLength = this.vectorLength();
	}
}