package Serie;
import java.io.*;

public class Serie {
    private int my_len;
	private int my_start;
	private int my_delta;
	    
	public Serie(int start, int delta, int length) {
		this.my_start = start;
		this.my_delta = delta;
		this.my_len = length;
	}
	    
	public void printMe() {
	   	int cur_value;
	   	System.out.print("Serie: ");
	   	for (int ii = 0; ii < this.my_len; ii ++) {
	   	    cur_value = ii*this.my_delta + this.my_start;
	   		System.out.print(cur_value + " ");
	   	}
	   	System.out.println("");
	}
	
	public static int extractIntFromString(String s, int pos) {
		// s - string to extract from
		// pos - position of the integer to extract
		String num = "";
		int ii = 0;
		int sign = 1;
		while (pos > 0) {
			num = "";
			while (ii < s.length() && !Character.isDigit(s.charAt(ii))) {
				if (s.charAt(ii) == '-') {
					sign = -1; // support negative a1 and delta
				} else {
					sign = 1;
				}
				ii++;
			}
			while (ii < s.length() && Character.isDigit(s.charAt(ii))) {
				num += s.charAt(ii);
				ii++;
			}
			if (num == "") {
				throw new IllegalArgumentException("No integer found in string at pos" + pos); 
			}
			pos--;
		}
		return sign*Integer.parseInt(num);		
	}
	    
	public static void main(String[] args) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a1, delta & n for the arithmetic serie");
		System.out.println("a1    - the first number of the serie - integers only");
		System.out.println("delta - the difference - integers only");
		System.out.println("n     - the length of the serie - positive integer");
		System.out.println("enter numbers in format <a1> <delta> <n>");
		String input_string;
		int a1, delta, n;
		try {
			input_string = stdin.readLine();
			a1 = extractIntFromString(input_string, 1);
			delta = extractIntFromString(input_string, 2);
			n = extractIntFromString(input_string, 3);
			if (n<=0) {
				System.out.println("n must be positive integer");
				System.out.println("please try again");
				main(args);
			}
			Serie ddd = new Serie(a1, delta, n);
			ddd.printMe();
		} catch (IOException e) {
			System.out.println("IO Error. Try again.\n");
			e.printStackTrace();
			main(args);
		}
		catch (NumberFormatException e) {
			System.out.println("format error, please try again");
			main(args);
		}
		catch (IllegalArgumentException e) {
			System.out.println("Illegal input string, please try again");
			main(args);
		}
	}
}