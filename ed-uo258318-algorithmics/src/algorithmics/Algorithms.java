package algorithmics;

public class Algorithms {
	
	// Complexity: O(n)
	public static void linear(long n) {
		for (long i = 0; i <= n; i++) {
			TestBench.doNothing(i);
		}
	}	
	
	// Complexity: O(n^2)
	public static void quadratic(long n) {
		for (long i = 0; i <= n; i++) {
			Algorithms.linear(n);
		}
	}	
	
	// Complexity: O(n^3)
	public static void cubic(long n) {
		for (long i = 0; i <= n; i++) {
			Algorithms.quadratic(n);
		}
	}
	
	// Complexity: O(log n)
	public static void logarithmic(long n) {
		for (long i = n; i > 0; i/=2) {
			TestBench.doNothing(i);
		}
	}

}
