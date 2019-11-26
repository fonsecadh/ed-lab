package hash_tables;

public class HashTable<T> {
	
	// Constants
	protected final static int LINEAR_PROBING = 0;
	protected final static int QUADRATIC_PROBING = 1;
	
	// Attributes
	private int B = 7;
	private int redispersionType = LINEAR_PROBING;
	private double minLF = 0.5;
	
	
	
	// Constructor
	public HashTable(int B, int redispersionType, double minLF) {
		this.B = B;
		this.redispersionType = redispersionType;
		this.minLF = minLF;
	}
	
	protected int f(T element, int i) {
		switch (redispersionType) {
			case LINEAR_PROBING:
				return -1;
			case QUADRATIC_PROBING:
				return -1;
			default:
				return -1;
		}
	}

}
