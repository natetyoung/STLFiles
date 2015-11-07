package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class STLCreator implements STL{
	private BufferedWriter writer;
	private String name;
	public STLCreator(File file, String name) throws IOException {
		writer = new BufferedWriter(new FileWriter(file));
		writer.append("solid "+name+" \n");
		this.name = name;
	}
	public STLCreator(String filename, String name) throws IOException{
		this(new File(filename),name);
	}
	public void addFacet(Facet f) throws IOException{
		writer.append(f.stlString());
	}
	public void addFacet(Vec3 v1, Vec3 v2, Vec3 v3) throws IOException{
		addFacet(new Facet(v1,v2,v3));
	}
	public void finish() throws IOException{
		writer.append("endsolid "+name);
		writer.close();
	}
}
