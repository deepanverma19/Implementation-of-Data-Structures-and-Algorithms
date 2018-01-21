

public class CriticalPaths {
    
    void findCriticalPaths(Graph g) {
    	g.findEC(g); // Calculating EC for every Vertex
    	g.findLC(g); // Calculating LC for every Vertex
    	g.findCriticalPathLength(g);// Calculating the crititcal path
    	//g.newGraph(g);
    }
    CriticalPaths(Graph g) {
    }
}
