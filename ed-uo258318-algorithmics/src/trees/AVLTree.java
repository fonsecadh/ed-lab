package trees;

public class AVLTree<T extends Comparable<T>> {
	
	// Attributes
	private AVLNode<T> root;

	

	// Getters
	public AVLNode<T> getRoot() {
		return root;
	}

	
	// Setters
	public void setRoot(AVLNode<T> root) {
		this.root = root;
	}

	
	// ToString
	@Override
	public String toString() {
		return toStringAux(root);		
	}
	
	private String toStringAux(AVLNode<T> currentNode) {
		StringBuilder sb = new StringBuilder();
		
		if (currentNode != null) {
			sb.append(currentNode.toString());
			sb.append(toStringAux(currentNode.getLeft()));
			sb.append(toStringAux(currentNode.getRight()));
		} else {
			sb.append("-");
		}
		
		return sb.toString();
	}
	
	
	// Methods	
	public void add(T element) {
		setRoot(add(root, element));
	}
	
	private AVLNode<T> add(AVLNode<T> currentNode, T element) {
		if (currentNode == null) {
			return new AVLNode<T>(element);
		}
		
		if (element.compareTo(currentNode.getElement()) == 0) {
			throw new IllegalArgumentException("Node already exists.");
		}
		
		if (element.compareTo(currentNode.getElement()) < 0) {
			currentNode.setLeft(add(currentNode.getLeft(), element));
		}
		
		if (element.compareTo(currentNode.getElement()) > 0) {
			currentNode.setRight(add(currentNode.getRight(), element));
		}
		
		currentNode.updateHeight();
		return currentNode;
	}
	
	public boolean search(T element) {
		return searchAux(root, element);
	}

	private boolean searchAux(AVLNode<T> currentNode, T element) {
		if (currentNode == null) {
			return false;
		}
		
		if (currentNode.getElement().equals(element)) {
			return true;
		} 
		
		return searchAux(currentNode.getLeft(), element) || searchAux(currentNode.getRight(), element);
	}
	
	protected T getMax(AVLNode<T> root) {
		if (root.getRight() == null) {
			return root.getElement();
		} else {			
			return getMax(root.getRight());
		}
	}
		
	public void remove (T element) {
		setRoot(removeAux(getRoot(), element));
	}

	private AVLNode<T> removeAux(AVLNode<T> currentNode, T element) {
		if (currentNode == null) {
			return null;
		}
		
		if (element.compareTo(currentNode.getElement()) == 0) {			
			return (currentNode.getLeft() == null) ? ((currentNode.getRight() == null) ? null : currentNode.getRight()) 
					: ((currentNode.getRight() == null) ? currentNode.getLeft() 
							: replaceAux(currentNode, getMax(currentNode.getLeft())));
		}
		
		if (element.compareTo(currentNode.getElement()) < 0) {
			currentNode.setLeft(removeAux(currentNode.getLeft(), element));
		}
		
		if (element.compareTo(currentNode.getElement()) > 0) {
			currentNode.setRight(removeAux(currentNode.getRight(), element));
		} 
		
		currentNode.updateHeight();
		return currentNode;
	}

	private AVLNode<T> replaceAux(AVLNode<T> currentNode, T max) {
		currentNode.setElement(max);
		currentNode.setLeft(removeAux(currentNode.getLeft(), max));
		return currentNode;
	}
	
}
