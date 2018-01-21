
Mandatory Project 2
---------------------
Skip Lists Implementation
-------------------------

The project deals with implementing various methods for the data structure SkipLists and comparing the performance of SkipLists' methods add(), contains() and remove() with TreeMaps.

This zip file includes 5 java files:

1. SkipList.java
-----------------

This file contains the SkipList interface which basically includes undefined methods for the SkipLists.
The various methods are:

 boolean add(T x);  // Add an element x to the list // Returns true if x is new, false otherwise.

 T ceiling(T x); // Least element that is >= x, or null if no such element

 boolean contains(T x);  // Is x in the list?

 T findIndex(int n);  // Return the element at index n in the list

 T first();  // Return the first element of the list

 T floor(T x);  // Greatest element that is <= x, or null if no such element

 boolean isEmpty();  // Is the list empty?

 Iterator<T> iterator();  // Returns an iterator for the list

 T last();  // Return the last element of the list

 void rebuild();  // Rebuild this list into a perfect skip list

 T remove(T x);  // Remove x from this list; returns false if x is not in list

 int size();  // Number of elements in the list

2. SkipListImpl.java
--------------------

This files implements all the methods being declared in the interface before.

3. SkipListDriver.java
-----------------------

This file contains the driver program which reads the input file and generates the output accordingly.

4. SkipListVSTreeMap.java
--------------------------

This file is another main file which compares the performance for the methods of add(), contains(), remove() for SkipLists and TreeMaps.

5. Timer. java
--------------

This file implements the methods for calculating the running times.