package trees;

public class AVLNode<T> {
	
	// Attributes
	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	
	
	
	// Constructors
	public AVLNode(T element) {
		this.element = element;
	}
	
	public AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}
	
	
	// Getters
	public T getElement() {
		return element;
	}
	
	public AVLNode<T> getLeft() {
		return left;
	}
	
	public AVLNode<T> getRight() {
		return right;
	}
		
	
	// Setters
	public void setElement(T element) {
		this.element = element;
	}
	
	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}
	
	public void setRight(AVLNode<T> right) {
		this.right = right;
	}
	
	
	// ToString and Print
	@Override
	public String toString() {
		return getElement().toString();
	}
	
	public void print() {
		System.out.println(toString());
	}

}
