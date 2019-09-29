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
	
	// Complexity: O(n)
	public static long factorial (long n) {
		long result = 1;
		
		for (long i = n; i > 0; i--) {
			TestBench.doNothing(i);
			result *= i;
		}
		
		return result;
	}
	
	// Complexity: ?
	public static long factorialRec (long n) {
		TestBench.doNothing(n);
		
		if (n <= 1) {
			return 1;
		}
		
		return n * factorialRec(n - 1);
	}
	
	// Complexity: ?
	public static long pow(long n) {
		long result = 1;
		
		for (long i = 0; i < n; i++) {
			TestBench.doNothing(i);
			result *= 2;
		}
		
		return result;		
	}
	
	// Complexity: ?
	public static long powRec1(long n) {
		TestBench.doNothing(n);
		
		if (n == 0) {
			return 1;
		}
		
		return powRec1(n - 1) + powRec1(n - 1);
	}
	
	// Complexity: ?
	public static long powRec2(long n) {
		TestBench.doNothing(n);
		
		if (n == 0) {
			return 1;
		}
		
		return 2 * powRec2(n - 1);
	}
	
	// Complexity: ?
	public static long powRec3(long n) {
		TestBench.doNothing(n);
		
		if (n == 0) {
			return 1;
		}
		
		long subres = powRec3(n / 2);
		long result = subres * subres;
		
		if (n % 2 != 0) {
			result *= 2;
		}
		
		return result;		
	}
	
	// Complexity: ?
	public static long powRec4(long n) {
		TestBench.doNothing(n);
		
		if (n == 0) {
			return 1;
		}
		
		long subres = powRec4(n / 2);
		long result = subres * subres;
		
		if (n % 2 != 0) {
			result *= 2;
		}
		
		return result;		
	}

}
