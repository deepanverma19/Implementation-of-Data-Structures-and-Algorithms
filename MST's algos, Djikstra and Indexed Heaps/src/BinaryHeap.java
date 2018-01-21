// Ver 1.0:  Starter code for Binary Heap implementation

import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
    Object[] pq; //Array which stores the elements for PQ
    Comparator<T> c; //Comparator which is used for comparing the elements
    int size; // Total no. of elements in the heap i.e. the size 
    
    /** Build a priority queue with a given array q */
    
    BinaryHeap(T[] q, Comparator<T> comp) {
    	
    	size = q.length;
    	pq = (T[]) new Object[size+1];
    	for(int i=0;i<size;i++){
    		pq[i+1] = q[i];
    	}
    	c = comp;
    	buildHeap();
    }

    /** Create an empty priority queue of given maximum size */
    
    BinaryHeap(int n, Comparator<T> comp) { 
    	
    	c = comp;
    	size = 0;
    	pq =(T[]) new Object[n+1];
    	
    }

    public void insert(T x) {
	add(x);
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }

    //Adding the elements in a binary heap
    
    public void add(T x) {
    	/*if(size == pq.length-1)
    		resize();*///Utility function to resize array when it is full
    	size++;
    	pq[size] = x;
    	percolateUp(size);
    }

    
    //Utility function to resize the array when it is full
    private void resize() {
	   T[] a = (T[]) new Object[(int)(pq.length * 2)]; //2 means doubling the array length
    	for(int i=0;i<pq.length;i++){
    		a[i+1]=(T) pq[i+1];
    	}
    	pq = (T[]) a;
	}
    
    
    //Removing the elements from the binary heap
	public T remove() {
		T min = (T) pq[1];
    	pq[1] = pq[size--];
    	percolateDown(1);
	return min;
    }

    //Removing the first element from the heap
	public T peek() {
    	return (T) pq[1];
    }

    /** pq[i] may violate heap order with parent
     * percolating Up the element in binary heap*/
    void percolateUp(int i) {
    	pq[0] = pq[i];
    	while(i > 1 && c.compare((T) pq[i/2],(T) pq[0]) > 0){
    		pq[i] = pq[i/2];
    		i = i/2;
    	}
    	pq[i] = pq[0];
    }

    /** pq[i] may violate heap order with children 
     * Percolating Down the element in binary heap*/
    	void percolateDown(int i) { 
    	T x = (T) pq[i];
    	while(2*i <= size){
    		if(2*i == size){
    			if(c.compare(x, (T) pq[size]) > 0){
    				pq[i] = pq[size];
    				i = size;
    			}else{
    				break;
    			}
    		}else{
    			int schild = 2*i;
    			if(c.compare((T) pq[schild], (T) pq[schild+1]) > 0)
    				schild++;
    			if(c.compare(x,(T) pq[schild]) > 0){
    				pq[i] = pq[schild];
    				i = schild;
    			}else{
    				break;
    			}
    		}
    	}
    	pq[i] = x;
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
    	for(int i=size/2;i>0;i--){
    		percolateDown(i);
    	}
    }

    /* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to build heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
    
    //Sorting the elements of the heap
	public static<T> void heapSort(T[] A, Comparator<T> comp) {
    	BinaryHeap<T> bh = new BinaryHeap<T>(A.length, comp);
    	for(int i=0;i<A.length;i++){
    		bh.add(A[i]);
    	}
    	for(int i=0;i<A.length;i++){
    		System.out.println(bh.remove());
    	}
    }
    
}
