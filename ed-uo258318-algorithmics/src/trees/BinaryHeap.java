package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> {
	
	// Attributes
	private ArrayList<T> heap;
	
	
	
	// Constructor
	public BinaryHeap() {
		this.heap = new ArrayList<T>();
	}
	
	public BinaryHeap(T[] elements) {
		this.heap = new ArrayList<T>(Arrays.asList(elements));
		for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
			filterDown(i);
		}
	}
	
	
	// Methods
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		return heap.toString();
	}
	
	private void filterUp(int pos) {		
		while (pos != 0) {
			int fatherPos = (pos - 1) / 2;
			T father = heap.get(fatherPos);
			T current = heap.get(pos);
			
			if (father.compareTo(current) < 0) {
				break;
			}
			
			Collections.swap(heap, pos, fatherPos);
			pos = fatherPos;		
		}
	}
	
	private void filterDown(int pos) {
		// While pos is not a leaf
		while (pos < heap.size() / 2) {
			int leftSon = (2 * pos) + 1;
			int rightSon = (2 * pos) + 2;
			
			// Get child with smallest value
			int smallestSon = smallestSon(leftSon, rightSon);
			
			// If value of pos > value of the child
			if (heap.get(pos).compareTo(heap.get(smallestSon)) > 0) {
				Collections.swap(heap, pos, smallestSon);
				pos = smallestSon;
			} else {
				break;
			}
		}
	}
	
	private int smallestSon(int leftSon, int rightSon) {
		// Check if the right son exists
		if (rightSon > heap.size() - 1) {
			return leftSon;
		}
		
		// Compare children
		T lSon = heap.get(leftSon);
		T rSon = heap.get(rightSon);	
		return lSon.compareTo(rSon) < 0 ? leftSon : rightSon;
	}

	public void add(T element) {
		heap.add(element);
		filterUp(heap.size() - 1);
	}
	
	public T getMin() {
		T min = heap.get(0); // Root element
		int lastIndex = heap.size() - 1; // Last element index
		Collections.swap(heap, 0, lastIndex); // We swap their positions
		heap.remove(lastIndex); // We remove the last element (previous root element)
		filterDown(0); // We filter down the new root element
		
		// We return the previous root element
		return min;
	}	

}
