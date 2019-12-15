package NumList;

import java.math.*;
import java.util.*;


public class NumList {
	
	LinkedList<Integer> collect;
	
	public NumList(){
		collect = new LinkedList<Integer>();
	}
	
	public NumList(List<Integer> l){
		collect = new LinkedList<Integer>(l);
	}
	
	public void add (int num){
		collect.add(num);
	}
	
	public void add (List<Integer> l){
		collect.addAll(l);
	}
	
	public int getSum(){
		int sum = 0;
		Iterator<Integer> iterator = collect.iterator();
		while (iterator.hasNext()){
			sum+=iterator.next();
		}
		return sum;
	}
	
	public int getProduct(){
		int product = 1;
		Iterator<Integer> iterator = collect.iterator();
		while (iterator.hasNext()){
			product*=iterator.next();
		}
		return product;
	}
	
	
	public long getSqrSum(){
		long sum = 0;
		Iterator<Integer> iterator = collect.iterator();
		while (iterator.hasNext()){
			sum+=Math.pow(iterator.next(), 2);
		}
		return sum;
	}
	
	
	public static void main(String[] args){
		Integer [] nums = {1,2};
		Integer [] newNums = {4,5};
		NumList numList = new NumList(Arrays.asList(nums));
		
		numList.add(3);
		numList.add(Arrays.asList(newNums));
		
		System.out.println("Sum: "+numList.getSum());
		System.out.println("Product: "+numList.getProduct());
		System.out.println("Square Sum: "+numList.getSqrSum());
	}
}
