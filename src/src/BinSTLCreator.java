package src;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinSTLCreator implements STL{
	private BufferedOutputStream stream;
	public BinSTLCreator(File file, int numFacets) throws IOException {
		stream = new BufferedOutputStream(new FileOutputStream(file));
		stream.write(new byte[80]);
		stream.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(numFacets).array());
	}
	public BinSTLCreator(String filename, int numFacets) throws IOException{
		this(new File(filename),numFacets);
	}
	
	public void addFacet(Facet f) throws IOException{
		stream.write(f.stlBytes());
	}
	
	public void finish() throws IOException{
		stream.close();
	}
}
