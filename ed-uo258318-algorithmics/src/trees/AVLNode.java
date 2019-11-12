package trees;

public class AVLNode<T> {

	// Attributes
	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	private int height;

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

	public int getHeight() {
		return height;
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

	public void setHeight(int height) {
		this.height = height;
	}

	// ToString and Print
	@Override
	public String toString() {
		return getElement().toString() + "(" + getBF() + ")";
	}

	public void print() {
		System.out.println(toString());
	}

	// Methods
	public void updateHeight() {
		setHeight((left == null) ? ((right == null) ? 0 : right.getHeight() + 1)
				: ((right == null) ? left.getHeight() + 1 : Math.max(left.getHeight(), right.getHeight()) + 1));
	}

	private int getBF() {
		return right == null ? (left == null ? 0 : 0 - left.getHeight() - 1)
				: (left == null ? right.getHeight() + 1 : right.getHeight() - left.getHeight());
	}	

}
