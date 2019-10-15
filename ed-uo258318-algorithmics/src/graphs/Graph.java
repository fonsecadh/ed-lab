package graphs;

import java.util.ArrayList;
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
	
	
	// Methods
	
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
	 * returns a string with the visited nodes in order.
	 * 
	 * @param element
	 * 			Element of the starting node.
	 * @return
	 * 			A string with the visited nodes in order.
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
	 */
	public void initDijkstra(T departure) {
		// TODO: initDijkstra
	}

	/**
	 * Computes Dijkstra's Algorithm.
	 * 
	 * @param departure
	 * 			The departure node.
	 */
	public void Dijkstra(T departure) {
		// TODO: Dijkstra
	}

	public void print() {
		// TODO: print		
	}

}
