package Circle;
import java.math.*;

public class Circle {
	private double r,x,y;

	public Circle(double x, double y, double r) throws NumberFormatException{
		if (r < 0) {
			throw new NumberFormatException("please provide non-negative radios");
		}
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public String toString() {
		return "o=("+this.x+","+this.y+"),r="+this.r;
	}
	
	public Boolean doesOverlap(Circle c) {
		double distance;
		distance = Math.sqrt(Math.pow((c.x - this.x),2) + Math.pow((c.y - this.y),2));
		return distance < (c.r + this.r);
	}
	
	public static void main (String[] args) {
		Circle A,B,C;
		
		A = new Circle(0., 0., 3.);
		B = new Circle(0., 5., 3.);
		C = new Circle(5., 5., 3.);
		
		System.out.println("A: " + A);
		System.out.println("B: " + B);
		System.out.println("C: " + C);
		
		System.out.println("AB: " + A.doesOverlap(B));
		System.out.println("BC: " + B.doesOverlap(C));
		System.out.println("AC: " + A.doesOverlap(C));
		
	}

}
