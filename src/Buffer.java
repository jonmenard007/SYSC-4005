import java.util.ArrayList;

public class Buffer {
	public int id;
	public boolean buffer1Full;
	public boolean buffer2Full;
	public Enum componentType;
	
	
	public Buffer(int id, Enum componentType) {
		buffer1Full = false;
		buffer2Full = false;
		this.id = id;
		this.componentType = componentType;
	}
	
	public boolean isBufferFull() {
		return buffer1Full && buffer2Full;
	}
	
	public int bufferSize() {
		if(isBufferFull()) {
			return 2;
		}else if (buffer1Full) {
			return 1;
		}
		
		return 0;
	}
	
	public boolean isComponentAvailable(Enum component) {		
		if(componentType.equals(component) && bufferSize() > 0) {
			return true;
		}
		
		return false;
	}
	
	public Enum getComponent() {
		switch(bufferSize()) {
		case 1: 
			buffer1Full = false;
			return componentType;
		case 2:
			buffer2Full = false;
			return componentType;	
		}
		
		return null;
	}
	
	public boolean addComponent(Enum component) {
		if(componentType.equals(component)) {
			switch(bufferSize()) {
			case 0:
				buffer1Full = true;
				return true;
			case 1: 
				buffer2Full = true;
				return true;
			}
		}
			
		return false;
	}
	
	

}
