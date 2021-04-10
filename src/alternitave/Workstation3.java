package alternitave;


/**
 * The Class Workstation3.
 */
public class Workstation3 extends Workstation{

	
	/** The has C 3. */
	public boolean hasC3;
	
	/**
	 * Instantiates a new workstation 3.
	 *
	 * @param id the id
	 * @param fileName the file name
	 */
	public Workstation3(int id, String fileName, float rMean) {
		super(id, fileName,  rMean);
		hasC3 = false;
		
	}
	
	
	/**
	 * Gets the required components from the buffers.
	 *
	 * @return the component
	 */
	@Override
	public void getComponent() {
		for(int i = 0; i < buffers.size(); i++) {
			Buffer buffer = buffers.get(i);
			if(buffer.isComponentAvailable(components.C1) && !hasC1){
				if(buffer.getComponent() == components.C1) {
				//	System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C1");
					hasC1 = true;
				}
			}else if(buffer.isComponentAvailable(components.C3) && !hasC3){
				if(buffer.getComponent() == components.C3) {
				//	System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C3");
					hasC3 = true;
				}
			}
			
		}
		makeComponent();
	}
	
	/**
	 * If it has the required components it makes them.
	 */
	@Override
	public void makeComponent() {
		if(hasC1 && hasC3) {
			hasC1 = false;
			hasC3 = false;
			count++;
			workingTimeRemaning = super.getRandomNumber();
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
