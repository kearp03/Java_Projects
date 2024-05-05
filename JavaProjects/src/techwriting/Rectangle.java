package techwriting;

public class Rectangle {
	double length;
	double width;
	
	Rectangle(double len, double wid) {
		length = len;
		width = wid;
	}
	
	double area() {
		return length * width;
	}
	
	void print() {
		System.out.println("Rectangle with length " + length
				+ " and width " + width + " has an area of " + area());
	}
}