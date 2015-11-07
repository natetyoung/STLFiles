package src;

public class Facet {
	public Vec3 normal;
	public Vec3 v1;
	public Vec3 v2;
	public Vec3 v3;
	public Facet(Vec3 normal){
		this.normal = normal;
	}
	public Facet(Vec3 v1, Vec3 v2, Vec3 v3){
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		float x = (v2.y-v1.y)*(v3.z-v2.z)-(v2.z-v1.z)*(v3.y-v2.y);
		float y = (v2.z-v1.z)*(v3.x-v2.x)-(v2.x-v1.x)*(v3.z-v2.z);
		float z = (v2.x-v1.x)*(v3.y-v2.y)-(v2.y-v1.y)*(v3.x-v2.x);
		this.normal = new Vec3(x,y,z);
	}

	public String stlString(){
		String str = "facet normal "+normal.getNums()+" \n";
		str = str+"outer loop \n";
		str = str+"vertex "+v1.getNums()+" \n";
		str = str+"vertex "+v2.getNums()+" \n";
		str = str+"vertex "+v3.getNums()+" \n";
		str = str+"endloop \nendfacet \n";
		return str;
	}
	
	public byte[] stlBytes(){
		byte[] a = new byte[50];
		System.arraycopy(normal.getBytes(), 0, a, 0, 12);
		System.arraycopy(v1.getBytes(), 0, a, 12, 12);
		System.arraycopy(v2.getBytes(), 0, a, 24, 12);
		System.arraycopy(v3.getBytes(), 0, a, 36, 12);
		return a;
	}
}
