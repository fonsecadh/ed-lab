package hash_tables;

import java.util.ArrayList;

public class HashTable<T> {
	
	// Constants
	protected final static int LINEAR_PROBING = 0;
	protected final static int QUADRATIC_PROBING = 1;
	
	// Attributes
	private int B = 7;
	private int redispersionType = LINEAR_PROBING;
	private double minLF = 0.5;
	private ArrayList<HashNode<T>> associativeArray;
	private double n; // Elements in the Hash Table
	
	
	
	// Constructor	
	public HashTable(int B, int redispersionType, double minLF) {
		if (isPrime(B) 
				&& (redispersionType == LINEAR_PROBING || redispersionType == QUADRATIC_PROBING) 
				&& minLF > 0
				&& B > 0) { // Input validation
			this.B = B;
			this.redispersionType = redispersionType;
			this.minLF = minLF;	
		}
		
		// Initialize and fill array list
		this.associativeArray = new ArrayList<HashNode<T>>();
		for (int i = 0; i < B; i++) {
			this.associativeArray.add(new HashNode<T>());
		}
	}
	

	// Methods
	protected int f(T element, int i) {
		switch (redispersionType) {
			case LINEAR_PROBING:
				return linearProbing(element, i);
			case QUADRATIC_PROBING:
				return quadraticProbing(element, i);
			default:
				return linearProbing(element, i);
		}
	}

	private int linearProbing(T element, int i) {
		return (element.hashCode() + i) % B;
	}

	private int quadraticProbing(T element, int i) {
		return (int) ((element.hashCode() + Math.pow(i, 2)) % B);
	}	
	
	public boolean isPrime(int n) {
		// Base cases		
		if (n < 2) {
			return false;
		}
		
		if (n < 4) {
			return true;
		}
		
		// We check this in order to skip the middle five numbers
		// between iterations in the next for-loop.
		if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}
		
		int maxValue = (int) Math.sqrt(n);
		for (int i = 5; i <= maxValue; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		
		return true;		
	}
	
	public int getPrevPrimeNumber(int n) {
		boolean found = false;
		int prime = n;
		while (!found) {
			if (isPrime(--prime)) {
				found = true;
			}
		}
		
		return prime;
	}
	
	public int getNextPrimeNumber(int n) {
		// Base case
		if (n < 2) {
			return 2;
		}
		
		boolean found = false;
		int prime = n;
		while (!found) {
			if (isPrime(++prime)) {
				found = true;
			}
		}
		
		return prime;
	}
	
	public double getLF() {
		return n / B;
	}
	
	public void add(T element) {
		if (search(element)) { // If element already exists
			return;
		}
		
		boolean added = false;
		int it = 0;
		while (!added && it < B) {
			HashNode<T> target = associativeArray.get(f(element, it));
			if (target.getStatus() == HashNode.EMPTY 
					|| target.getStatus() == HashNode.DELETED) {
				target.setElement(element);
				target.setStatus(HashNode.VALID);
				added = true;
				this.n++;
			}
			it++;
		}
	}
	
	public boolean search(T element) {
		boolean found = false;
		int it = 0;
		while (!found && it < B) {
			HashNode<T> target = associativeArray.get(f(element, it));
			if (target.getStatus() == HashNode.VALID) {
				if (target.getElement().equals(element)) {
					found = true;
				}
			}
			it++;
		}
		return found;
	}
	
	public void remove(T element) {
		if (!search(element)) { // If element does not exist
			return;
		}
		
		boolean removed = false;
		int it = 0;
		while (!removed && it < B) {
			HashNode<T> target = associativeArray.get(f(element, it));
			if (target.getStatus() == HashNode.VALID) {
				if (target.getElement().equals(element)) {
					target.setStatus(HashNode.DELETED);
					removed = true;
					this.n--;
				}				
			}
			it++;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < associativeArray.size(); i++) {
			sb.append("[" + i + "] ");
			sb.append("(" + associativeArray.get(i).getStatus() + ")");
			if (associativeArray.get(i).getStatus() == HashNode.EMPTY) {
				sb.append(" = null - ");
			} else {
				sb.append(" = " + associativeArray.get(i).getElement().toString() + " - ");
			}
		}
		return sb.toString();
	}
	
	public void print() {
		System.out.println(toString());
	}
	
}
