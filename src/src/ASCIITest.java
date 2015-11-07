package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ASCIITest {
	public static void main(String[] args) throws Exception {
//		File path = new File("");
//		path.mkdirs();
//		File file = new File(path,"a.stl");
		File file = new File("C:\\Users\\Debb\\Desktop\\a.stl");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.append("solid tetrahedron \n");
		Vec3 a = new Vec3(0,1,0);
		Vec3 b = new Vec3(1,0,1);
		Vec3 c = new Vec3(1,0,-1);
		Vec3 d = new Vec3(-2,0,0);
		Facet[] fs = {new Facet(a,b,c),new Facet(d,b,c),new Facet(a,b,d),new Facet(a,c,d)};
		for(Facet f: fs){
			writer.append(f.stlString());
		}
		writer.append("endsolid tetrahedron");
		writer.close();
	}
}
