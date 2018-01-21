
/* Ver 1.0: Starter code for Prim's MST algorithm */


import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;


public class MST {
    static final int Infinity = Integer.MAX_VALUE;

    static int PrimMST(Graph g, Vertex s)
    {
        int wmst = 0;
        //An object of CompareVertex helps in deciding the priority of vertices
        CompareVertex compvertex = new CompareVertex();
        int i = 1;
        for(Vertex u : g){
        	u.seen = false;//Consider vertex isn't seen yet
        	u.p = null;//No vertex has parent as of now
        	u.d = Infinity;//setting each vertex's distance as infinity
        	u.inTree = false;//To denote whether it has been added in Set or not
        	
        }
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
        	wmst = wmst + u.d;//Updating the wmst with vertex's distance
        	ts.add(u);//Adding it in the set
        	u.inTree = true;//Updating value to true to denote that it has been added
        	for(Edge e : u.adj){
        		Vertex v = e.otherEnd(u);
        		if(!ts.contains(v) && e.weight < v.d){
        			v.d = e.weight;
        			v.p = u;
        			q.decreaseKey(v);
        		}
        	}
        }
        return wmst;
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
        Vertex s = g.getVertex(1);
        Timer timer = new Timer();
        timer.start();
        System.out.println(PrimMST(g, s));
        timer.end();
        System.out.println(timer);
    }
}
