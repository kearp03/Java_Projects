package boxclass;

public class Box {
	//private member fields
	private double width, height, depth, volume;
	
	//default constructor
	Box() {
		//calls constructor with 1 as the parameter
		this(1);
	}
	
	//one parameter constructor
	Box(double w) {
		//calls constructor with w as all three parameters
		this(w, w, w);
	}
	
	//copy constructor
	Box(Box obj) {
		this(obj.width, obj.height, obj.depth);
	}
	
	//three parameter constructor
	Box(double width, double height, double depth) {
		this.setWidth(width);
		this.setHeight(height);
		this.setDepth(depth);
		this.setVolume();
	}
	
	//public method to change width
	public void setWidth(double width) {
		this.width = width;
		setVolume();
	}
	
	//public method to change height
	public void setHeight(double height) {
		this.height = height;
		setVolume();
	}
	
	//public method to change depth
	public void setDepth(double depth) {
		this.depth = depth;
		setVolume();
	}
	
	//public method to calculate volume
	public void setVolume() {
		volume = width * height * depth;
	}
	
	//public method to return width
	public double getWidth() {
		return width;
	}
	
	//public method to return height
	public double getHeight() {
		return height;
	}
	
	//public method to return depth
	public double getDepth() {
		return depth;
	}
	
	//public method to return volume
	public double getVolume() {
		return volume;
	}
	
	@Override
	public String toString() {
		return "Box [width=" + width + ", height=" + height + ", depth=" + depth + ", volume=" + volume + "]";
	}

	//public method that compares two Box object and returns a boolean
	public boolean equals(Box box) {
		return (this.height == box.height && this.width == box.width && this.depth == box.depth);
	}
}