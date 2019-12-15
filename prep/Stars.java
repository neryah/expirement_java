package Stars;
import java.io.*;

public class Stars {
    private int my_len;
    private String my_str = "*"; 
    
    public Stars(int length) {
    	this.my_len = length;
    }
    
    public void printMe() {
    	for (int ii = 0; ii < this.my_len; ii ++) {
    	    System.out.println(this.my_str);
    	    this.my_str = " " + this.my_str;
    	}
    }
    
	public static void main(String[] args) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Choose number of stars (positive integer): ");
		String input_string; 
		try {
			input_string = stdin.readLine();
			int input_num = Integer.parseInt(input_string);
			if (input_num <= 0) {
				System.out.println("Please enter a positive integer only");
				main(args);
			}
			Stars my_stars = new Stars(input_num);
			my_stars.printMe();
		} catch (IOException e) {
			System.out.println("IO Error. Try again.\n");
			e.printStackTrace();
			main(args);
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a positive integer only");
			main(args);
		}
    }
}
