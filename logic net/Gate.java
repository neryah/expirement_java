package Gate;

public abstract class Gate {
	protected Gate[] inputs;
	
	public void connect(int leg, Gate LN){
		try {
			inputs[leg] = LN;
		} catch (IndexOutOfBoundsException e){
			//e.printStackTrace();
			System.out.println("no such input for this gate");
		}
	}
	
	public abstract boolean output();
}

