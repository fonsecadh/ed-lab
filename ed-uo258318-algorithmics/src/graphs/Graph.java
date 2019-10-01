package graphs;

import java.util.ArrayList;

public class Graph<T> {
	
	// Constants
	public static final int INDEX_NOT_FOUND = -1;
	
	// Attributes
	ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weights;
	private int maxSize;
	
	
	
	// Constructor
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

	public double[][] getWeight() {
		return weights;
	}

	
	// Setters
	public void setNodes(ArrayList<GraphNode<T>> nodes) {
		this.nodes = nodes;
	}

	public void setEdges(boolean[][] edges) {
		this.edges = edges;
	}

	public void setWeight(double[][] weight) {
		this.weights = weight;
	}
	
	
	// Methods
	public int getNode(T element) {
		return nodes.indexOf(element);
	}

	public int getSize() {
		return nodes.size();
	}

	public void addNode(T element) throws Exception {
		if (getNode(element) != INDEX_NOT_FOUND) {
			throw new Exception("Node already exists.");
		}
		
		if (getSize() == maxSize) {
			throw new Exception("Graph is at its maximum capacity.");
		}
		
		nodes.add(new GraphNode<T>(element));
	}	

	public boolean existsEdge(T origin, T dest) throws Exception {
		int oriIndex = getNode(origin);
		int destIndex = getNode(dest);
		
		if (oriIndex == INDEX_NOT_FOUND || destIndex == INDEX_NOT_FOUND) {
			throw new Exception("Some of the given nodes do not exist.");
		}
		
		return edges[oriIndex][destIndex];
	}

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

	public void removeNode(T element) throws Exception {
		int eleIndex = getNode(element);
		
		if (eleIndex == INDEX_NOT_FOUND) {
			throw new Exception("Node does not exist.");
		}
		
		nodes.remove(eleIndex);
		
		weights[eleIndex][eleIndex] = 0;
		edges[eleIndex][eleIndex] = false;
	}
	
	public void removeEdge(T origin, T dest) throws Exception {
		if (!existsEdge(origin, dest)) {
			throw new Exception("The edge does not exist.");
		}
		
		int oriIndex = getNode(origin);
		int destIndex = getNode(dest);
		
		weights[oriIndex][destIndex] = 0;
		edges[oriIndex][destIndex] = false;
	}
	

}
