

/**
 * The Class Inspector1.
 */
public class Inspector1 extends Inspector{

	
	Workstation2 workstation2;
	Workstation3 workstation3;
	Inspector2 inspector2;
	Buffer workstation2Buffer;
	Buffer workstation3Buffer;
	
	
	
	/**
	 * Instantiates a new inspector 1.
	 *
	 * @param id the id
	 * @param fileName the file name
	 */
	public Inspector1( int id, String fileName, float rMean, Workstation2 w2, Workstation3 w3, Inspector2 insp2) {
		super(id, fileName,  rMean);
		this.workstation2 = w2;
		this.workstation3 = w3;
		this.inspector2 = insp2;
		
		getComponent();
	}
	
	/**
	 * Gets the component and how long it will take to inspect it.
	 *
	 * @return the component
	 */
	@Override
	public void getComponent() {
		currentComponent = components.C1;
		inspectionTimeRemaining  = super.getRandomNumber();
		state = states.INSPECTING;
	}

	/**
	 * Process next event.
	 */
	@Override
	public void processNextEvent() {
		if(state == states.BLOCKED) {
			giveBufferComponent();			
		}else if (state == states.INSPECTING) {
			inspectionTimeRemaining -= 1;
			if(inspectionTimeRemaining <= 0) {
				giveBufferComponent();
			}
		}else if(state == states.DONE) {
			return;
		}
		
	}
	
	public boolean checkInspector() {
		if(inspector2.currentComponent == components.C2) {
			if(this.workstation2.buffers.get(0).addComponent(this.currentComponent)) {
				return true;
			}
		}else {
			if(this.workstation3.buffers.get(0).addComponent(this.currentComponent)) {
				return true;
			}
		}
		
		if(this.buffers.get(0).addComponent(this.currentComponent)) {
			return true;
		}
		
		// set to blocked
		return false;
		
		
	}
	
	@Override
	public void giveBufferComponent() {
		
		
		boolean workstation2OtherComponent = workstation2.hasC2;
		boolean workstation3OtherComponent = workstation3.hasC3;
		boolean workstation2C1 = workstation2.hasC1;
		boolean workstation3C1 = workstation3.hasC1;
		boolean workstation1C1 = (this.buffers.get(0).bufferSize() > 0);
		
		boolean addedToBuffer = false;
		
		if(workstation2OtherComponent) {
			if(workstation3OtherComponent) {
				if(workstation2C1) {
					if(workstation3C1) {
						if(workstation1C1) {
							addedToBuffer = checkInspector();
						}else {
							if(this.buffers.get(0).addComponent(this.currentComponent)) {
								addedToBuffer = true;
								return;
							}
							
						}
					}else {
						if(this.workstation3.buffers.get(0).addComponent(this.currentComponent)) {
							addedToBuffer = true;
							return;
						}
					}
				}else {
					if(this.workstation2.buffers.get(0).addComponent(this.currentComponent)) {
						addedToBuffer = true;
						return;
					}
				}
			}else {
				if(workstation2C1) {
					if(workstation3C1) {
						if(workstation1C1) {
							addedToBuffer = checkInspector();
						}else {
							if(this.buffers.get(0).addComponent(this.currentComponent)) {
								addedToBuffer = true;
							}
						}
					}else {
						if(this.workstation3.buffers.get(0).addComponent(this.currentComponent)) {
							addedToBuffer = true;
						}
					}
				}else {
					if(this.workstation2.buffers.get(0).addComponent(this.currentComponent)) {
						addedToBuffer = true;
					}
				}
			}	
		}else {
			if(workstation3OtherComponent) {
				if(workstation3C1) {
					if(workstation2C1) {
						if(workstation1C1) {
							addedToBuffer = checkInspector();
						}else {
							if(this.buffers.get(0).addComponent(this.currentComponent)) {
								addedToBuffer = true;
							}
						}
					}else {
						if(this.workstation2.buffers.get(0).addComponent(this.currentComponent)) {
							addedToBuffer = true;
						}
					}
				}else {
					if(this.workstation3.buffers.get(0).addComponent(this.currentComponent)) {
						addedToBuffer = true;
					}
				}
			}else {
				addedToBuffer = checkInspector();
			}
		}
		
		if(this.buffers.get(0).addComponent(this.currentComponent)) {
			addedToBuffer = true;
		}
		
		if(addedToBuffer) {
			getComponent();
			state = states.INSPECTING;
			return;
		}else {
			state = states.BLOCKED;
		}

	}
	
	
}
