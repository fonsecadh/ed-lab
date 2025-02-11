package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Graph data structure.
 * 
 * @author Hugo Fonseca Díaz (UO258318)
 *
 * @param <T>
 * 			The type of the graph.
 */
public class Graph<T> {
	
	// Constants
	
	/**
	 * When the index of a node is not found in the graph.
	 */
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * Infinite cost.
	 */
	public static final double INFINITE = Double.POSITIVE_INFINITY;
	
	/**
	 * For the Floyd's Pathway matrix.
	 */
	public static final int EMPTY = -1;
	
	
	// Attributes
	
	/**
	 * List containing the nodes of the graph.
	 */
	ArrayList<GraphNode<T>> nodes;
	
	/**
	 * Boolean matrix representing the edges of the graph.
	 */
	protected boolean[][] edges;
	
	/**
	 * Double matrix representing the weights of the graph.
	 */
	protected double[][] weights;
	
	/**
	 * Maximum size of the graph.
	 */
	private int maxSize;
	
	/**
	 * Cost matrix.
	 */
	protected double[][] costMatrixA;
	
	/**
	 * Pathway matrix.
	 */
	protected int[][] pathwayMatrixP;	
	
	/**
	 * Dijkstra's D vector.
	 */
	private double[] D;
	
	/**
	 * Dijkstra's pathway vector.
	 */
	private int[] PD;
	
	
	// Constructor
	
