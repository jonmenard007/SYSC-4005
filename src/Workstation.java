import java.util.ArrayList;
public class Workstation extends SimulationObject{
	
	public static enum states {BLOCKED,WORKING,DONE};
	public int id;
	public Enum state;
	public ArrayList<Buffer> buffers;
	public boolean hasC1;
	public float workingTimeRemaning;


	public Workstation (int id, String[] fileNames){
		super(fileNames);
		this.id = id;
		this.buffers = new ArrayList<Buffer>();
		this.workingTimeRemaning = 0;
		this.state = states.BLOCKED;
		hasC1 = false;
	}
	
	public void getComponent() {
		for(int i = 0; i < buffers.size(); i++) {
			Buffer buffer = buffers.get(i);
			if(buffer.isComponentAvailable(components.C1) && !hasC1){
				if(buffer.getComponent() == components.C1) {
					System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C1");
					hasC1 = true;
					break;
				}
			}
		}
		makeComponent();
	}
	
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
	
	public void addBuffer(Buffer buffer) {
		this.buffers.add(buffer);
	}
	
	public void makeComponent() {
		if(hasC1) {
			hasC1 = false;
			int index;
			index = super.valueIndexs.get(0);
			if(index >= super.fileValues.get(0).size()) {
				state = states.DONE;
				return;
			}
			workingTimeRemaning = super.fileValues.get(0).get(index);
			valueIndexs.set(0, (index + 1));
			state = states.WORKING;
		}else { 
			state = states.BLOCKED;
		}
	}

	
	
}
