
public class Inspector2 extends Inspector {
	
	public Inspector2( int id, String[] fileNames) {
		super(id, fileNames);
	}
	
	@Override
	public void getComponent() {
		double componentRV = Math.random();
		int index;
		if(componentRV < 0.50) {
			currentComponent = components.C2;
			index = valueIndexs.get(0);
			if(index >= super.fileValues.get(0).size()) {
				state = states.DONE;
				return;
			}
			inspectionTimeRemaining = fileValues.get(0).get(index);
			valueIndexs.set(0, (index + 1));
		}else {
			currentComponent = components.C3;
			index = valueIndexs.get(1);
			
			if(index >= super.fileValues.get(1).size()) {
				state = states.DONE;
				return;
			}
			inspectionTimeRemaining = fileValues.get(1).get(index);
			valueIndexs.set(1, (index + 1));
		}
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
