import java.util.ArrayList;

public class Inspector extends SimulationObject {

private int id;
public Enum state;
public ArrayList<Buffer> buffers;
public Enum currentComponent;


Inspector (int id, String[] fileNames){
	super(fileNames);
	this.id = id;
	this.buffers = new ArrayList<Buffer>();
}

public void Inspect() {
	
}

public void addBuffer(Buffer buffer) {
	this.buffers.add(buffer);
}

public void getComponent() {
	
}

public void giveBufferComponent() {
	
}

	
	
}
