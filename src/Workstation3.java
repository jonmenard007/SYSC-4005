public class Workstation3 extends Workstation{

	
	public boolean hasC3;
	
	public Workstation3(int id, String[] fileNames) {
		super(id, fileNames);
		hasC3 = false;
		
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
			}else if(buffer.isComponentAvailable(components.C3) && !hasC3){
				if(buffer.getComponent() == components.C3) {
					System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C3");
					hasC3 = true;
				}
			}
			
		}
		makeComponent();
	}
	
	@Override
	public void makeComponent() {
		if(hasC1 && hasC3) {
			hasC1 = false;
			hasC3 = false;
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
	
//	@Override
//	public void processNextEvent() {
//		if(state == states.BLOCKED) {
//			getComponent();
//		}else if (state == states.WORKING) {
//			workingTimeRemaning -= 1;
//			if(workingTimeRemaning <= 0) {
//				getComponent();
//			}
//		}else if(state == states.DONE) {
//			return;
//		}
//		
//	}

}
