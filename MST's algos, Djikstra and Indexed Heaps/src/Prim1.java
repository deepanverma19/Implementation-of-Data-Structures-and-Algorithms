
/* Ver 1.0: Starter code for Prim's MST algorithm */


import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Prim1 {
    static final int Infinity = Integer.MAX_VALUE;
    static int size = 1;

    static int PrimMST1(Graph g, Vertex s)
    {
    	
    	int wmst = 0;
    	
    	for(Vertex u : g){
    		u.seen = false;
    		u.p = null;
    		u.inTree = false;
    	}
    	s.d = 0;//Source distance is 0
    	//Creating a PQ of edges
    	PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    	for(Edge e : s.adj){
    		pq.add(e);//Adding all the edges in the PQ
    	}
    	s.inTree = true;//Source has been added in the tree
    	while(!pq.isEmpty()){
    		Edge e = pq.remove();
    		if(e.from.inTree && e.to.inTree)
    			continue;
    		if(e.from.inTree){
    			for(Edge f : e.to.adj){
    				pq.add(f);
    			}
    			e.to.inTree = true;
    			e.to.p = e.from;
    		}
    		else{
    			e.from.inTree = true;
    			e.from.p = e.to;
    			for(Edge f : e.from.adj){
    				pq.add(f);
    			}
    		}
    		//System.out.println(e);
    		wmst = wmst + e.weight;
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
        System.out.println(PrimMST1(g, s));
        timer.end();
        System.out.println(timer);
    }
}
