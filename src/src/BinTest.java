package src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinTest {
	public static void main(String[] args) {
		/*byte[] data = new byte[134];
		data[83] = 1;
		//putfloat(data,)
		//THIS FILE IS NOT FINISHED BECAUSE BINARY STL FILES SEEM UNNECESSARILY DIFFICULT*/
		byte[] num = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(20000).array();
		for(byte b:num){
			System.out.println(b);
		}
	}
	
	public static byte[] putfloat(byte[] file, float num, int pos){
		byte[] rep = ByteBuffer.allocate(4).putFloat(num).array();
		System.arraycopy(rep,0,file,pos,4);
		return file;
	}
}
