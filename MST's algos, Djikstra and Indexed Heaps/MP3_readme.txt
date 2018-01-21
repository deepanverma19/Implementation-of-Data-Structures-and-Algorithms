
Mandatory Project 3
---------------------
Indexed Heaps Implementation
-------------------------

The project deals with implementing various methods for the data structure Binary Heap and Indexed Heap plus implementing the Prim's MST using indexed heaps and java's Priority Queues.
Also implementing the Dijkstra's Shortest Path algorithm using Indexed Heaps
And comparing the performance of both the Prim MSTs on large inputs. 

This zip file includes 13 java files:

1. BinaryHeap.java
------------------
This JAVA file includes the implementation of various methods of Binary Heap which are as follows:

1.	BinaryHeap(T[] q, Comparator<T> comp) 	// Building a PQ with a given array
2.	BinaryHeap(int n,Comparator<T> comp)	// Building a PQ of given max size
3.	Add(T x) //Adding an element in the heap
4.	Resize() //Resizing the array in case it’s full
5.	T remove() //Removing an element from the heap
6.	T peek() //Removing the first element from the heap
7.	percolateUp(int i) //Percolating up the element while adding in the heap
8.	percolateDown(int i) //Percolating up the element while removing from the heap
9.	buildHeap() //Building the heap
10.	heapsort(T[] A, Comparator<T> comp) // sorting the elements of the heap


2. CompareVertex.java
----------------------
This JAVA file is used for setting up the Vertex's priority in the Prim's MST using indexed heaps.

3. Driver.java
--------------
This file is the main driver of the program which prints the MST, Distance alongwith Timer and Memory.

4. Edge.java
-------------
This JAVA file is the edge representing the various requirements of an edge in Graph.

5. Graph.java
-------------
This file consists of various techniques and methods which can be used to read the input Graph.

6. Index.java
-------------
This file consists of an interface which contains various methods which have been built in the Vertex.java. They are:

public void putIndex(int index);
public int getIndex();

7. IndexedHeap.java
--------------------
This JAVA file includes the implementation of various methods of Indexed Heap which are as follows while some of them override the BinaryHeap.java:

1.	IndexedHeap(T[]q, Comparator<T> comp)	// Building a PQ with a given array
2.	IndexedHeap(int n,Comparator<T> comp) 	// Building a PQ of given max size
3.	percolateDown(int i) //Percolating down the element while removing from the heap and updating the index
4.	decreaseKey(T x) //Restore the heap order property after priority of x has decreased

8. MST.java
------------
This JAVA file contains the implementation of Prim's MST algorithm using priority queue of vertices; Indexed Heaps.

9. PQ.java
------------
This JAVA file consists of an interface which contains various methods which are:

public void insert(T x);
public T deleteMin();
public T min();
public void add(T x);
public T remove();
public T peek();

10. Prim1.java
----------------
This JAVA file contains the implementation of Prim's MST algorithm using JAVA's Priority Queues of edges.

11.ShortestPath.java
---------------------
This JAVA file contains the implementation of Dijkstra's Shortest Path algorithm using Indexed Heaps.

12. Timer.java
--------------
This JAVA file contains the various factors which display out the Timing and Memory capcaity.

13. Vertex.java
----------------
This JAVA file is the vertex representing the various requirements of an vertex in Graph.
