package original;

/**
 * The Class Inspector1.
 */
public class Inspector1 extends Inspector{

	
	/**
	 * Instantiates a new inspector 1.
	 *
	 * @param id the id
	 * @param fileName the file name
	 */
	public Inspector1( int id, String fileName, float rMean) {
		super(id, fileName,  rMean);
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
		state = states.INSCPECTING;
	}

	/**
	 * Process next event.
	 */
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
