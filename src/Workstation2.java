
/**
 * The Class Workstation2.
 */
public class Workstation2 extends Workstation {

	/** The has C 2 Component. */
	public boolean hasC2;
	
	/**
	 * Instantiates a new workstation 2.
	 *
	 * @param id the id
	 * @param fileName the file name
	 */
	public Workstation2(int id, String fileName , float rMean) {
		super(id, fileName , rMean);
		hasC1 = false;
		
	}
	
	/**
	 * Gets a component from the workstations buffers.
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
			}else if(buffer.isComponentAvailable(components.C2) && !hasC2){
				if(buffer.getComponent() == components.C2) {
				//	System.out.println("WorkStation " + id + " got from Buffer " + buffer.id + " component C2");
					hasC2 = true;
				}
			}
			
		}
		makeComponent();
	}
	
	/**
	 * If it has the right components it makes the object.
	 */
	@Override
	public void makeComponent() {
		if(hasC1 && hasC2) {
			hasC1 = false;
			hasC2 = false;
			super.count++;
			workingTimeRemaning = super.getRandomNumber();
			state = states.WORKING;
		}else { 
			state = states.BLOCKED;
		}
	}

}
