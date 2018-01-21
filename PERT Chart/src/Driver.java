/** Driver program for MP4
 *  
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
	Graph g = Graph.readDirectedGraph(in);
	for(Vertex u: g) {
	    u.d = in.nextInt();
	}
	
	Timer timer = new Timer();
	CriticalPaths cp = new CriticalPaths(g);
	cp.findCriticalPaths(g);
	System.out.println(timer.end());
    }
}

