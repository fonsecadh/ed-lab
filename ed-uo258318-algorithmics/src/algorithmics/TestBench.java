package algorithmics;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TestBench {
	
	// Constants
	public static final int SAMPLES = 3;
	public static final int STARTN = 0;
	public static final int ENDN = 50;
	public static final int SLEEP_TIME = 2;
	public static final String LINEAR = "linear.csv";
	public static final String QUADRATIC = "quadratic.csv";
	public static final String CUBIC = "cubic.csv";
	public static final String LOGARITHMIC = "logarithmic.csv";	

	
	// Methods
	public static void main(String[] args) {
		TestBench.test(LINEAR, STARTN, ENDN);
	}
	
	public static void test(String outputFilename, 
			int startN, int endN) {		
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		try {
			file = new FileWriter(outputFilename);
			pw = new PrintWriter(file);		
			
			calculateAndPrintResult(startN, endN, pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				} 
			} catch (Exception e2) {
				e2.printStackTrace();				
			}
		}
	}

	private static void calculateAndPrintResult(
			int startN, int endN, PrintWriter pw) {
		long startTime;
		long endTime;
		long totalTime;
		
		for (long i = startN; i <= endN; i++) {
			startTime = System.currentTimeMillis();
			Algorithms.linear(i);
			endTime = System.currentTimeMillis();
			
			totalTime = endTime - startTime;
			pw.println(totalTime);
		}
	}
	
	public static void doNothing(long i) {
		System.out.println("Iteration: " + i);
		
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
