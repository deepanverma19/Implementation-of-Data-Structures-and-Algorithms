
import java.util.Iterator;

public interface SkipList<T extends Comparable<? super T>> {

	boolean add(T x); // Add an element x to the list.
	// Returns true if x is new, false otherwise.

	T ceiling(T x); // Least element that is >= x, or null if no such element

	boolean contains(T x); // Is x in the list?

	T findIndex(int n); // Return the element at index n in the list

	T first(); // Return the first element of the list

	T floor(T x); // Greatest element that is <= x, or null if no such element

	boolean isEmpty(); // Is the list empty?

	Iterator<T> iterator(); // Returns an iterator for the list

	T last(); // Return the last element of the list

	void rebuild(); // Rebuild this list into a perfect skip list

	T remove(T x); // Remove x from this list; returns false if x is not in list

	int size(); // Number of elements in the list
}