package Animal;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.Iterator;


public class Animal{
	public String type;
	public String name;
	
	public static void main (String[] args) {
		TreeSet<Animal> collect = new TreeSet<Animal>(new AnimalComp());
		
		Animal a = new Animal("Dog", "Rex");
		Animal b = new Animal("Cat", "Shmil");
		Animal c = new Animal("Racoon", "Robbie");
		Animal d = new Animal("Panda", "Ted");
		Animal e = new Animal("Dog", "Bob");
		
		collect.add(a);
		collect.add(b);
		collect.add(c);
		collect.add(d);
		collect.add(e);
		
		Iterator<Animal> iterator = collect.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		}

	
	public Animal (	String type, String name){
		this.type = type;
		this.name = name;
	}
	
	
	
	public String toString(){
		return this.name + " the " +this.type;
	}
}

class AnimalComp implements Comparator<Animal> {
	@Override
	public int compare(Animal a, Animal b){
		int typeCmp = a.type.compareTo(b.type);
		int nameCmp = a.name.compareTo(b.name);
		return (typeCmp == 0) ? nameCmp : typeCmp;
	}	
}

