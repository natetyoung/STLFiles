package mandelbulb;

import java.io.IOException;
import java.util.ArrayList;

import src.BinSTLCreator;
import src.Facet;
import src.STLCreator;
import src.Vec3;

public class MandelbulbSTL {
	
	public static final int RES = 500;
	public static void main(String[] args) throws IOException {
		boolean[][][] a = new boolean[RES][RES][RES];
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a[i].length; j++){
				for(int k=0; k<a[i][j].length; k++){
					a[i][j][k] = BulbMath.isInBulb8(new Vec3(p(i),p(j),p(k)));
				}
			}
			System.out.println("Heavy Math "+(i+1)+"/"+RES);
		}
		System.out.println("Heavy Math Done");
		boolean[][][] b = new boolean[RES][RES][RES];
		for(int i=1; i<b.length-1; i++){
			for(int j=1; j<b[i].length-1; j++){
				for(int k=1; k<b[i][j].length-1; k++){
					b[i][j][k] = a[i][j][k] && !(a[i+1][j][k]&&a[i][j+1][k]&&a[i][j][k+1]
							&&a[i-1][j][k]&&a[i][j-1][k]&&a[i][j][k-1]);
				}
			}
		}
		int facets = 0;
		System.out.println("Culling done");
		BinSTLCreator sc = new BinSTLCreator("C:\\Users\\Nate\\Desktop2\\mandelbulb.stl",3822288);
		ArrayList<Vec3> v = new ArrayList<Vec3>(8);
		for(int i=0; i<b.length-1; i++){
			for(int j=0; j<b[i].length-1; j++){
				for(int k=0; k<b[i][j].length-1; k++){
					v.clear();
					for(int l=0; l<2; l++)
						for(int m=0; m<2; m++)
							for(int n=0; n<2; n++)
								if(b[i+l][j+m][k+n])
									v.add(new Vec3(p(i+l),p(j+m),p(k+n)));
					int s = v.size();
					for(int p=0; p<s; p++){
						sc.addFacet(new Facet(v.get(p%s),v.get((p+1)%s),v.get((p+2)%s)));
						facets++;
					}
				}
			}
		}
		System.out.println("Facets: "+facets);
		sc.finish();
		System.out.println("File done");
	}
	
	public static float p(int i){
		return i*4f/RES-2;
	}
}
