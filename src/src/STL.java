package src;

import java.io.IOException;

public interface STL {
	public void addFacet(Facet f) throws IOException;
	public void finish() throws IOException;
}
