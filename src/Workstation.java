import java.util.ArrayList;

public class Workstation extends SimulationObject{

	private int id;
	public Enum state;
	public ArrayList<Buffer> buffers;
	public boolean hasC1;


	public Workstation (int id, String[] fileNames){
		super(fileNames);
		this.id = id;
		this.buffers = new ArrayList<Buffer>();
	}
	
	public void getComponent() {
		
	}
	
	public void addBuffer(Buffer buffer) {
		this.buffers.add(buffer);
	}

	
	
}
