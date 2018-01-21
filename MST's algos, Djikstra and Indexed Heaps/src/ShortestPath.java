
/* Ver 1.0: Starter code for Dijkstra's Shortest path algorithm */

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;

public class ShortestPath {
    static final int Infinity = Integer.MAX_VALUE;

    static void DijkstraShortestPaths(Graph g, Vertex s)
    {
    	//An object of CompareVertex helps in deciding the priority of vertices
    	CompareVertex compvertex = new CompareVertex();
        for(Vertex u : g){
        	u.seen = false;//Consider vertex isn't seen yet
        	u.d = Infinity;//setting each vertex's distance as infinity
        	u.p = null;//No vertex has parent as of now
        	u.inTree = false;//To denote whether it has been added in Set or not
        }
        int i=1;
        s.d = 0;//Source distance is 0
        HashSet<Vertex> ts = new HashSet<Vertex>();//Creating a set to add vertices
      //declaring indexed heap with size as g.size(vertices) and priority as vertex from CompareVertex class
        IndexedHeap<Vertex> q = new IndexedHeap<>(g.size,compvertex);
      //Adding all the vertices in the indexed heap
        for(Vertex v : g){
        	q.add(v);
        	v.index = i++;
        }
        
        while(q.size > 0){
        	Vertex u = q.remove();
        	u.seen = true;
        	ts.add(u);//Adding it in the set
        	u.inTree = true;//Updating value to true to denote that it has been added
        	for(Edge e : u.adj){
        		Vertex v = e.otherEnd(u);
        		if(!ts.contains(v) && u.d + e.weight < v.d){
        			v.d = u.d + e.weight;
        			v.p = u;
        			q.decreaseKey(v);
        		}
        	}
        }
   }

    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

	Graph g = Graph.readGraph(in);
	int src = in.nextInt();
	int target = in.nextInt();
        Vertex s = g.getVertex(src);
	Vertex t = g.getVertex(target);
        DijkstraShortestPaths(g, s);

	System.out.println(t.d);
    }
}
