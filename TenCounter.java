package TenCounter;

public class TenCounter {
	static private int general_cnt = 0;
	private int my_cnt = 0;
	
	public void next10(){
		int num = general_cnt*100 + this.my_cnt*10;
		for (int ii = 0; ii < 10 ; ii++){
			System.out.print(String.format("%03d",num+ii) + " ");
		}
		System.out.println("");
		general_cnt += 1;
		this.my_cnt += 1;
	}
	
	public static void main (String[] args) {
		TenCounter A = new TenCounter();
		TenCounter B = new TenCounter();
		TenCounter C = new TenCounter();
		
		A.next10();
		B.next10();
		C.next10();
		A.next10();
		A.next10();
				
	}

}
