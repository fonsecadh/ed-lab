package graphs;

/**
 * Represents a node from a Graph data structure.
 * 
 * @author Hugo Fonseca Díaz (UO258318)
 *
 * @param <T>
 * 			The type of the graph node.
 */
public class GraphNode<T> {
	
	// Attributes
	
	/**
	 * The element of the graph node.
	 */
	private T element;
	
	/**
	 * Represents if the node has been visited or not.
	 */
	private boolean visited;

	/**
	 * Represents if the node is visible or not.
	 */
	private boolean visible;
	
	
	
	// Constructors
	
	/**
	 * Creates a GraphNode without initializing any element.
	 */
	public GraphNode() {
		// Empty constructor
	}
	
	/**
	 * Creates a GraphNode given its element.
	 * 
	 * @param element
	 * 			The element of the graph node.
	 */
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
	
	public boolean isVisible() {
		return visible;
	}
	
	
	// Setters
	public void setElement(T element) {
		this.element = element;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}	

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	// Methods
	public String toString() {
		return "GN(N:" + element + "/V:" + visited + ")";
	}
	
	public void print() {
		System.out.println(this.toString());
	}
	

}
