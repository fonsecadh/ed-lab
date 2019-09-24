package algorithmics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlgorithmsTest {

	@Test
	void testFactorial() {
		assertEquals(720, Algorithms.factorial(6));		
	}
	
	@Test
	void testRecursiveFactorial() {		
		assertEquals(720, Algorithms.factorialRec(6));
	}
	
	@Test
	void testPow() {		
		assertEquals(16, Algorithms.pow(4));
	}
	
	@Test
	void testRecursive1Pow() {		
		assertEquals(16, Algorithms.powRec1(4));
	}
	
	@Test
	void testRecursive2Pow() {		
		assertEquals(16, Algorithms.powRec2(4));
	}
	
	@Test
	void testRecursive3Pow() {		
		assertEquals(16, Algorithms.powRec3(4));
	}
	
	@Test
	void testRecursive4Pow() {		
		assertEquals(16, Algorithms.powRec4(4));
	}

}
