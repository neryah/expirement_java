package Array;

public class Array {
	public static void main(String[] args) {
        Array arr = new Array(3,4);
        arr.printRand2DArray();
    }

    private int cols;
    private int rows;
    public Array(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
    }
    public void printRand2DArray(){
        for (int i = 0; i < this.rows; i ++) {
            for (int j = 0; j < this.cols; j ++) {
                char cur = Math.round( Math.random() ) == 1 ? '1' : '0';
                System.out.printf(cur + " ");
            }
            System.out.println("");
        }
    }
}
