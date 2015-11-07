package mandelbrot;

import java.io.IOException;

import src.*;

public class MandelbrotSTL {
	public static void main(String[] args) throws IOException {
		MQMath.MAX_ITER = 50;
		STLCreator sc = new STLCreator("C:\\Users\\Nate\\Desktop2\\mb.stl","mandelblock");
		Vec3[][] arr = new Vec3[100][100];
		for(double x = -2; x<=1.995; x+=.04){
			for(double y = -2; y<=1.995; y+=.04){
				double iter = MQMath.mIterations(new ComplexNum(x,y));
				float height = 0;
				if(iter>0)
					height = (float) (Math.log(iter)+1)/5;
				arr[(int)Math.round(((x+2)/.04))][(int)Math.round(((y+2)/.04))] = 
						new Vec3((float)x,height,(float)y);
			}
		}
		for(int i=0; i<arr.length; i++)
			for(int j=0; j<arr[i].length; j++)
				if(arr[i][j]==null) System.out.println(i+" "+j);
		for(int i=0; i<arr.length-1; i++)
			for(int j=0; j<arr[i].length-1; j++){
				sc.addFacet(new Facet(arr[i][j],arr[i+1][j+1],arr[i+1][j]));
				sc.addFacet(new Facet(arr[i][j],arr[i][j+1],arr[i+1][j+1]));
			}
		Vec3 c1 = new Vec3(-2,0,-2);
		Vec3 c2 = new Vec3(-2,0,2);
		Vec3 c3 = new Vec3(2,0,-2);
		Vec3 c4 = new Vec3(2,0,2);
		sc.addFacet(new Facet(c1, c4, c2));
		sc.addFacet(new Facet(c4, c1, c3));
		sc.finish();
		System.out.println("Done");
	}
}
