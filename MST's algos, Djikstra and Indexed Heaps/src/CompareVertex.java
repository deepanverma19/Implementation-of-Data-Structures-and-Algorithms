import java.util.Comparator;

//This class has been built for deciding the priority of vertices in the heap

public class CompareVertex implements Comparator<Vertex>{

		@Override
		public int compare(Vertex u, Vertex v) {
			if(u.d > v.d) return 1;

	    	else if(u.d == v.d) return 0;

	    	else return -1;
		}
    }