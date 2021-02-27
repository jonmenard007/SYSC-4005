import java.util.ArrayList;

enum components {C1,C2,C3};

public class simulator {
	

	
	public static void main(String[] args) {

		
		
		
        ArrayList<Inspector> inspectors = new ArrayList<Inspector>();
        
        Inspector inspector1 = new Inspector(1, new String[] {"resources/simulationTiming/servinspec1.dat"});
        Inspector inspector2 = new Inspector(2,new String[] {"resources/simulationTiming/servinspec22.dat","resources/simulationTiming/servinspec23.dat"});
        inspectors.add(inspector1);
        inspectors.add(inspector2);

        Workstation workstation1 = new Workstation(1,new String[] {"resources/simulationTiming/ws1.dat"});
        Workstation2 workstation2 = new Workstation2(2,new String[] {"resources/simulationTiming/ws2.dat"});
        Workstation3 workstation3 = new Workstation3(3,new String[] {"resources/simulationTiming/ws3.dat"});

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

       
    }
}
