
public class simulator {
	public static void main(String[] args) {

        ArrayList<Inpector> inspectors = new ArrayList<Inspector>();

        Inspector inspector1 = new Inspector(1);
        Inspector inspector2 = new Inspector(2);
        inspectors.add(inspector1);
        inspectors.add(inspector2);

        Workstation workstation1 = new Workstation(1);
        Workstation2 workstation2 = new Workstation2(2);
        Workstation3 workstation3 = new Workstation3(3);

        Buffer buffer1 = new Buffer(1, C1);
        Buffer buffer2 = new Buffer(2, C1);
        Buffer buffer3 = new Buffer(3, C2);
        Buffer buffer4 = new Buffer(4, C1);
        Buffer buffer5 = new Buffer(5, C3);

        inspector1.addBuffer(buffer1);
        inspector1.addBuffer(buffer2);
        inspector1.addBuffer(buffer4);

        inspector2.addBuffer(buffer3);
        inspector2.addBuffer(buffer5);

        while(true){

            for(int i = 0; i < inspectors.length; i++){
                Inspector inspector = inspectors.get(i);
                if(inspector.getState == IDLE){
                    inspector.getComponent();
                }


            }


        }
    }
}
