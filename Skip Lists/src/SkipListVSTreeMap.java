/* This class compares the functions add(), remove(), contains()
 *	for SkipLists and TreeMaps
 */
import java.util.TreeMap;
import java.util.Random;


public class SkipListVSTreeMap {

	public static void main(String[] args) {
		final int inf1 = Integer.MIN_VALUE; //Storing the negative sentinel
		final int inf2 = Integer.MAX_VALUE;	//Stroing the positive sentinel
		Timer t1 = new Timer();	//Timer 1 for skipLists
		Timer t2 = new Timer();	//Timer 2 for TreeMaps
		SkipListImpl<Integer> sl = new SkipListImpl<Integer>(7,inf1,inf2);
		TreeMap<Integer,Integer> tm = new TreeMap<Integer,Integer>();
		Random ran = new Random();// ran for generating random numbers
		
		//	Comparing the add() for skipLists and TreeMaps
		
		System.out.println("Comparing the add method of SkipLists and TreeMaps");
		t1.start();
		for(int i=0;i<100000;i++){
			int a = ran.nextInt(100000);
			sl.add(a);
		}
		t1.end();
		t2.start();
		for(int i=0;i<100000;i++){
			int a = ran.nextInt(100000);
			tm.put(i, a);
		}
		t2.end();
		System.out.println(t1);
		System.out.println(t2);
		
		//	Comparing the contains() for SkipLists and TreeMaps
		
		System.out.println("Comparing the contains method for both");
		t1.start();
		sl.contains(100);
		t1.end();
		t2.start();
		tm.containsValue(100);
		t2.end();
		System.out.println(t1);
		System.out.println(t2);
		
		// Comparing the remove() for skipLists and TreeMAps
		
		System.out.println("Comparing the remove method for both");
		t1.start();
		sl.remove(100);
		t1.end();
		t2.start();
		tm.remove(100);
		t2.end();
		System.out.println(t1);
		System.out.println(t2);
	}
}
