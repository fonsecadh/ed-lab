package algorithmics;

import java.util.Random;

import graphs.Graph;

public class GraphPerformanceTest {
	
	private static final double RANGE_MIN = 1;
	private static final double RANGE_MAX = 50;
 
	public static Graph<Integer> initGraph(long n) {
		Graph<Integer> g = new Graph<Integer>((int) n);
		Random rand = new Random();
		try {
			for (int i = 0; i < n; i++) {				
				g.addNode(i);
			}
			
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (j != k) {
						double weight = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * rand.nextDouble();
						g.addEdge(j, k, weight);
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return g;
	}

}
