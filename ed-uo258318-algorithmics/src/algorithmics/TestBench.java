package algorithmics;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class TestBench {

	// Constants
	public static final int SAMPLES = 3;
	public static final int STARTN = 1;
	public static final int ENDN = 50;
	public static final int ENDN_CUBIC = 20;	
	public static final int SLEEP_TIME = 2;
	
	public static final String LINEAR = "linear.csv";
	public static final String QUADRATIC = "quadratic.csv";
	public static final String CUBIC = "cubic.csv";
	public static final String LOGARITHMIC = "logarithmic.csv";
	
	public static final String CLASSNAME = "algorithmics.Algorithms";
	

	// Methods
	public static void main(String[] args) {
		TestBench.test(LINEAR, SAMPLES, STARTN, ENDN, "linear");
		TestBench.test(QUADRATIC, SAMPLES, STARTN, ENDN, "quadratic");
		TestBench.test(CUBIC, SAMPLES, STARTN, ENDN_CUBIC, "cubic");
		TestBench.test(LOGARITHMIC, SAMPLES, STARTN, ENDN, "logarithmic");		
	}

	public static void test(String outputFilename, 
			int samples, int startN, int endN, 
			String methodName) {

		FileWriter file = null;
		PrintWriter pw = null;

		try {
			file = new FileWriter(outputFilename);
			pw = new PrintWriter(file);

			calculateAndPrintResult(samples, startN, endN, pw, 
					methodName);

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
			int samples, int startN, int endN, PrintWriter pw, 
			String methodName) throws Exception {
		long startTime;
		long endTime;
		long totalTime;

		for (long j = startN; j <= endN; j++) {
			totalTime = 0;
			
			for (long i = 0; i < samples; i++) {
				startTime = System.currentTimeMillis();
				testAlgorithm(CLASSNAME, methodName, endN);
				endTime = System.currentTimeMillis();

				totalTime += endTime - startTime;
			}

			pw.println(totalTime / samples);
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
	
	public static void testAlgorithm (
			String className, String methodName, long n) 
					throws Exception {
		// We infer the class by means of its name
		Class<?> theClass = Class.forName(className);
		
		// We retrieve the method object
		Method method = theClass.getMethod(methodName, Long.TYPE); 
		
		// We invoke the method
		method.invoke(null, n);		
	}

}
