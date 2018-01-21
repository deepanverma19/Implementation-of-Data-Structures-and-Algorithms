/**
 * Class to represent a vertex of a graph
 * @author rbk
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Vertex implements Iterable<Edge> {
    int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int d;  // duration of task corresponding to vertex
    List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
    int indegree;//edges coming in the vertex
    Vertex p;//parent of vertex
    int cno;//component number
    int top;//Used in topological order algo 1
    int ec;//earliest completion time
    int lc;//latest completion time
    int slack;//time difference between ec and lc

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	d = Integer.MAX_VALUE;
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
