package algorithmics;

public class Algorithms {
	
	// Complexity: O(n)
	public static void linear(long n) {
		for (long i = 0; i <= n; i++) {
			TestBench.doNothing(i);
		}
	}	

}
