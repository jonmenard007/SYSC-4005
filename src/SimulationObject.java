import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


/**
 * The Class SimulationObject.
 *
 * @author Jon
 */

public class SimulationObject {

	/** The filename. */
	public String filename;
	
	/** The file values. */
	public ArrayList<ArrayList<Float>> fileValues;
	
	/** The value indexs. */
	public ArrayList<Integer> valueIndexs;
	
	/** The random number. */
	public Random randomNumber;
	
	/** The seed. */
	public BigInteger seed;
	
	/** The writer. */
	FileWriter writer;
	
	
	/**
	 * Instantiates a new simulation object.
	 *
	 * @param  the filename of the file to save generated inputs to
	 */
	SimulationObject(String filename) {
		this.filename = filename;
		Random random = new Random();
		seed = new BigInteger(48, random);
	    while(seed.equals(BigInteger.ZERO)) {
	        seed = new BigInteger(48, random); 
	    }
		this.randomNumber = new Random(seed.longValue());
		
			try {
				writer = new FileWriter("resources/results/" + filename + ".csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	
	}
	
	
	/**
	 * Close the file.
	 */
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * Gets the next random number.
	 *
	 * @return the random number
	 */
	public float getRandomNumber() {
		float f = this.randomNumber.nextFloat() ;
		f = (float) ((-1 / 0.084483038) * (Math.log(f)));
		
		try {
			
			System.out.println(f);
	    	writer.write(Float.toString(f) + ",\n");
	    
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem Writing to results.csv");
		}
		
		return f;
	}
	


	
	// reads the list of file names and converts them to an arrayList of floats to be read
//	private void readFiles() {
//		fileValues = new ArrayList<ArrayList<Float>>();
//		valueIndexs = new ArrayList<Integer>();
//		for(int i = 0; i < filenames.length; i++) {
//			ArrayList<Float> values = new ArrayList<Float>();
//			String filename = filenames[i];
//			BufferedReader reader;
//			//System.out.println(filename + "\n");
//			try {
//				reader = new BufferedReader(new FileReader(filename));
//				String line = reader.readLine();
//				if(line != null) {
//					while(line != null) {
//						if(!line.equals("")) {
//							//System.out.println(Float.parseFloat(line));
//							values.add(Float.parseFloat(line) * 100);
//						}
//						line = reader.readLine();
//					}
//					fileValues.add(values);
//					valueIndexs.add(0);
//				}
//			} catch (IOException e){
//				e.printStackTrace();
//				System.out.println("Problems Trying to read in Simulation Object");
//			}
//		}
//	}	
}
