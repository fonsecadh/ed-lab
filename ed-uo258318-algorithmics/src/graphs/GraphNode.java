package graphs;

public class GraphNode<T> {
	
	// Attributes
	private T element;
	private boolean visited;
	
	
	
	// Constructors
	public GraphNode() {
		// Empty constructor
	}
	
	public GraphNode(T element) {
		setElement(element);
	}
	

	// Getters
	public T getElement() {
		return element;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	
	// Setters
	public void setElement(T element) {
		this.element = element;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}	
	
	
	// Methods
	public String toString() {
		return "GN(N:" + element + "/V:" + visited + ")";
	}
	
	public void print() {
		System.out.println(this.toString());
	}
	

}
