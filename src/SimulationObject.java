import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationObject {

	public String[] filenames;
	public ArrayList<ArrayList<Float>> fileValues;
	public ArrayList<Integer> valueIndexs;
	
	
	SimulationObject(String[] filenames) {
		this.filenames = filenames;
		readFiles();
	}

	
	// reads the list of file names and converts them to an arrayList of floats to be read
	private void readFiles() {
		fileValues = new ArrayList<ArrayList<Float>>();
		valueIndexs = new ArrayList<Integer>();
		for(int i = 0; i < filenames.length; i++) {
			ArrayList<Float> values = new ArrayList<Float>();
			String filename = filenames[i];
			BufferedReader reader;
			//System.out.println(filename + "\n");
			try {
				reader = new BufferedReader(new FileReader(filename));
				String line = reader.readLine();
				if(line != null) {
					while(line != null) {
						if(!line.equals("")) {
							//System.out.println(Float.parseFloat(line));
							values.add(Float.parseFloat(line) * 100);
						}
						line = reader.readLine();
					}
					fileValues.add(values);
					valueIndexs.add(0);
				}
			} catch (IOException e){
				e.printStackTrace();
				System.out.println("Problems Trying to read in Simulation Object");
			}
		}
	}	
}
