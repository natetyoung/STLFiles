package src;

import java.io.IOException;

public class Models {
	public static void main(String[] args) throws IOException {
		ceilingLight();
	}
	public static void ceilingLight() throws IOException{
		STLCreator sc = new STLCreator("C:\\Users\\Nate\\Desktop2\\light.stl","light");
		Vec3[] v = {
				new Vec3(0,0,0),
				new Vec3(0,0,1),
				new Vec3(3,0,1),
				new Vec3(3,0,0),
				new Vec3(.25f,-.5f,.25f),
				new Vec3(.25f,-.5f,.75f),
				new Vec3(2.75f,-.5f,.75f),
				new Vec3(2.75f,-.5f,.25f)
		};
		sc.addFacet(v[0],v[4],v[1]);
		sc.addFacet(v[1],v[4],v[5]);
		sc.addFacet(v[1],v[5],v[2]);
		sc.addFacet(v[2],v[5],v[6]);
		sc.addFacet(v[2],v[6],v[3]);
		sc.addFacet(v[3],v[6],v[7]);
		sc.addFacet(v[3],v[7],v[0]);
		sc.addFacet(v[0],v[7],v[4]);
		sc.addFacet(v[6],v[4],v[7]);
		sc.addFacet(v[4],v[6],v[5]);
		sc.finish();
	}
}
