package hash_tables;

public class HashNode<T> {
	
	// Constants
	public final static int EMPTY = 0;
	public final static int VALID = 1;
	public final static int DELETED = 2;
	
	// Attributes
	private T element;
	private int status;
	
	
	
	// Constructor
	public HashNode() {
		this.status = EMPTY;
	}


	// Getters
	public T getElement() {
		return element;
	}

	public int getStatus() {
		return status;
	}

	
	// Setters
	public void setElement(T element) {
		this.element = element;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
