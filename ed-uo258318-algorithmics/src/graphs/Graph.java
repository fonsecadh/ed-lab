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
	 * Infinite cost for traveling from one node to the other.
	 */
	public static final double INFINITE = Double.POSITIVE_INFINITY;
	
	/**
	 * For the Floyd's P vector
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
	 * Double matrix representing the weigths of the graph.
	 */
	protected double[][] weights;
	
	/**
	 * Maximum size of the graph.
	 */
	private int maxSize;
	
	
	
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
		sb.append(node.toString());
		
		// For each accessible node that has not been visited
		nodes
			.parallelStream()
			.filter(n -> n.isVisited() == false)
			.collect(Collectors.toList())
			.forEach(candidate -> sb.append("-" + DFPrint(getNode(candidate.getElement()))));
		
		// We return the string
		return sb.toString();		
	}


	public void floyd(int size) {
		// TODO Auto-generated method stub
		
	}

	public int[][] getP() {
		// TODO Auto-generated method stub
		return null;
	}

	public double[][] getA() {
		// TODO Auto-generated method stub
		return null;
	}

}
