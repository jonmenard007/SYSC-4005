import java.math.BigInteger;
import java.util.Random;


/**
 * The Class Inspector2.
 */
public class Inspector2 extends Inspector {
	
	/** The random number 2. */
	public Random randomNumber2;
	
	/** The seed 2. */
	public BigInteger seed2;
	
	
	/**
	 * Instantiates a new inspector 2.
	 *
	 * @param id the id
	 * @param fileName the file name
	 */
	public Inspector2( int id, String fileName) {
		super(id, fileName);
		
		Random random = new Random();
		seed2 = new BigInteger(48, random);
	    while(seed2.equals(BigInteger.ZERO)) {
	    	seed2 = new BigInteger(48, random); 
	    }
		this.randomNumber2 = new Random(seed2.longValue());
		getComponent();
	}
	
	/**
	 * Gets the next component, and how long it will take to inspect it.
	 *
	 * @return the component
	 */
	@Override
	public void getComponent() {
		double componentRV = this.randomNumber2.nextDouble();
		int index;
		if(componentRV < 0.50) {
			currentComponent = components.C2;
			inspectionTimeRemaining  = super.getRandomNumber();
		}else {
			currentComponent = components.C3;
			inspectionTimeRemaining  = super.getRandomNumber();
			
		}
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
