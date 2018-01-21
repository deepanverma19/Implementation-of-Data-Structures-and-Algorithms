/**
 * Class to represent a vertex of a graph
 * @author rbk
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Vertex implements Index, Comparator<Vertex>, Iterable<Edge> {
    int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int d;  Vertex p;  // fields used in algorithms of Prim and Dijkstra
    List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
    int index; // used for index in the priority queue
    boolean inTree; // used to know if is has been added in tree or not
    
    public int getIndex() {
    	return this.index;
    }

    public void putIndex(int i) {
    	index = i;
    }

    public int compare(Vertex u, Vertex v) {
    	if(u.d > v.d) return 1;

    	else if(u.d == v.d) return 0;

    	else return -1;
    }

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	p = null;
	adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
    }

    
    public Iterator<Edge> iterator() { return adj.iterator(); }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
}
