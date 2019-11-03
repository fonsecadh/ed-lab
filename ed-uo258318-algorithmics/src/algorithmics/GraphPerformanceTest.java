package algorithmics;

import java.util.Random;

import graphs.Graph;

public class GraphPerformanceTest {
	
	// Constants
	private static final double RANGE_MIN = 1;
	private static final double RANGE_MAX = 50;
 
	
	/**
	 * Returns a graph of Integer elements containing n nodes.
	 * Every node is connected with each other by an edge of weight
	 * calculated as a random value.
	 *  
	 * @param n
	 * 			Number of nodes of the graph.
	 * @return
	 * 			A graph with n nodes connected with 
	 * 			each other by an edge of random weight.
	 */
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
	
	/**
	 * Calls the initGraph(n) method and runs the Dijkstra 
	 * algorithm on the resulting graph.
	 * 
	 * @param n
	 * 			Number of nodes of the graph.
	 */
	public static void runDijkstra(long n) {
		initGraph(n).Dijkstra(0);
	}
	
	/**
	 * Calls the initGraph(n) method and runs the Floyd
	 * algorithm on the resulting graph.
	 * 
	 * @param n
	 * 			Number of nodes of the graph.
	 */
	public static void runFloyd(long n) {
		initGraph(n).floyd((int) n);
	}

}
