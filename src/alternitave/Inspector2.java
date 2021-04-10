package alternitave;
import java.io.FileWriter;
import java.io.IOException;
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
	public FileWriter writer;
	
	
	/**
	 * Instantiates a new inspector 2.
	 *
	 * @param id the id
	 * @param fileName the file name
	 */
	public Inspector2( int id, String fileName, float rMean) {
		super(id, fileName,  rMean);
		
		Random random = new Random();
		seed2 = new BigInteger(48, random);
	    while(seed2.equals(BigInteger.ZERO)) {
	    	seed2 = new BigInteger(48, random); 
	    }
		this.randomNumber2 = new Random(seed2.longValue());
		try {
			writer = new FileWriter("resources/results/inspector2C3.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getComponent();
	}
	
	/**
	 * Gets the next component, and how long it will take to inspect it.
	 *
	 * @return the component
	 */
	@Override
	public void getComponent() {
		double componentRV = Math.random();
		//System.out.println("This is the r Mean" + componentRV);
		int index;
		if(componentRV < 0.50) {
			currentComponent = components.C2;
			inspectionTimeRemaining  = getRandomNumber();
		}else {
			currentComponent = components.C3;
			inspectionTimeRemaining  = getRandomNumber2();
			
		}
		state = states.INSPECTING;
		
	}
	
	/**
	 * Close the file.
	 */
	@Override
	public void close() {
		super.close();
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public float getRandomNumber2() {
		float f = this.randomNumber2.nextFloat() ;
		f = (float) ((-1 / 0.048466621) * (Math.log(f)));
		
		try {
			
		//	System.out.println(f);
	    	writer.write(Float.toString(f) + ",\n");
	    
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem Writing to results.csv");
		}
		
		return f * 100;// times 100 to make each iteration 1 100th of a second ;
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
	
	
}
