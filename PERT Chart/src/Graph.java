/**
 * Class to represent a graph
 *  @author rbk
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class Graph implements Iterable<Vertex> {
    List<Vertex> v; // vertices of graph
    int size; // number of verices in the graph
    boolean directed;  // true if graph is directed, false otherwise

    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    Graph(int size) {
	this.size = size;
	this.v = new ArrayList<>(size + 1);
	this.v.add(0, null);  // Vertex at index 0 is not used
	this.directed = false;  // default is undirected graph
	// create an array of Vertex objects
	for (int i = 1; i <= size; i++)
	    this.v.add(i, new Vertex(i));
    }

    /**
     * Find vertex no. n
     * @param n
     *           : int
     */
    Vertex getVertex(int n) {
	return this.v.get(n);
    }
    
    /**
     * Method to add an edge to the graph
     * 
     * @param a
     *            : int - one end of edge
     * @param b
     *            : int - other end of edge
     * @param weight
     *            : int - the weight of the edge
     */
    void addEdge(Vertex from, Vertex to, int weight) {
	Edge e = new Edge(from, to, weight);
	if(this.directed) {
	    from.adj.add(e);
            to.revAdj.add(e);
	} else {
	    from.adj.add(e);
	    to.adj.add(e);
	}
    }

    /**
     * Method to create iterator for vertices of graph
     */
    public Iterator<Vertex> iterator() {
	Iterator<Vertex> it = this.v.iterator();
	it.next();  // Index 0 is not used.  Skip it.
	return it;
    }
    
    // Run DFS on graph
    
   
    //Running DFSVisit for topological algorithm 2
    public void DFSVisit(Vertex u,List<Vertex> list){
    	u.seen = true;
    	for(Edge e : u.adj){
    		Vertex v = e.otherEnd(u);
    		if(!v.seen){
    			v.p = u;
    			v.seen = true;
    			DFSVisit(v,list);//Recursively calling the DFSVisit algo
    		}
    	}
    	((LinkedList<Vertex>) list).addFirst(u);//using addFirst to process the vertices
    }
    
    
    //Topological Ordering algorithm 1
    public List<Vertex> topologicalOrder1(Graph g){
    	for(Vertex u : g){
        	u.indegree = u.revAdj.size();//Setting indegree as the no of edges incident to it
        	if(u.indegree == 0 && u != g.getVertex(size-1) && u!=g.getVertex(size)){
        		g.addEdge(g.getVertex(size-1), u, 1);//Adding the src vertex
        		u.indegree = u.indegree + 1;//Upgrading the other vertices indegree
        		g.getVertex(size-1).indegree = 0;//Setting the src vertex indegree as zero
        	}
        	if(u.adj.size() == 0 && u!=g.getVertex(size)){
        		g.addEdge(u, g.getVertex(size), 1);//Adding the destination vertex
        	}
        }
    	List<Vertex> topList = new LinkedList<Vertex>();
    	Queue<Vertex> q = new LinkedList<>();
    	int count = 0;
    	for(Vertex u : g){
    		if(u.indegree == 0){
    			q.add(u);//Adding those vertices whose indegree are zero
    		}
    	}
    	while(!q.isEmpty()){
    		Vertex u = q.remove();
    		u.seen = true;
    		topList.add(u);
    		u.top = ++count;
    		for(Edge e : u.adj){
    			Vertex v = e.otherEnd(u);
    			v.indegree--;//Deleting the edge
    			if(v.indegree == 0){
    				q.add(v);
    			}
    		}
    	}
    	
    	return topList;//returning the topological order
    }
    
    //topological Order algorithm 2 using DFS
    public List<Vertex> topologicalOrder2(Graph g){
    	for(Vertex u : g){
        	u.indegree = u.revAdj.size();
        	u.seen = false;
        	u.p = null;
        	if(u.indegree == 0 && u != g.getVertex(size-1) && u!=g.getVertex(size)){
        		g.addEdge(g.getVertex(size-1), u, 1);
        		u.indegree = u.indegree + 1;
        		g.getVertex(size-1).indegree = 0;
        	}
        	if(u.adj.size() == 0 && u!=g.getVertex(size)){
        		g.addEdge(u, g.getVertex(size), 1);
        	}
        }
    	List<Vertex> list = new LinkedList<>();
    	for(Vertex u : g){
    		if(!u.seen){
    			DFSVisit(u,list);
    		}
    	}
    	Collections.reverse(list);//reversing list to get original topological order
    	return list;//returning the topological Order 2 using DFSVisit
    }
    
    //Calculating EC
    public void findEC(Graph g){
    	/* Initialisation*/
    	for(Vertex u : g){
    		u.ec = u.d;
    		u.seen = false;
    	}
    	g.getVertex(g.size-1).ec = 0;//setting the ec of src vertex as 0
    	for(Vertex u : g.topologicalOrder1(g)){//Checking vertices in topological Order 1
    		for(Edge e : u.adj){
    			Vertex v = e.otherEnd(u);
    			v.ec = Math.max(v.ec,u.ec + v.d);//Selecting the max value out of both
    			
    		}
    	}
    }
    
    //Calculating LC
	public void findLC(Graph g){
    	g.getVertex(g.size).lc = g.getVertex(g.size).ec;//Setting the lc of destination vertex as same as of src
    	for(Vertex u : g){
    		u.lc = g.getVertex(g.size).lc;//Setting all vertices lc as destination one's
    	}
    	for(Vertex u : g.topologicalOrder2(g)){
    		u.slack = u.lc - u.ec;
    		for(Edge e : u.revAdj){
    			Vertex p = e.otherEnd(u);
    			p.lc = Math.min(p.lc, u.lc-u.d);//selecting the min value out of both
    			//p.slack = p.lc - p.ec;//Calculating the slack
    		}
    	}
    }
	//To be implemented in second deadline
	/*public void newGraph(Graph g){
		g.findEC(g);
		g.findLC(g);
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		for(Vertex u : g){
			for(Edge e : u.adj){
				Vertex v = e.otherEnd(u);
				//Checking if it's a tight edge
				if(u.slack == 0 && v.slack == 0 && u.lc + v.d == v.lc && u!=g.getVertex(size-1)){
					list.add(u);
					//System.out.print(e);
				}
			}
		}
			Graph g1 = new Graph(list.size());
			for(Vertex u : g1){
				System.out.println(u);
				//g1.addEdge(u, , weight);
				for(Edge e :u.adj){
					//System.out.println(v);
					Vertex v = e.otherEnd(u);
					//System.out.println("From : "+u+" To:"+v);
				}
			}
			//System.out.println(g1.size);
	}
	*/
	//Checking whether it's a tight edge or not
	public void findCriticalPathLength(Graph g){
		int length = 0;//length of Critical Path
		int criticalNodes = 0;//Number of Critical nodes
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		ArrayList<ArrayList<Vertex>> criticalPaths = new ArrayList<ArrayList<Vertex>>();
		for(Vertex u : g){
			for(Edge e : u.adj){
				Vertex v = e.otherEnd(u);
				//Checking if it's a tight edge
				if(u.slack == 0 && v.slack == 0 && u.lc + v.d == v.lc && u!=g.getVertex(size-1)){
					length = length + u.d;//Calculating the length of CP
					criticalNodes = criticalNodes +1;//Calculating the no of critical nodes
					list.add(u);
				}
			}
		}
		criticalPaths.add(list);
		//System.out.println();
		System.out.println(length);//Displaying length of CP
		for(Vertex u : list){
			System.out.print(u+" ");
		}
		//System.out.print(list);
		System.out.println();
		System.out.println("Task \t EC \t LC \t Slack");//Displaying the Table 
		for(Vertex u : g){
			u.seen = false;
			for(Edge e : u.adj){
				if(u != g.getVertex(size-1) && !u.seen){
					u.seen = true;
					System.out.println(u+" \t "+u.ec+" \t "+u.lc+" \t "+u.slack);
					}
				}
			}
		System.out.println(criticalNodes);//returning no of critical nodes
		System.out.println(criticalPaths.size());//returning no of critical paths
		
		for(ArrayList<Vertex> al : criticalPaths){
			for(Vertex u:al){
				System.out.print(u+" ");
			}
		}
		System.out.println();
	}
	
	
    // Run BFS from a given source node
    // Precondition: nodes have already been marked unseen
    public void bfs(Vertex src) {
	src.seen = true;
	src.d = 0;
	Queue<Vertex> q = new LinkedList<>();
	q.add(src);
	while(!q.isEmpty()) {
	    Vertex u = q.remove();
	    for(Edge e: u.adj) {
		Vertex v = e.otherEnd(u);
		if(!v.seen) {
		    v.seen = true;
		    v.d = u.d + 1;
		    q.add(v);
		}
	    }
	}
    }

    // Check if graph is bipartite, using BFS
    public boolean isBipartite() {
	for(Vertex u: this) {
	    u.seen = false;
	}
	for(Vertex u: this) {
	    if(!u.seen) {
		bfs(u);
	    }
	}
	for(Vertex u:this) {
	    for(Edge e: u.adj) {
		Vertex v = e.otherEnd(u);
		if(u.d == v.d) {
		    return false;
		}
	    }
	}
	return true;
    }


    // read a directed graph using the Scanner interface
    public static Graph readDirectedGraph(Scanner in) {
	return readGraph(in, true);
    }
    
    // read an undirected graph using the Scanner interface
    public static Graph readGraph(Scanner in) {
	return readGraph(in, false);
    }
    
    public static Graph readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
	int n = in.nextInt(); // number of vertices in the graph
	int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph g = new Graph(n);
	g.directed = directed;
	for (int i = 0; i < m; i++) {
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    g.addEdge(g.getVertex(u), g.getVertex(v), w);
	}
	return g;
    }

}
