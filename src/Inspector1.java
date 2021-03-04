public class Inspector1 extends Inspector{

	
	public Inspector1( int id, String[] fileNames) {
		super(id, fileNames);
	}
	
	@Override
	public void getComponent() {
		int index;
		currentComponent = components.C1;
		index = super.valueIndexs.get(0);
		if(index >= super.fileValues.get(0).size()) {
			state = states.DONE;
			return;
		}
		inspectionTimeRemaining = super.fileValues.get(0).get(index);
		valueIndexs.set(0, (index + 1));
		state = states.INSCPECTING;
	}

	@Override
	public void processNextEvent() {
		if(state == states.BLOCKED) {
			giveBufferComponent();			
		}else if (state == states.INSCPECTING) {
			inspectionTimeRemaining -= 1;
			if(inspectionTimeRemaining <= 0) {
				giveBufferComponent();
			}
		}else if(state == states.DONE) {
			return;
		}
		
	}
	
	
}
