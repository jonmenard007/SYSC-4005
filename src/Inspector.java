import java.util.ArrayList;

public abstract class Inspector extends SimulationObject {

public static enum states {BLOCKED,INSCPECTING,DONE};
private int id;
public Enum state;
public ArrayList<Buffer> buffers;
public Enum currentComponent;
public float inspectionTimeRemaining;


Inspector (int id, String[] fileNames){
	super(fileNames);
	this.id = id;
	this.buffers = new ArrayList<Buffer>();
	this.inspectionTimeRemaining = 0;
	this.state = states.BLOCKED;
	getComponent();
}

public abstract void processNextEvent();

public void Inspect() {
	
}

public void addBuffer(Buffer buffer) {
	this.buffers.add(buffer);
}


public abstract void getComponent();

public void giveBufferComponent() {
	Buffer bufferToAddTo = null;
	int maxSize = 3;
	for(int i = 0; i < buffers.size(); i++) {
		Buffer buffer = buffers.get(i);
		if(buffer.bufferSize() < maxSize && !buffer.isBufferFull() && buffer.componentType == currentComponent) {
			bufferToAddTo = buffer;
			maxSize = buffer.bufferSize();
		}
	}
	if(bufferToAddTo != null) {
		if(bufferToAddTo.addComponent(currentComponent)) {
			System.out.println("Inspector " + id + " gave Buffer " + bufferToAddTo.id + " component" + currentComponent);
			getComponent();
			state = states.INSCPECTING;
			return;
		}
	}
	state = states.BLOCKED;
}

	
	
}
