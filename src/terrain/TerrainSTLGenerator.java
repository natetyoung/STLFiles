package terrain;

import java.io.File;
import java.io.IOException;

import src.*;

public class TerrainSTLGenerator {

	private float xmin;
	private float xmax;
	private float zmin;
	private float zmax;
	private float[][] heights;
	
	public TerrainSTLGenerator(float xmin, float xmax, float zmin, float zmax,
			float[][] heights) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.zmin = zmin;
		this.zmax = zmax;
		this.heights = heights;
	}
	
	public void makeSTL(File file, String name) throws IOException{
		STLCreator sc = new STLCreator(file,name);
		fill(sc);
		sc.finish();
	}
	public void makeSTL(String filename, String name) throws IOException{
		makeSTL(new File(filename),name);
	}
	public void makeBin(File file) throws IOException{
		BinSTLCreator sc = new BinSTLCreator(file,heights.length*heights[0].length*2);
		fill(sc);
		sc.finish();
	}
	public void makeBin(String filename) throws IOException{
		makeBin(new File(filename));
	}
	
	private void fill(STL s) throws IOException{
		Vec3[][] arr = new Vec3[heights.length][heights[0].length];
		for(int i=0; i<heights.length; i++)
			for(int j=0; j<heights[i].length; j++)
				arr[i][j] = new Vec3(i*(xmax-xmin)/heights.length+xmin,heights[i][j],
						j*(zmax-zmin)/heights[0].length+zmin);
		for(int i=0; i<arr.length-1; i++){
			for(int j=0; j<arr[i].length-1; j++){
				s.addFacet(new Facet(arr[i][j],arr[i+1][j],arr[i+1][j+1]));
				s.addFacet(new Facet(arr[i][j],arr[i][j+1],arr[i+1][j+1]));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		float[][] heights = {{0,0,0,1,1,0,0,0,0},
		                     {0,0,0,1,1,0,0,0,0},
		                     {0,0,0,1,1,1,1,1,0},
		                     {1,1,1,1,1,1,1,1,1},
		                     {1,1,1,1,1,1,1,1,1},
		                     {1,1,1,1,1,1,1,1,1},
		                     {1,1,1,1,1,1,1,1,1},
		                     {1,1,1,1,1,1,1,1,0}};
		TerrainSTLGenerator tsg = new TerrainSTLGenerator(-2,2,-2,2,heights);
		tsg.makeSTL("C:\\Users\\Nate\\Desktop2\\like.stl","like");
	}
}
