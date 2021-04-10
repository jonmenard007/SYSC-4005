package original;
import java.util.ArrayList;


/**
 * The Class Workstation.
 */
public class Workstation extends SimulationObject{
	
	/**
	 * The Enum states.
	 */
	public static enum states {/** The blocked. */
BLOCKED,/** The working. */
WORKING,/** The done. */
DONE};
	
	/** The id. */
	public int id;
	
	/** The state. */
	public Enum state;
	
	/** The buffers. */
	public ArrayList<Buffer> buffers;
	
	/** The has C 1. */
	public boolean hasC1;
	
	/** The working time remaning. */
	public float workingTimeRemaning;
	
	public int count = 0;


	/**
	 * Instantiates a new workstation.
	 *
	 * @param id the id
	 * @param fileName the file name
	 */
	public Workstation (int id, String fileName, float rMean){
		super(fileName,  rMean);
		this.id = id;
		this.buffers = new ArrayList<Buffer>();
		this.workingTimeRemaning = 0;
		this.state = states.BLOCKED;
		hasC1 = false;
	}
	
	/**
	 * Gets a component from a buffer.
	 *
	 * @return the component
	 */
	public void getComponent() {
		for(int i = 0; i < buffers.size(); i++) {
			Buffer buffer = buffers.get(i);
			if(buffer.isComponentAvailable(components.C1) && !hasC1){
				if(buffer.getComponent() == components.C1) {
					//System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C1");
					hasC1 = true;
					break;
				}
			}
		}
		makeComponent();
	}
	
	/**
	 * Process next event.
	 */
	public void processNextEvent() {
		if(state == states.BLOCKED) {
			getComponent();
		}else if (state == states.WORKING) {
			workingTimeRemaning -= 1;
			if(workingTimeRemaning <= 0) {
				getComponent();
			}
		}else if(state == states.DONE) {
			return;
		}
		
	}
	
	/**
	 * Adds a buffer to the list of buffers.
	 *
	 * @param buffer the buffer
	 */
	public void addBuffer(Buffer buffer) {
		this.buffers.add(buffer);
	}
	
	/**
	 * if it has the required component it makes the next object.
	 */
	public void makeComponent() {
		if(hasC1) {
			hasC1 = false;
			count++;
			workingTimeRemaning = super.getRandomNumber();
			state = states.WORKING;
		}else { 
			state = states.BLOCKED;
		}
	}

	
	
}
