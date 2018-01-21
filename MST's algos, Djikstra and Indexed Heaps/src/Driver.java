
/* Ver 1.0: Driver code for MST and Shortest paths */

import java.util.Scanner;
import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
	int VERBOSE = 0;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
	if (args.length > 1) { VERBOSE = Integer.parseInt(args[1]); }

	Graph g = Graph.readGraph(in);
        int src = in.nextInt();
        int target = in.nextInt();
        Vertex s = g.getVertex(src);
        Vertex t = g.getVertex(target);

	Timer timer = new Timer();

	if(VERBOSE%3 == 0) { // Run MST if VERBOSE is a multiple of 3
	    int mst = MST.PrimMST(g, s);
            System.out.println("MST: " + mst);

	    if(VERBOSE > 0) {
                System.out.println();
	        for(Vertex u: g) {
		    System.out.println(u + " " + u.d + " " + u.p);
	        }
		System.out.println();
	    }
	}

        if(VERBOSE%5 == 0) { // Run Shortest path if VERBOSE is a multiple of 5
	    ShortestPath.DijkstraShortestPaths(g, s);
            System.out.println("Distance: " + t.d);

            if(VERBOSE > 0) {
                System.out.println();
                for(Vertex u: g) {
                    System.out.println(u + " " + u.d + " " + u.p);
                }
		System.out.println();
            }
	}

	System.out.println(timer.end());
    }
}
