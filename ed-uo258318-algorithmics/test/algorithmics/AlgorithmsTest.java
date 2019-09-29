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
		assertEquals(512, Algorithms.pow(9));
	}
	
	@Test
	void testRecursivePow1() {		
		assertEquals(16, Algorithms.powRec1(4));
		assertEquals(512, Algorithms.powRec1(9));
	}
	
	@Test
	void testRecursivePow2() {		
		assertEquals(16, Algorithms.powRec2(4));
		assertEquals(512, Algorithms.powRec2(9));
	}
	
	@Test
	void testRecursivePow3() {		
		assertEquals(16, Algorithms.powRec3(4));
		assertEquals(512, Algorithms.powRec3(9));
	}
	
	@Test
	void testRecursivePow4() {		
		assertEquals(16, Algorithms.powRec4(4));
		assertEquals(512, Algorithms.powRec4(9));
	}

}
