import java.util.Iterator;
import java.util.Random;

/* Skeleton for skip list implementation.
	
*/
public class SkipListImpl<T extends Comparable<? super T>> implements SkipList<T> {

	/*
	 * Entry<T> is the Entry node for the SkipList elements which have the value
	 * of the element ie data, array of pointers ie next, and a previous pointer
	 * (mainly used for finding the last element)
	 */

	class Entry<T> {
		T data;
		Entry<T>[] next;

		// Entry Constructor which takes in the int data ie value

		Entry(T data) {
			this.data = data;
			next = new Entry[maxLevel + 1];
		}

		// Entry Constructor which takes in the data and levels for add() method

		Entry(T data, int levels) {
			this.data = data;
			next = new Entry[levels + 1];
		}
	}

	/*
	 * Variables header,tail of type of Entry<T> used for storing the sentinels
	 * Variable size- Size of skipList Variable threshold- Used for rebuild
	 * method Variable maxLevel- Maximum level of an ideal skipList
	 */

	Entry<T> header, tail;
	int size, threshold, maxLevel;

	/*
	 * SkipList Constructor which takes s - no of elements of a skipList x- used
	 * for storing the sentinel value of a header y- used for storing the
	 * sentinel value of a tail
	 * 
	 */

	SkipListImpl(int s, T x, T y) {
		size = 0;
		maxLevel = (int) Math.ceil(Math.log10(s) / Math.log10(2));
		threshold = (int) s;
		header = new Entry<>(x);
		tail = new Entry<>(y);
		for (int i = 0; i < maxLevel + 1; i++) {
			header.next[i] = tail;
			tail.next[i] = null;
		}
	}

	/*
	 * Helper function choice
	 * 
	 */

	int choice(int maxLevel) {
		int l = 0;
		Random random = new Random();
		while (l < maxLevel) {
			boolean b = random.nextBoolean();
			if (b == true)
				break;
			else
				l++;
		}
		return l;
	}

	/*
	 * Helper function Find- Used for locating the elements in a skipList
	 */

	Entry<T>[] find(T x) {
		Entry<T>[] prev = new Entry[maxLevel + 1];
		Entry<T> p = header;
		for (int i = maxLevel; i >= 0; i--) {
			while (p.next[i].data.compareTo(x) < 0) {
				p = p.next[i];
			}
			prev[i] = p;
		}
		return prev;
	}

	/*
	 * add method adds a element in skipList
	 */

	@Override
	public boolean add(T x) {
		Entry<T>[] prev = find(x);
		if (prev[0].next[0].data.compareTo(x) == 0) {
			prev[0].next[0].data = x;
			return false;
		} else {
			int lev = choice(maxLevel);
			Entry<T> n = new Entry<>(x, lev);
			for (int i = 0; i <= lev; i++) {
				n.next[i] = prev[i].next[i];
				prev[i].next[i] = n;
			}
			size++;
			return true;
		}
	}

	/*
	 * ceiling function basically gives out the element which is >= to x in the
	 * skipList or null if no such element found
	 */

	@Override
	public T ceiling(T x) {
		if (size == 0)
			return null;
		Entry<T>[] prev = find(x);
		if (prev[0].next[0].data.compareTo(x) == 0)
			return x;
		else {
			if (prev[0].next[0] != tail)
				return prev[0].next[0].data;
			else
				return null;
		}
	}

	/*
	 * contains method checks if element is present in the skipList
	 */

	@Override
	public boolean contains(T x) {
		if (size == 0)
			return false;

		Entry<T>[] prev = find(x);
		return prev[0].next[0].data.compareTo(x) == 0;
	}

	@Override
	public T findIndex(int n) {
		if(n >= size || n < 0)
			return null;
		Entry<T> x = header.next[0];
		for(int i=0;i < n;i++)
			x = x.next[0];
		return x.data;
	}

	/*
	 * Return the first element of the list
	 */

	@Override
	public T first() {
		return header.next[0] != tail ? header.next[0].data : null;
	}

	/*
	 * floor() function gives us the Greatest element that is <= x, or null if
	 * no such element
	 */

	@Override
	public T floor(T x) {
		if (size == 0)
			return null;
		Entry<T>[] prev = find(x);
		if (prev[0].next[0].data.compareTo(x) == 0)
			return x;
		else {
			if (prev[0] == header)
				return null;
			else
				return prev[0].data;
		}
	}

	/*
	 * if size is 0 then list is empty
	 */

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * iterator method is used to iterate over the elements of a skipList
	 * returns the SKIPLISTITERATOR
	 */

	@Override
	public Iterator<T> iterator() {
		return new SkipListIterator<>();
	}
	// This class generates the methods of an iterator ie hasNext() and next()

	public class SkipListIterator<T> implements Iterator<T> {

		// node element of type Entry<T>
		Entry<T> node;

		// SkipListIterator constructor used for assigning node header
		SkipListIterator() {

			// node element of type Entry<T>

			node = (Entry<T>) header;
		}

		// hasNext() function of iterator to check if there is any element
		// further

		public boolean hasNext() {
			return node.next[0] != tail;
		}

		// next() function of iterator to go to the next element

		public T next() {
			T data = node.next[0].data;
			node = node.next[0];
			return data;
		}
	}

	/*
	 * last() method gives us the last value of a skipList 
	 */

	@Override
	public T last() {
		Entry<T> temp = header; //temp iterates until it equals tails
		while(temp.next[0] != tail){
			temp = temp.next[0];
		}
		return temp.data;
	}

	/*
	 * resize() method basically resizes the size of the skiplists
	 */
	public Entry<T>[] resize(Entry<T>[] arr, int p, int r, int k) {
		if (p <= r) {
			if (k == 0) {
				for (int i = p; i <= r; i++) {
					arr[i] = new Entry<>(null, k);
				}
			} else {
				int q = (p + r) / 2;
				arr[q] = new Entry<>(null, k);
				resize(arr, p, q - 1, k - 1);
				resize(arr, q + 1, r, k - 1);
			}
		}
		return arr;
	}

	/*
	 * rebuild() method is used to rebuild the skiplist into ideal skiplist
	 * 
	 */
	@Override
	public void rebuild() {
		Entry<T>[] arr = new Entry[size];
		arr = resize(arr, 0, size - 1, (int) Math.ceil(Math.log10(size) / Math.log10(2)));
		int i = 0;
		// iterate over all the instances and place previous elements into arr
		// and setup links
		Entry<T> x = header.next[0];
		while (!x.equals(tail)) {
			arr[i].data = x.data;
			Entry<T>[] prev = find(x.data);
			for (int k = 0; k < prev.length; k++) {
				prev[k].next[k] = arr[i];
			}
			x = x.next[0];
			i++;
		}
	}

	/*
	 * remove(T x) removes the specific element passed in the method from the
	 * skipList
	 */

	@Override
	public T remove(T x) {
		if (size != 0) {
			Entry<T>[] prev = find(x);
			if (prev[0].next[0].data.compareTo(x) == 0) {
				Entry<T> n = prev[0].next[0];
				for (int i = 0; i < maxLevel; i++) {
					if (prev[i].next[i] == n) {
						prev[i].next[i] = n.next[i];
					} else
						break;
					size--;
					return n.data;
				}
			}
		}
		return null;
	}

	// size() method gives us the size of a skip List(no of elements)

	@Override
	public int size() {
		return size;
	}
}