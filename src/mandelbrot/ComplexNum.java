package mandelbrot;

/**
 * A complex number of the form x+iy
 * @author Nate Young
 *
 */
public class ComplexNum {
	public double x;
	public double y;
	public ComplexNum(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public ComplexNum(ComplexNum o){
		this(o.x,o.y);
	}
	
	public double abs(){
		return Math.sqrt(x*x+y*y);
	}
	public ComplexNum square(){
		return new ComplexNum(x*x-y*y,2*x*y);
	}
	public ComplexNum add(ComplexNum o){
		return new ComplexNum(x+o.x,y+o.y);
	}
	public ComplexNum sub(ComplexNum o){
		return new ComplexNum(x-o.x,y-o.y);
	}
	public ComplexNum sqrt(){
		double rn = Math.sqrt(r());
		double tn = theta()/2;
		return new ComplexNum(rn*Math.cos(tn),rn*Math.sin(tn));
	}
	
	public double r(){
		return abs();
	}
	public double theta(){
		return Math.atan2(y, x);
	}
}