	/**
	 * Creates a graph given its maximum size.
	 * 
	 * @param n
	 * 			Maximum size of nodes of the graph.
	 */
	public Graph(int n) {
		this.maxSize = n;
		this.edges = new boolean[n][n];
		this.weights = new double[n][n];
		this.nodes = new ArrayList<GraphNode<T>>();
		
		
		// Floyd's Algorithm
		
		// We reserve memory for A and P
		this.costMatrixA = new double[n][n];
		this.pathwayMatrixP = new int[n][n];
		
		
		// Dijkstra's Algorithm
		
		// We reserve memory for D and PD
		this.D = new double[n];
		this.PD = new int[n];		
	}

	
	// Getters
	public ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}

	public boolean[][] getEdges() {
		return edges;
	}

	public double[][] getWeights() {
		return weights;
	}

	/**
	 * Returns the cost matrix A.
	 * 
	 * @return
	 * 			The cost matrix A.
	 */
	public double[][] getA() {
		return costMatrixA;
	}

	/**
	 * Returns the pathway matrix P.
	 * 
	 * @return
	 * 			The pathway matrix P.
	 */
	public int[][] getP() {
		return pathwayMatrixP;
	}
	
	/**
	 * Returns the Dijkstra's D vector.
	 * 
	 * @returns
	 * 			Dijkstra's D vector.
	 */
	public double[][] getD() {
		double[][] aux = new double[1][D.length];
		for (int i = 0; i < D.length; i ++) {
			aux[0][i] = D[i];
		}
		return aux;		
	}
	
	/**
	 * Returns the Dijkstra's pathway vector.
	 * 
	 * @return
	 * 			Dijkstra's pathway vector.
	 */
	public int[] getPD() {
		return PD;
	}

	
	// Setters
	public void setNodes(ArrayList<GraphNode<T>> nodes) {
		this.nodes = nodes;
	}

	public void setEdges(boolean[][] edges) {
		this.edges = edges;
	}

	public void setWeights(double[][] weight) {
		this.weights = weight;
	}
	
	
	// Print

	/**
	 * Prints the nodes of the graph, as well as the 
	 * edges and weights matrices.
	 */
	public void print() {		
		System.out.println(toString());
	}
	
	
	// Methods
	
	@Override
	/**
	 * Returns a string with the nodes of the graph, as well 
	 * as the edges and weights matrices.
	 */
	public String toString() {
		StringBuilder info = new StringBuilder();
		int currentSize = getSize();
		
		// Nodes of the graph
		info.append("----------------Nodes----------------");
		nodes.forEach(n -> info.append("\n" + n.toString()));
		info.append("\n\n");
		
		// Edges of the graph
		info.append("----------------Edges----------------\n");
		for (int i = 0; i < currentSize; i++) {
			for (int j = 0; j < currentSize; j++) {
				info.append(edges[i][j] + " ");
			}
			info.append("\n");
		}
		info.append("\n");
		
		// Weights of the graph
		info.append("----------------Weights----------------\n");
		for (int i = 0; i < currentSize; i++) {
			for (int j = 0; j < currentSize; j++) {
				info.append(weights[i][j] + " ");
			}
			info.append("\n");
		}
		info.append("\n");
		
		return info.toString();
	}


	/**
	 * Returns the position in the graph for a 
	 * given node element.
	 * 
	 * @param element
	 * 			The given node element.
	 * @return
	 * 			An integer representing the position of the node in the graph.
	 */
	public int getNode(T element) {
		List<GraphNode<T>> filteredNodes = nodes
				.stream()
				.filter(n -> n.getElement().equals(element))
				.collect(Collectors.toList());
		
		if (filteredNodes.size() == 0) {
			return INDEX_NOT_FOUND;
		}
		
		return nodes.indexOf(filteredNodes.get(0)); 
	}

	/**
	 * Returns the current size of the graph.
	 * 
	 * @return
	 * 			The current size of the graph.
	 */
	public int getSize() {
		return nodes.size();
	}

	/**
	 * Adds a node to the graph given an element.
	 * 
	 * @param element
	 * 			The element of the new node.
	 * @throws Exception
	 * 			If the node already exists or if the graph is at its maximum capacity.
	 */
	public void addNode(T element) throws Exception {		
		if (getNode(element) != INDEX_NOT_FOUND) {
			throw new Exception("Node already exists.");
		}
		
		if (getSize() == maxSize) {
			throw new Exception("Graph is at its maximum capacity.");
		}
		
		nodes.add(new GraphNode<T>(element));
		
		int currentSize = getSize() - 1;
		
		// We initialize the vectors for the new node
		for (int i = 0; i < currentSize; i++) {
			edges[currentSize][i] = false;
			edges[i][currentSize] = false;
			weights[currentSize][i] = 0.0;
			weights[i][currentSize] = 0.0;
		}
	}	

	/**
	 * Checks whether there exists an edge between two nodes or not.
	 * 
	 * @param origin
	 * 			The origin node.
	 * @param dest
	 * 			The destination node.
	 * @return
	 * 			True if an edge exists. False otherwise.
	 * @throws Exception
	 * 			If some of the given nodes do not exist.
	 */
	public boolean existsEdge(T origin, T dest) throws Exception {
		int oriIndex = getNode(origin);
		int destIndex = getNode(dest);
		
		if (oriIndex == INDEX_NOT_FOUND || destIndex == INDEX_NOT_FOUND) {
			throw new Exception("Some of the given nodes do not exist.");
		}
		
		return edges[oriIndex][destIndex];
	}

	/**
	 * Adds an edge between two nodes and specifies its weight.
	 * 
	 * @param origin
	 * 			The origin node.
	 * @param dest
	 * 			The destination node.
	 * @param weight
	 * 			The weight of the edge.
	 * @throws Exception
	 * 			If the weight is lower or equal to zero.
	 */
	public void addEdge(T origin, T dest, double weight) throws Exception {
		if (weight < 1) {
			throw new Exception("Weight cannot be lower or equal to zero.");
		}
		
		if (existsEdge(origin, dest)) {
			throw new Exception("Edge already exists.");
		}
		
		int oriIndex = getNode(origin);
		int destIndex = getNode(dest);
		
		weights[oriIndex][destIndex] = weight;
		edges[oriIndex][destIndex] = true;
		
	}

	/**
	 * Removes a node given its element.
	 * 
	 * @param element
	 * 			The element of the node to be removed.
	 * @throws Exception
	 * 			If the node does not exist.
	 */
	public void removeNode(T element) throws Exception {
		int eleIndex = getNode(element);
		
		if (eleIndex == INDEX_NOT_FOUND) {
			throw new Exception("Node does not exist.");
		}
		
		// We get the last index in the matrices
		int lastIndex = getSize() - 1;
		
		// Replacing elements from the vectors of last node to the removed node
		for (int i = 0; i < lastIndex; i++) {
			edges[i][eleIndex] = edges[i][lastIndex];
			edges[eleIndex][i] = edges[lastIndex][i];
			weights[eleIndex][i] = weights[lastIndex][i];
			weights[i][eleIndex] = weights[i][lastIndex];
		}		
		
		// Replacing the diagonal vector elements
		weights[eleIndex][eleIndex] = weights[lastIndex][lastIndex];
		edges[eleIndex][eleIndex] = edges[lastIndex][lastIndex];
		
		// We update the list
		nodes.set(eleIndex, nodes.get(lastIndex));
		nodes.remove(lastIndex);
	}
	
	/**
	 * Removes the edge between two nodes.
	 * 
	 * @param origin
	 * 			The origin node.
	 * @param dest
	 * 			The destination node.
	 * @throws Exception
	 * 			If the edge does not exist.
	 */
	public void removeEdge(T origin, T dest) throws Exception {
		if (!existsEdge(origin, dest)) {
			throw new Exception("The edge does not exist.");
		}
		
		int oriIndex = getNode(origin);
		int destIndex = getNode(dest);
		
		weights[oriIndex][destIndex] = 0;
		edges[oriIndex][destIndex] = false;
	}	
	
	/**
	 * Checks whether the given node is a drain node or not.
	 * 
	 * A drain node is a node which only has incoming edges.
	 * 
	 * @param element
	 * 			The element identifying the node.
	 * @return
	 * 			Whether the given node is a drain node or not.
	 */
	public boolean isDrainNode(T element) {
		int currentSize = getSize();
		int pos = getNode(element);
		int countIncomingEdges = 0;
		
		for (int i = 0; i < currentSize; i++) {
			if (edges[pos][i] == true) {
				return false;
			}
			if (edges[i][pos]) {
				countIncomingEdges++;
			}
		}	
		
		if (countIncomingEdges == 0) {
			return false;
		}
		
		return true;		
	}
	
	/**
	 * Checks whether the given node is a source node or not.
	 * 
	 * A source node is a node which only has outgoing edges.
	 * 
	 * @param element
	 * 			The element identifying the node.
	 * @return
	 * 			Whether the given node is a source node or not.
	 */
	public boolean isSourceNode(T element) {
		int currentSize = getSize();
		int pos = getNode(element);
		int countOutgoingEdges = 0;
		
		for (int i = 0; i < currentSize; i++) {
			if (edges[i][pos] == true) {
				return false;
			}
			if (edges[pos][i] == true) {
				countOutgoingEdges++;
			}
		}
		
		if (countOutgoingEdges == 0) {
			return false;
		}
		
		return true;		
	}
	
	/**
	 * Returns the number of drain nodes of the graph.
	 * 
	 * @return
	 * 			The number of drain nodes of the graph.
	 */
	public int countDrainNodes() {
		return nodes
				.parallelStream()
				.filter(node -> isDrainNode(node.getElement()))
				.collect(Collectors.toList()).size();	
	}
	
	/**
	 * Returns the number of source nodes of the graph.
	 * 
	 * @return
	 * 			The number of source nodes of the graph.
	 */
	public int countSourceNodes() {
		return nodes
				.parallelStream()
				.filter(node -> isSourceNode(node.getElement()))
				.collect(Collectors.toList()).size();			
	}


	
	// Advanced Methods
	
	/**
	 * Traverses the graph using a Depth-First Search and then
	 * returns a string with the visited nodes in Depth-First order.
	 * 
	 * @param element
	 * 			Element of the starting node.
	 * @return
	 * 			A string with the visited nodes in that order.
	 */
	public String traverseGraphDF(T element) {
		// We set all visited flags to false
		nodes.forEach(node -> node.setVisited(false));
		
		// We get the starting node
		int startNode = getNode(element);	
		
		// We check if it exists
		if (startNode == INDEX_NOT_FOUND) {
			return null;
		}
		
		return DFPrint(startNode);
	}

	private String DFPrint(int startNode) {
		GraphNode<T> node = nodes.get(startNode);
		
		// Set visited flag to true
		node.setVisited(true);
		
		// We create the string builder
		StringBuilder sb = new StringBuilder();
		
		// We append the information of the node
		sb.append(node.getElement().toString() + "-");
		
		// For each accessible node that has not been visited
		nodes
			.parallelStream()
			.filter(n -> n.isVisited() == false)
			.filter(fn -> edges[startNode][getNode(fn.getElement())])
			.collect(Collectors.toList())
			.forEach(candidate -> {
				if (!candidate.isVisited()) {
					sb.append(DFPrint(getNode(candidate.getElement())));
				}
			});
		
		// We return the string
		return sb.toString();		
	}
	
	/**
	 * Reserves memory for A and P.
	 * 
	 * Copies weight over A (placing INFINITE whenever the 
	 * related slot in Edges is false.
	 * 
	 * Fills P with EMPTY values.
	 * 
	 * Sets the cost of going from one node to itself to 0.
	 */
	protected void initsFloyd() {
		// We iterate the matrices
		int currentSize = getSize();
		
		for (int i = 0; i < currentSize; i++) {
			for (int j = 0; j < currentSize; j++) {
				// We copy the weights over A
				if (edges[i][j] == false) {
					costMatrixA[i][j] = INFINITE;
				} else {
					costMatrixA[i][j] = weights[i][j];					
				}
				
				// We fill P with EMPTY values
				pathwayMatrixP[i][j] = EMPTY;
				
				// We set the cost of going from one node
				// to itself to 0
				costMatrixA[i][i] = 0;
			}
		}
	}
	
	/**
	 * Computes the Floyd's cost matrix A up to the An 
	 * iteration.
	 * 
	 * @param an
	 * 			The An iteration 			
	 */
	public void floyd(int an) {
		initsFloyd(); // We call the Auxiliary method
		int currentSize = getSize();
		
		// Floyd's Algorithm
		for (int k = 0; k < an; k++) {
			for (int i = 0; i < currentSize; i++) {
				for (int j = 0; j < currentSize; j++) {
					// If the cost of going from any node i 
					// to any other node j through k is lower 
					// than the cost recorded so far
					if (costMatrixA[i][k] + costMatrixA[k][j] < costMatrixA[i][j]) {
						// We update the cost matrix A
						costMatrixA[i][j] = costMatrixA[i][k] + costMatrixA[k][j];
						
						// We update the pathway matrix P
						pathwayMatrixP[i][j] = k;
					}
				}
			}
		}
	}
	
	/**
	 * Prints the Floyd's pathway from a departure node 
	 * to an arrival node.
	 * 
	 * @param departure
	 * 			The departure node.
	 * @param arrival
	 * 			The arrival node.
	 * @return
	 * 			The Floyd's pathway.
	 * @throws Exception
	 * 			If some of the parameters are not valid or do not exist.
	 */
	public String printFloydPath(T departure, T arrival) 
			throws Exception {
		// We get the index of the nodes
		int indexDepart = getNode(departure);
		int indexArrival = getNode(arrival);
		
		// We check that the nodes exist
		if (indexDepart == INDEX_NOT_FOUND || indexArrival == INDEX_NOT_FOUND) {
			throw new Exception("Some of the parameters do not exist.");
		}
		
		return printFloydPathAux(departure, arrival);
	}

	private String printFloydPathAux(T departure, T arrival) throws Exception {
		// We get the index of the nodes
		int indexDepart = getNode(departure);
		int indexArrival = getNode(arrival);
		
		int k = pathwayMatrixP[indexDepart][indexArrival];
		
		if (k < 0) {
			return "";
		}
		
		// We create the resulting string
		StringBuilder sb = new StringBuilder();
		
		sb.append(printFloydPathAux(departure, nodes.get(k).getElement()));
		sb.append(nodes.get(k).getElement().toString());
		sb.append(printFloydPathAux(nodes.get(k).getElement(), arrival));	
		
		return sb.toString();
	}
	
	/**
	 * Verifies that the departure element exists.
	 * 
	 * Initializes the D and PD structures.
	 * 
	 * Initializes the S set.
	 * 
	 * Sets the visible flag of the departure node 
	 * to true.
	 * 
	 * @param departure
	 * 			The departure node.
	 * @throws Exception 
	 * 			If the departure node does not exist.
	 */
	public void initDijkstra(T departure) throws Exception {
		int indexDep = getNode(departure);
		
		// Input validation
		if (indexDep == INDEX_NOT_FOUND) {
			throw new Exception("Departure node does not exist");
		}
		
		int currentSize = getSize();
		
		// We initialize the D and PD structures
		for (int i = 0; i < currentSize; i++) {
			// PD
			if (edges[indexDep][i] == true) {
				PD[i] = indexDep;
			} else {
				PD[i] = EMPTY;
			}			
			
			// D
			if (weights[indexDep][i] == 0.0 || edges[indexDep][i] == false) {
				D[i] = INFINITE;
			} else {
				D[i] = weights[indexDep][i];
			}
			
		}
		
		// We initialize the S set		
		nodes.forEach(n -> n.setVisible(false));
		nodes.get(indexDep).setVisible(true);
	}

	/**
	 * Computes Dijkstra's Algorithm.
	 * 
	 * @param departure
	 * 			The departure node.
	 */
	public void Dijkstra(T departure) {		
		try {			
			// Dijkstra's initialization
			initDijkstra(departure); 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 		
		
		int currentSize = getSize();
		
		// N-1 iterations
		for (int p = 1; p < currentSize; p++) {	
			// We select the pivot
			GraphNode<T> pivotW = pivotSelectionLinear();
			
			// We check that a pivot was selected
			if (pivotW != null) {
				// We get the selected pivot w
				GraphNode<T> selectedW = pivotW;
				
				// We add the pivot to the S set
				nodes.get(getNode(selectedW.getElement())).setVisible(true);
				
				// For each node m in V-S update costs 
				nodes
					.stream()
					.filter(n -> !n.isVisible())
					.collect(Collectors.toList())
					.forEach(m -> {
						int wIndex = getNode(selectedW.getElement());
						int mIndex = getNode(m.getElement());
						if (D[wIndex] + weights[wIndex][mIndex] < D[mIndex]
								&& edges[wIndex][mIndex] == true) {
							// We update the costs
							D[mIndex] = D[wIndex] + weights[wIndex][mIndex];
							PD[mIndex] = wIndex;
						}
					});		
			}				
		}
	}

	@SuppressWarnings("unused")
	private GraphNode<T> pivotSelectionQuadratic() {
		// S set
		List<GraphNode<T>> s = nodes
			.stream()
			.filter(n -> n.isVisible())
			.collect(Collectors.toList());
		
		// V-S set
		List<GraphNode<T>> vMinusS = nodes
			.stream()
			.filter(n -> !n.isVisible())
			.collect(Collectors.toList());
		
		// Evaluate cost of every edge {k, w} where k is member 
		// of the S set and w is member of V-S.				
		double minCost = INFINITE;
		GraphNode<T> pivotW = null;
		
		for (int i = 0; i < s.size(); i++) {
			GraphNode<T> k = s.get(i);
			for (int j = 0; j < vMinusS.size(); j++) {
				GraphNode<T> w = vMinusS.get(j);
				
				int kIndex = getNode(k.getElement());
				int wIndex = getNode(w.getElement());
				
				if (edges[kIndex][wIndex] == true) {
					if (weights[kIndex][wIndex] < minCost) {
						// Select the edge of minimum cost, adding w to the S set.
						// w is the node with the lowest cost in D.		
						minCost = weights[kIndex][wIndex];
						pivotW = w;
					}
				}
			}
		}
		
		return pivotW;
	}
	
	private GraphNode<T> pivotSelectionLinear() {
		double minCost = INFINITE;
		GraphNode<T> pivotW = null;
		
		// We iterate the D vector and get the 
		// pivot w with minimum cost.
		for (int i = 0; i < getSize(); i++) {
			if (D[i] < minCost && !nodes.get(i).isVisible()) {
				minCost = D[i];
				pivotW = nodes.get(i);				
			}
		}
		
		return pivotW;
	}
	
	/**
	 * Traverses the graph using a Breath-First Search and then
	 * returns a string with the visited nodes in that order.
	 * 
	 * @param element
	 * 			Element of the starting node.
	 * @return
	 * 			A string with the visited nodes in Breath-First order.
	 */
	public String BFPrint(T element) {
		// We set all visited flags to false
		nodes.forEach(node -> node.setVisited(false));
		
		// We get the starting node
		int startNode = getNode(element);	
		
		// We check if it exists
		if (startNode == INDEX_NOT_FOUND) {
			return null;
		}
		
		return BFPrintAux(startNode);
	}

	private String BFPrintAux(int startNode) {
		GraphNode<T> node = nodes.get(startNode);
		
		// Set visited flag to true
		node.setVisited(true);
		
		// We create the string builder
		StringBuilder sb = new StringBuilder();
		
		// We create a FIFO data structure
		LinkedList<GraphNode<T>> fifo = new LinkedList<GraphNode<T>>();
		
		// We add the starting node
		fifo.add(node);
		
		while (!fifo.isEmpty()) {			
			// We append the information of the node
			sb.append(fifo.getFirst().getElement().toString() + "-");
			
			// For each node reachable from that element
			// (And not already visited)
			nodes
				.stream()
				.filter(n -> n.isVisited() == false)
				.filter(fn -> edges[getNode(fifo.getFirst().getElement())][getNode(fn.getElement())])
				.collect(Collectors.toList())
				.forEach(candidate -> {
					candidate.setVisited(true);
					fifo.add(candidate);
				});
			
			// We remove the node
			fifo.removeFirst();
		}
		
		
		// We return the string
		return sb.toString();		
	}
	
	/**
	 * Returns the center of the graph, that is, the node 
	 * closest to the farthest node. In other words, the node 
	 * with minimum eccentricity.
	 *  
	 * @return
	 * 			The element of the center of the graph.
	 */
	public T getCenter() {
		// Execute Floyd's algorithm
		floyd(getSize());
		
		// Minimum eccentricity
		double minEccentricity = INFINITE;
		
		// Index of the center of the graph
		int indexCenter = -1;
		
		// Obtain the node with minimum eccentricity
		// (Minimum of the maximum values of each 
		// column in the A matrix)
		for (int i = 0; i < costMatrixA[0].length; i++) { // Cols
			double eccentricity = 0;			
			for (int j = 0; j < costMatrixA.length; j++) { // Rows
				if (costMatrixA[j][i] > eccentricity) {
					eccentricity = costMatrixA[j][i];
				}
			}
			
			if (eccentricity < minEccentricity) {
				// Update minimum eccentricity
				minEccentricity = eccentricity;
				
				// Update the index of the center
				indexCenter = i;
			}
		}
		
		// We return the center of the graph
		return nodes.get(indexCenter).getElement();
	}	
	
	/**
	 * Returns the shortest path length between two nodes.
	 * 
	 * @param origin
	 * 			The origin node.
	 * @param destination
	 * 			The destination node.
	 * @return
	 * 			The shortest path length between the given nodes.
	 * @throws Exception
	 * 			If any of the nodes do not exist.
	 */
	public int shortestPathLength(T origin, T destination) {
		// We get the index of the nodes
		int indexOrigin = getNode(origin);
		int indexDestination = getNode(destination);
		
		// We check that the nodes exist
		if (indexOrigin == INDEX_NOT_FOUND || indexDestination == INDEX_NOT_FOUND) {
			return -1;
		}
		
		// We execute the modified version of Floyd's algorithm
		// for getting the shortest path between two nodes
		modifiedFloydShortestPath(getSize());
		
		// We get the shortest path between the origin and the 
		// destination
		return (int) costMatrixA[indexOrigin][indexDestination];
	}
	
	private void initsModifiedFloydShortestPath() {
		// We iterate the matrices
		int currentSize = getSize();
		
		for (int i = 0; i < currentSize; i++) {
			for (int j = 0; j < currentSize; j++) {
				// We represent the lengths over A
				if (edges[i][j] == false) {
					costMatrixA[i][j] = INFINITE;
				} else {
					costMatrixA[i][j] = 1.0;					
				}
				
				// We fill P with EMPTY values
				pathwayMatrixP[i][j] = EMPTY;
				
				// We set the length of going from one node
				// to itself to 0
				costMatrixA[i][i] = 0;
			}
		}
	}
	
	private void modifiedFloydShortestPath(int an) {
		initsModifiedFloydShortestPath(); // We call the Auxiliary method
		int currentSize = getSize();
		
		// Modified Floyd's Algorithm for getting the shortest path
		// between two nodes
		for (int k = 0; k < an; k++) {
			for (int i = 0; i < currentSize; i++) {
				for (int j = 0; j < currentSize; j++) {
					// If the length of going from any node i 
					// to any other node j through k is lower 
					// than the length recorded so far
					if (costMatrixA[i][k] + costMatrixA[k][j] < costMatrixA[i][j]) {
						// We update the cost matrix A
						costMatrixA[i][j] = costMatrixA[i][k] + costMatrixA[k][j];
						
						// We update the pathway matrix P
						pathwayMatrixP[i][j] = k;
					}
				}
			}
		}
	}

}
