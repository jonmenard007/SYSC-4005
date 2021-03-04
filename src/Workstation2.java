public class Workstation2 extends Workstation {

	public boolean hasC2;
	
	public Workstation2(int id, String[] fileNames) {
		super(id, fileNames);
		hasC1 = false;
		
	}
	
	@Override
	public void getComponent() {
		for(int i = 0; i < buffers.size(); i++) {
			Buffer buffer = buffers.get(i);
			if(buffer.isComponentAvailable(components.C1) && !hasC1){
				if(buffer.getComponent() == components.C1) {
					System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C1");
					hasC1 = true;
				}
			}else if(buffer.isComponentAvailable(components.C2) && !hasC2){
				if(buffer.getComponent() == components.C2) {
					System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C2");
					hasC2 = true;
				}
			}
			
		}
		makeComponent();
	}
	
	@Override
	public void makeComponent() {
		if(hasC1 && hasC2) {
			hasC1 = false;
			hasC2 = false;
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
