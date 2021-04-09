import java.util.ArrayList;



/**
 * The Class Inspector.
 */
public abstract class Inspector extends SimulationObject {

/**
 * The Enum states.
 */
public static enum states {/** The blocked. */
BLOCKED,/** The inscpecting. */
INSPECTING,/** The done. */
DONE};

/** The id. */
private int id;

/** The state. */
public Enum state;

/** The buffers. */
public ArrayList<Buffer> buffers;

/** The current component. */
public Enum currentComponent;

/** The inspection time remaining. */
public float inspectionTimeRemaining;


/**
 * Instantiates a new inspector.
 *
 * @param id the id
 * @param fileName; the filename of the file to save generated inputs to
 */
Inspector (int id, String fileName, float rMean){
	super(fileName, rMean);
	this.id = id;
	this.buffers = new ArrayList<Buffer>();
	this.inspectionTimeRemaining = 0;
	this.state = states.BLOCKED;
}


/**
 * Process next event.
 */
public abstract void processNextEvent();

/**
 * Inspect.
 */
public void Inspect() {
	
}


/**
 * Adds a buffer to list of buffers
 *
 * @param buffer the buffer
 */
public void addBuffer(Buffer buffer) {
	this.buffers.add(buffer);
}


/**
 * Gets the next component.
 *
 * @return the component
 */
public abstract void getComponent();

/**
 * Give a buffer a component.
 */
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
		//	System.out.println("Inspector " + id + " gave Buffer " + bufferToAddTo.id + " component" + currentComponent);
			getComponent();
			state = states.INSPECTING;
			return;
		}
	}
	state = states.BLOCKED;
}

	
	
}
