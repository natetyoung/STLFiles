package mandelbrot;

public abstract class MQMath {
	public static int MAX_ITER;
	/*public static ComplexNum c1(ComplexNum zn, ComplexNum zn2){
		ComplexNum zns = zn.square();
		ComplexNum a = new ComplexNum(-2*zns.x-1,-2*zns.y).add(s(zn,zn2));
		return new ComplexNum(a.x/2,a.y/2);
	}
	public static ComplexNum s(ComplexNum zn, ComplexNum zn2){
		ComplexNum zns = zn.square();
		ComplexNum a = zn.square().sub(zn2);
		//NOPE
		return a;
	}*/
	public static ComplexNum nextZ(ComplexNum z, ComplexNum c){
		return z.square().add(c);
	}
	public static int mIterations(ComplexNum c){
		int i=0;
		ComplexNum z = new ComplexNum(0,0);
		while(i<MAX_ITER){
			z = nextZ(z,c);
			if(z.r()>2)
				return i;
			i++;
		}
		return i;
	}
	public static double rnmIterations(ComplexNum c){
		int i=0;
		ComplexNum z = new ComplexNum(0,0);
		while(i<MAX_ITER&&z.r()<2){
			z = nextZ(z,c);
			i++;
		}
		if(i>=MAX_ITER-5) return i;
		return i+1-Math.log(Math.log(z.abs()))/Math.log(2);
	}
}
