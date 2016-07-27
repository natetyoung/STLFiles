package mandelbrot;

import java.io.IOException;

import src.BinSTLCreator;
import src.Facet;
import src.STLCreator;
import src.Vec3;

public class MandelbrotSTL {
	public static void main(String[] args) throws IOException {
		MQMath.MAX_ITER = 100;
		double[] xr = {-.035,0};
		double[] yr = {.70,.73};
		double scale = 300/(xr[1]-xr[0]);
		int xd = 300;
		int yd = (int)(scale*(yr[1]-yr[0]));
		double xi = (xr[1]-xr[0])/xd;
		double yi = (yr[1]-yr[0])/yd;
		STLCreator sc = new STLCreator("mandelbrot.stl","mandelbrot");
		Vec3[][] arr = new Vec3[xd][yd];
		for(double x = xr[0]; x<xr[1]-xi/2; x+=xi){
			for(double y = yr[0]; y<yr[1]-yi/2; y+=yi){
				double iter = MQMath.mIterations(new ComplexNum(x,y));
				float height = 0;
				if(iter>0)
					height = (float) (Math.log(Math.log(iter))+1)/3;
				int[] coords = {(int)Math.round(((x-xr[0])/xi)),(int)Math.round(((y-yr[0])/yi))};
				arr[coords[0]][coords[1]] = 
						new Vec3(coords[0]/100f,height,coords[1]/100f);
			}
		}
		for(int i=0; i<arr.length; i++)
			for(int j=0; j<arr[i].length; j++)
				if(arr[i][j]==null) System.out.println(i+" "+j);
		for(int i=0; i<arr.length-1; i++)
			for(int j=0; j<arr[i].length-1; j++){
				if(arr[i][j]!=null&&arr[i+1][j+1]!=null){
					if(arr[i+1][j]!=null){
						sc.addFacet(new Facet(arr[i][j],arr[i+1][j],arr[i+1][j+1]));
					}
					if(arr[i][j+1]!=null){
						sc.addFacet(new Facet(arr[i][j],arr[i][j+1],arr[i+1][j+1]));
					}
				}
			}
		sc.finish();
		System.out.println("Done");
	}
}
