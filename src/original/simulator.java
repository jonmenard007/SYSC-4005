package original;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

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
        
       
        

        Workstation workstation1 = new Workstation(1,"Workstation1",0.217182777f);
        Workstation2 workstation2 = new Workstation2(2,"Workstation2",0.090150136f);
        Workstation3 workstation3 = new Workstation3(3,"Workstation3",0.113693469f);

        Inspector2 inspector2 = new Inspector2(2,"Inspector2C2",0.06436289f);
        Inspector1 inspector1 = new Inspector1(1, "Inspector1",0.096544573f);
        inspectors.add(inspector1);
        inspectors.add(inspector2);
        
        
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
			writer.write("Current Time,Inspector 1,Inspector 2,Buffer 1,Buffer 2,Buffer 3,Buffer 4,Buffer 5,Workstation 1,W1-C1,Workstation 2,W2-C1,W2-C2,Workstation 3,W3-C1,W3-C3,,,,,," + workstation1.count + "," + workstation2.count + "," + workstation3.count + ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n");
			//writer.write("Current Time,Inspector 1,Inspector 2,Buffer 1,Buffer 2,Buffer 3,Buffer 4,Buffer 5,Workstation 1,W1-C1,Workstation 2,W2-C1,W2-C2,Workstation 3,W3-C1,W3-C3,,,,,," + workstation1.count + "," + workstation2.count + "," + workstation3.count + "\n");
			//writer.write("Current Time,Inspector 1,Inspector 2,Buffer 1,Buffer 2,Buffer 3,Buffer 4,Buffer 5,Workstation 1,W1-C1,Workstation 2,W2-C1,W2-C2,Workstation 3,W3-C1,W3-C3,,,,,," + workstation1.count + "," + workstation2.count + "," + workstation3.count + "\n");
			
			writer.write(0 +","+Inspector1LastState+","+Inspector2LastState+","+buffer1LastSize+","+buffer2LastSize+","+buffer3LastSize+","+buffer4LastSize+","+buffer5LastSize+","+Workstation1LastState+","+Workstation1LastC1+","+Workstation2LastState+","+Workstation2LastC1+","+Workstation2LastC2+","+Workstation3LastState+","+Workstation3LastC1+","+Workstation3LastC3 + "\n");
			boolean write = false;
			//7200 is equal to 2 hours 
			for(int i = 0; i < (7200 * 100); i++) {
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
			//writer.write("Current Time,Inspector 1,Inspector 2,Buffer 1,Buffer 2,Buffer 3,Buffer 4,Buffer 5,Workstation 1,W1-C1,Workstation 2,W2-C1,W2-C2,Workstation 3,W3-C1,W3-C3, \n");
			writer.close();	
			
			
			RandomAccessFile f = new RandomAccessFile(new File("resources/results/results.csv"), "rw");
			f.seek(0); // to the beginning
			String line = "Current Time,Inspector 1,Inspector 2,Buffer 1,Buffer 2,Buffer 3,Buffer 4,Buffer 5,Workstation 1,W1-C1,Workstation 2,W2-C1,W2-C2,Workstation 3,W3-C1,W3-C3,,,,,," + workstation1.count + "," + workstation2.count + "," + workstation3.count + "\n";
			// workstation1.count + "," + workstation2.count + "," + workstation3.count + "\n";
			f.write(line.getBytes());
			f.close();
			
			inspector1.close();
        	inspector2.close();
        	workstation1.close();
        	workstation2.close();
        	workstation3.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem Writing to results.csv");
		}
        
        final Charset CS = Charset.defaultCharset(); // e.g. UTF-8
        ArrayList<String> lines1 = (ArrayList<String>) Files.readAllLines(Paths.get("resources/results/Inspector1.csv"), CS),
                     lines2 = (ArrayList<String>) Files.readAllLines(Paths.get("resources/results/Inspector2C2.csv"), CS),
                     lines3 = (ArrayList<String>) Files.readAllLines(Paths.get("resources/results/Inspector2C3.csv"), CS),
                     lines4 = (ArrayList<String>) Files.readAllLines(Paths.get("resources/results/Workstation1.csv"), CS),
                     lines5 = (ArrayList<String>) Files.readAllLines(Paths.get("resources/results/Workstation2.csv"), CS),
                     lines6 = (ArrayList<String>) Files.readAllLines(Paths.get("resources/results/Workstation3.csv"), CS);
        // MERGE until end of either list, then APPEND until end of lines2
        int maxSize = lines1.size();
        if(maxSize < lines2.size()) maxSize = lines2.size();
        if(maxSize < lines3.size()) maxSize = lines3.size();
        if(maxSize < lines4.size()) maxSize = lines4.size();
        if(maxSize < lines5.size()) maxSize = lines5.size();
        if(maxSize < lines6.size()) maxSize = lines6.size();
        
        
        FileWriter writer = new FileWriter("resources/results/data.csv");
        writer.write("Inspector 1, Inspector 2C2, Inspector 2C3, Workstation 1,Workstation 2,Workstation 3 \n");
		
        for (int i = 0; i < maxSize; i++) {
             	String l1 = ",", l2 = ",",l3 = ",",l4 = ",",l5 = ",",l6 = "";
             	
             	if(lines1.size() > i) {
             		l1 = lines1.get(i);
             	}
             	if(lines2.size() > i) {
             		l2 = lines2.get(i);
             	}
             	if(lines3.size() > i) {
             		l3 = lines3.get(i);
             	}
             	if(lines4.size() > i) {
             		l4 = lines4.get(i);
             	}
             	if(lines5.size() > i) {
             		l5 = lines5.get(i);
             	}
             	if(lines6.size() > i) {
             		l6 = lines6.get(i);
             	}
                writer.write(l1 +  l2 +  l3 +  l4 +  l5 + l6 + "\n");
            
		}
        writer.close();
        System.out.println("Original Simulator Done Executing");
    
    }
}
