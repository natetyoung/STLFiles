package src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/** A class to represent a vector in Euclidean 3-space. <br>
 * This class contains fields and methods useful when dealing with vectors and points for the
 * purpose of StL files.
 * 
 * @author Nate Young
 */
public class Vec3 {
	public float x;
	public float y;
	public float z;
	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**Returns the r-value of the spherical coordinates of this vector (same as absolute value)
	 * 
	 * @return the r-value, or absolute value, of this vector
	 */
	public float r(){
		return (float)Math.sqrt(x*x+y*y+z*z);
	}
	/**Returns the phi value of the spherical coordinates of this vector
	 * 
	 * @return the phi of this vector
	 */
	public float phi(){
		return (float)Math.atan2(y,x);
	}
	/**Returns the theta value of the spherical coordinates of this vector
	 * 
	 * @return the theta of this vector
	 */
	public float theta(){
		return (float)Math.atan2(Math.sqrt(x*x+y*y),z);
	}
	/**Returns this vector raised to a power n. <br>
	 * This method uses the method defined for use in the mandelbulb
	 * @param n the power to raise this Vec3 to
	 * @return this vector raised to the nth power
	 */
	public Vec3 pow(float n){
		double rn = Math.pow(r(), n);
		float t = theta();
		float p = phi();
		float xn = (float) (rn*Math.sin(n*t)*Math.cos(n*p));
		float yn = (float) (rn*Math.sin(n*t)*Math.sin(n*p));
		float zn = (float) (rn*Math.cos(n*t));
		return new Vec3(xn,yn,zn);
	}
	/**Returns the sum of this vector and another given vector
	 * 
	 * @param o the other vector to add
	 * @return the vector sum of this vector and o.
	 */
	public Vec3 add(Vec3 o){
		return new Vec3(x+o.x,y+o.y,z+o.z);
	}
	/**Returns this vector scaled by (multiplied by) a given factor
	 * 
	 * @param s the factor by which to scale this vector
	 * @return this vector scaled by the scalar s
	 */
	public Vec3 scale(float s){
		return new Vec3(s*x,s*y,s*z);
	}
	/**Returns this vector's components in text form for use in ASCII StL files. <br>
	 * Text returned is of the form <code>x y z</code>
	 * 
	 * @return the components of this vector in text form
	 */
	public String getNums(){
		return x+" "+y+" "+z;
	}
	/**Returns a byte array representation of this vector for use in binary StL files.
	 * The length of the array returned will always be 12.
	 * 
	 * @return This vector's components represented as an array of bytes
	 */
	public byte[] getBytes(){
		byte[] a = new byte[12];
		System.arraycopy(ftb(x), 0, a, 0, 4);
		System.arraycopy(ftb(y), 0, a, 4, 4);
		System.arraycopy(ftb(z), 0, a, 8, 4);
		return a;
	}
	private byte[] ftb(float value){
		return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array();
	}
}
