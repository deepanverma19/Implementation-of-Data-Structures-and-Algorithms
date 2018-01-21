// Ver 1.0:  Starter code for Indexed heaps

/**Separate functions have been built over here
 * instead of calling the functions from the Binary Heap
 * classe directly because they have to be assigned 
 * Indices over here
 */

import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
	
    /** Build a priority queue with a given array q */
	IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
    }

    /** Create an empty priority queue of given maximum size */
	IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
    }
	
    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
	percolateUp(x.getIndex());
	for(int i=1;i<size;i++){
		T y  = (T) pq[i];
		y.putIndex(i);
		}
   }
    
    /** Percolating down the element after removing and reassigning the index to the element */
    void percolateDown(int i) { 
    	super.percolateDown(i);
    	((Vertex) pq[i]).putIndex(i);
    }
}
