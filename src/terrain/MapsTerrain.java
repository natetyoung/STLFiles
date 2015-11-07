package terrain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MapsTerrain {
	public static void main(String[] args) throws IOException {
		double[] startLocation = {0,0};
		double delta = .1;
		double[][][] locations = new double[8][8][2];
		for(int i=0; i<locations.length; i++){
			for(int j=0; j<locations[i].length; j++){
				locations[i][j][0] = startLocation[0]+i*delta;
				locations[i][j][1] = startLocation[1]+j*delta;
			}
		}
		URL url = new URL("https://maps.googleapis.com/maps/api/elevation/json?"
				+ "locations="+arrayify(locations)
				+ "&key="+"YOUR_KEY_HERE");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		float[][] heights = new float[8][8];
		int i=0,j=0,pos=0;
		String line = br.readLine();
		while(line!=null){
			System.out.println(line);
			pos = line.indexOf("elevation");
			if(pos>-1){
				heights[i][j] = Float.parseFloat(line.substring(pos+13,line.indexOf(",")))/300;
				i++;
				if(i==heights.length){i=0; j++;}
			}
			line = br.readLine();
		}
		TerrainSTLGenerator tsg = new TerrainSTLGenerator(-2,2,-2,2,heights);
		tsg.makeSTL("C:\\Users\\Nate\\Desktop2\\maps.stl", "maps");
	}
	
	public static String arrayify(double[][][] arr){
		String str = "";
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				str = str+arr[i][j][0]+","+arr[i][j][1]+"|";
			}
		}
		return str.substring(0,str.length()-1);
	}
}
