import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


enum components {C1,C2,C3};

/**
 * The Class simulator.
 */
public class simulator {
	

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Enum Inspector1LastState;
		Enum Inspector2LastState;
		
		int buffer1LastSize;
		int buffer2LastSize;
		int buffer3LastSize;
		int buffer4LastSize;
		int buffer5LastSize;
		
		
		Enum Workstation1LastState;
		boolean Workstation1LastC1;
		Enum Workstation2LastState;
		boolean Workstation2LastC1;
		boolean Workstation2LastC2;
		Enum Workstation3LastState;
		boolean Workstation3LastC1;
		boolean Workstation3LastC3;
		
		
		
        ArrayList<Inspector> inspectors = new ArrayList<Inspector>();
        
        Inspector1 inspector1 = new Inspector1(1, "Inspector1");
        Inspector2 inspector2 = new Inspector2(2,"Inspector2");
        inspectors.add(inspector1);
        inspectors.add(inspector2);

        Workstation workstation1 = new Workstation(1,"Workstation1");
        Workstation2 workstation2 = new Workstation2(2,"Workstation2");
        Workstation3 workstation3 = new Workstation3(3,"Workstation3");

        Buffer buffer1 = new Buffer(1, components.C1);
        Buffer buffer2 = new Buffer(2, components.C1);
        Buffer buffer3 = new Buffer(3, components.C2);
        Buffer buffer4 = new Buffer(4, components.C1);
        Buffer buffer5 = new Buffer(5, components.C3);

        inspector1.addBuffer(buffer1);
        inspector1.addBuffer(buffer2);
        inspector1.addBuffer(buffer4);

        inspector2.addBuffer(buffer3);
        inspector2.addBuffer(buffer5);
        
        workstation1.addBuffer(buffer1);
        
        workstation2.addBuffer(buffer2);
        workstation2.addBuffer(buffer3);
        
        workstation3.addBuffer(buffer4);
        workstation3.addBuffer(buffer5);
        
        
      	Inspector1LastState = inspector1.state;
		Inspector2LastState = inspector2.state;
		
		buffer1LastSize = buffer1.bufferSize();
		buffer2LastSize = buffer2.bufferSize();
		buffer3LastSize = buffer3.bufferSize();
		buffer4LastSize = buffer4.bufferSize();
		buffer5LastSize = buffer5.bufferSize();
		
		
		Workstation1LastState = workstation1.state;
		Workstation1LastC1 =workstation1.hasC1;
		Workstation2LastState = workstation2.state;
		Workstation2LastC1 =workstation2.hasC1;
		Workstation2LastC2 =workstation2.hasC2;
		Workstation3LastState = workstation3.state;
		Workstation3LastC1 =workstation3.hasC1;
		Workstation3LastC3 =workstation3.hasC3;
        
        
        try {
			FileWriter writer = new FileWriter("resources/results/results.csv");
			writer.write("Current Time,Inspector 1,Inspector 2,Buffer 1,Buffer 2,Buffer 3,Buffer 4,Buffer 5,Workstation 1,W1-C1,Workstation 2,W2-C1,W2-C2,Workstation 3,W3-C1,W3-C3 \n");
			writer.write(0 +","+Inspector1LastState+","+Inspector2LastState+","+buffer1LastSize+","+buffer2LastSize+","+buffer3LastSize+","+buffer4LastSize+","+buffer5LastSize+","+Workstation1LastState+","+Workstation1LastC1+","+Workstation2LastState+","+Workstation2LastC1+","+Workstation2LastC2+","+Workstation3LastState+","+Workstation3LastC1+","+Workstation3LastC3 + "\n");
			boolean write = false;
			for(int i = 0; i < 100000; i++) {
				inspector1.processNextEvent();
	        	inspector2.processNextEvent();
	        	workstation1.processNextEvent();
	        	workstation2.processNextEvent();
	        	workstation3.processNextEvent();
	        	
				if(
						
						
			        	Inspector1LastState != inspector1.state ||
			    		Inspector2LastState != inspector2.state ||
			    		buffer1LastSize != buffer1.bufferSize() ||
			    		buffer2LastSize != buffer2.bufferSize() ||
			    		buffer3LastSize != buffer3.bufferSize() ||
			    		buffer4LastSize != buffer4.bufferSize() ||
			    		buffer5LastSize != buffer5.bufferSize() ||
			    		Workstation1LastState != workstation1.state ||
			    		Workstation1LastC1 !=workstation1.hasC1 ||
			    		Workstation2LastState != workstation2.state ||
			    		Workstation2LastC1 !=workstation2.hasC1 ||
			    		Workstation2LastC2 !=workstation2.hasC2 ||
			    		Workstation3LastState != workstation3.state||
			    		Workstation3LastC1 !=workstation3.hasC1 ||
			    		Workstation3LastC3 !=workstation3.hasC3
				) {
					write = true;
				}
				Inspector1LastState = inspector1.state;
	    		Inspector2LastState = inspector2.state;
	    		
	    		buffer1LastSize = buffer1.bufferSize();
	    		buffer2LastSize = buffer2.bufferSize();
	    		buffer3LastSize = buffer3.bufferSize();
	    		buffer4LastSize = buffer4.bufferSize();
	    		buffer5LastSize = buffer5.bufferSize();
	    		
	    		
	    		Workstation1LastState = workstation1.state;
	    		Workstation1LastC1 =workstation1.hasC1;
	    		Workstation2LastState = workstation2.state;
	    		Workstation2LastC1 =workstation2.hasC1;
	    		Workstation2LastC2 =workstation2.hasC2;
	    		Workstation3LastState = workstation3.state;
	    		Workstation3LastC1 =workstation3.hasC1;
	    		Workstation3LastC3 =workstation3.hasC3;
	    		if(write) {
					writer.write((float) i/100 +","+Inspector1LastState+","+Inspector2LastState+","+buffer1LastSize+","+buffer2LastSize+","+buffer3LastSize+","+buffer4LastSize+","+buffer5LastSize+","+Workstation1LastState+","+Workstation1LastC1+","+Workstation2LastState+","+Workstation2LastC1+","+Workstation2LastC2+","+Workstation3LastState+","+Workstation3LastC1+","+Workstation3LastC3 + "\n");
					write = false;
	    		}
					
	        	
			}
			inspector1.close();
        	inspector2.close();
        	workstation1.close();
        	workstation2.close();
        	workstation3.close();
			writer.close();	
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem Writing to results.csv");
		}
        
        

       
    }
}
