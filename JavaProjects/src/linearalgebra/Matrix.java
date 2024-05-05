package linearalgebra;

public class Matrix {
	private double[][] matrix;
	
	public Matrix() {
		this(2);
	}
	
	public Matrix(int n) {
		this(n, n);
	}
	
	public Matrix(int m, int n) {
		matrix = new double[m][n];
		for(int r = 0; r < m; r++) {
			for(int c = 0; c < n; c++) {
				matrix[r][c] = Scanning.readDouble("Row " + (r+1) + ", Column" + (c+1));
			}
		}
	}
	
	public Matrix(double[][] array) {
		matrix = array;
	}
	
	public Matrix(Vector ...vectors) {
		matrix = new double[vectors[0].getVector().length][vectors.length];
		
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[r].length; c++) {
				matrix[r][c] = vectors[c].getVector()[r];
			}
		}
	}
	
	public Matrix transpose() {
		double[][] temp = new double[matrix[0].length][matrix.length];
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[r].length; c++) {
				temp[c][r] = matrix[r][c];
			}
		}
		return new Matrix(temp);
	}
	
	public Matrix matrixAdd(Matrix b) {
		if(this.matrix.length == b.matrix.length && this.matrix[0].length == b.matrix[0].length) {
			double[][] sum = new double[this.matrix.length][this.matrix[0].length];
			for(int r = 0; r < sum.length; r++) {
				for(int c = 0; c < sum[r].length; c++) {
					sum[r][c] = this.matrix[r][c] + b.matrix[r][c];
				}
			}
			return new Matrix(sum);
		}
		System.out.println("ERROR: INCOMPATABLE SIZES");	
		return null;
	}
	
	public Matrix matrixSub(Matrix b) {
		if(this.matrix.length == b.matrix.length && this.matrix[0].length == b.matrix[0].length) {
			double[][] diff = new double[this.matrix.length][this.matrix[0].length];
			for(int r = 0; r < diff.length; r++) {
				for(int c = 0; c < diff[r].length; c++) {
					diff[r][c] = this.matrix[r][c] - b.matrix[r][c];
				}
			}
			return new Matrix(diff);
		}
		System.out.println("ERROR: INCOMPATABLE SIZES");	
		return null;
	}

	public Matrix scalarMultiply(double s) {
		double[][] product = new double[this.matrix.length][this.matrix[0].length];
		for(int r = 0; r < product.length; r++) {
			for(int c = 0; c < product[r].length; c++) {
				product[r][c] = s*this.matrix[r][c];
			}
		}
		return new Matrix(product);
	}
	
	public Matrix matrixMultiply(Matrix b) {
		if(this.matrix[0].length == b.matrix.length) {
			double[][] product = new double[this.matrix[0].length][b.matrix.length];
			for(int r = 0; r < product.length; r++) {
				for(int c = 0; c < product[r].length; c++) {
					double sum = 0;
					for(int i = 0; i < b.matrix.length; i++) {
						sum += this.matrix[r][i] * b.matrix[i][c];
					}
					product[r][c] = sum;
				}
			}
			return new Matrix(product);
		}
		System.out.println("ERROR: INCOMPATABLE SIZES");
		return null;
	}
	
	public double determinant() {
		if(square()) {
			if(this.matrix.length == 2) {
				return (this.matrix[0][0] * this.matrix[1][1]) - (this.matrix[0][1] * this.matrix[1][0]);
			}
			else {
				double determ = 0;
				for(int c = 0; c < this.matrix.length; c++) {
					determ += Math.pow(-1, c+2)*this.matrix[0][c]*this.cofactor(0, c).determinant();
				}
				return determ;
			}
		}
		System.out.println("ERROR: NOT A SQUARE MATRIX");
		return 0;
	}
	
	public Matrix cofactor(int i, int j) {
		if(square()) {
			double[][] temp = new double[this.matrix.length-1][this.matrix[0].length-1];
			for(int r = 0; r < temp.length; r++) {
				for(int c = 0; c < temp[r].length; c++) {
					if(r >= i) {
						if(c >= j) {
							temp[r][c] = this.matrix[r+1][c+1];
						}
						else {
							temp[r][c] = this.matrix[r+1][c];
						}
					}
					else {
						if(c >= j) {
							temp[r][c] = this.matrix[r][c+1];
						}
						else {
							temp[r][c] = this.matrix[r][c];
						}
					}
				}
			}
			return new Matrix(temp);
		}
		System.out.println("ERROR: NOT A SQUARE MATRIX");
		return null;
	}
	
	public Matrix inverse() {
		if(square()) {
			double[][] temp = new double[this.matrix.length][this.matrix[0].length];
			for(int r = 0; r < matrix.length; r++) {
				for(int c = 0; c < matrix[r].length; c++) {
					temp[r][c] = Math.pow(-1, r+c+2) * this.cofactor(r, c).determinant();
				}
			}
			return new Matrix(temp).transpose().scalarMultiply(1/determinant());
		}
		System.out.println("ERROR: NOT A SQUARE MATRIX");
		return null;
	}
	
	public String toString() {
		String temp = "";
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[r].length; c++) {
				temp += matrix[r][c] + "\t";
			}
			temp += "\n";
		}
		
		return temp;
	}
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	public boolean square() {
		return this.matrix.length == this.matrix[0].length;
	}
	
	public boolean equals(Matrix B) {
		if(this.matrix.length == B.matrix.length && this.matrix[0].length == B.matrix[0].length) {
			for(int r = 0; r < this.matrix.length; r++) {
				for(int c = 0; c < this.matrix[r].length; c++) {
					if(this.matrix[r][c] != B.matrix[r][c]) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public static Matrix identityMatrix(int n) {
		double[][] temp = new double[n][n];
		
		for(int i = 0; i < n; i++) {
			temp[i][i] = 1;
		}
		return new Matrix(temp);
	}
}