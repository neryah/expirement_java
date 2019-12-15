package LogNet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Gate.*;


public class LogNet {
	public Map<String,Gate> LN;
	
	public LogNet(){
		LN = new HashMap<String,Gate>();
	}
	

	
	public void parser(String filename){
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();;
			String first;
			String second;
			while (line != null) {
				if (line.isEmpty()){
					line = reader.readLine();
					continue;
				}
				first = line.split("\\s+")[0];
				try {
					second = line.split("\\s+")[1];
				} catch (ArrayIndexOutOfBoundsException e) { // if line has no spaces
					System.out.println("illegal line");
					line = reader.readLine();
					continue;
				}
				
				// check type of row - declaration or connect
				if (first.contains(".")){ // connect
					String connect_to = second.split("\\.")[0];
					int connect_idx = Integer.parseInt(second.replaceAll("[^0-1]", ""));
					String first_name = first.split("\\.")[0];
					
					// format checks
					String first_out = first.split("\\.")[1];
					String second_in = second.split("\\.")[1];
					if (first_out.equals("out") && second_in.endsWith("]") && second_in.startsWith("in[")){
						if (LN.containsKey(connect_to)){
							LN.get(connect_to).connect(connect_idx, LN.get(first_name));
						} else {
							System.out.println("no such gate to connect " + connect_to);
						}
					} else {
						System.out.println("illegal connect format");
					}
					
				} else { // create
					if (!LN.containsKey(second)){
						switch (first){
						case "AND": LN.put(second,new AND()); break;
						case "OR": LN.put(second,new OR()); break;
						case "NOT": LN.put(second,new NOT()); break;
						case "XOR": LN.put(second,new XOR()); break;
						default: System.out.println("ERROR - no such gate of type: " + first);
						}
					} else {
						System.out.println("ERROR - net already contains gate with name: " + second);
					}
				}
				
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void out(){
		try {
			System.out.println(LN.get("output").output());
		} catch (NullPointerException e){
			System.out.println("net is not connecterd. please check all gates have input values");
		}
	}
	
	public static void main(String[] args){
		LogNet net = new LogNet();
		net.LN.put("output",new OUT());
		net.LN.put("1",new ONE());
		net.LN.put("0",new ZERO());

		net.parser("/home/java25/workspace/java25_exp/src/LogNet/net.txt");
		net.out();
	}
			
}

class NOT extends Gate{
	
	public NOT(){
		this.inputs = new Gate[1];
	}
	
	public boolean output(){
		return !(this.inputs[0].output());
	}
}

class AND extends Gate{
	
	public AND(){
		this.inputs = new Gate[2];
	}
	
	public boolean output(){
		return ((this.inputs[0].output()) && (this.inputs[1].output()));
	}
}

class OR extends Gate{
	
	
	public OR(){
		this.inputs = new Gate[2];
	}
	
	public boolean output(){
		return ((this.inputs[0].output()) || (this.inputs[1].output()));
	}
}

class XOR extends Gate{
	
	public XOR(){
		this.inputs = new Gate[2];
	}
	
	public boolean output(){
		return ((this.inputs[0].output()) ^ (this.inputs[1].output()));
	}
}

class ONE extends Gate{
	
	public boolean output(){
		return true;
	}
}

class ZERO extends Gate{
	
	public boolean output(){
		return false;
	}
}

class OUT extends Gate{
	
	public OUT(){
		this.inputs = new Gate[1];
	}
	
	public boolean output(){
		return this.inputs[0].output();
	}
}



