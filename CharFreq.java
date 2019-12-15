package CharFreq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CharFreq {
	private int[] char_cnt = new int[27];
	private int tot_cnt = 0;
	
	public void addSample(char a){
		int c_int = this.char2int(a);
		this.char_cnt[c_int] += 1;
		if ((c_int < 26) && (c_int >= 0)){
			this.tot_cnt += 1;
		}
	}
	
	public double get_single_stat(char a){
		int c_int = this.char2int(a);
		if (this.tot_cnt == 0){
			return 0;
		}
		return 100*Double.valueOf(this.char_cnt[c_int])/Double.valueOf(this.tot_cnt);
	}
	
	private int char2int(char a){
		int idx = 26; // defaultl is non-letter
		if (Character.isLetter(a)){
			idx = Character.toLowerCase(a) - 'a';
		}
		return idx;
	}
	
	public void print_stat(){
		char c;
		for (int ii = 0; ii < 26 ; ii++){
			c = (char)(ii + 'a');
			System.out.print("The frequency for " + String.format("%c",c) + " is ");
			System.out.print(String.format("%.03f",this.get_single_stat(c)));
			System.out.println("%");
		}
	}
	
	public int readFile (String filename) {
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(filename));
			int data = reader.read();
			char cur_data;
			
			while(data != -1){
				cur_data = (char)data;
				this.addSample(cur_data);
				data = reader.read();
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static void main(String[] args){
		CharFreq CF = new CharFreq();
		CF.readFile("/home/java25/workspace/java25_exp/src/CharFreq/tmp.txt");
		CF.print_stat();
	}
	
}
