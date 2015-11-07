package mandelbulb;

import src.Vec3;

public class BulbMath {
	public static int MAX_ITER=30;
	public static Vec3 nextV8(Vec3 v, Vec3 c){
		return v.pow(8).add(c);
	}
	
	public static boolean isInBulb8(Vec3 c){
		Vec3 v = new Vec3(0,0,0);
		int i=0;
		while(i<MAX_ITER){
			v = nextV8(v, c);
			if(v.r()>=2) return false;
			i++;
		}
		return true;
	}
}
