package trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyBinaryHeapTest {

	@Test
	void testAdd() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		a.add(10);
		a.add(9);
		a.add(8);
		assertTrue(a.toString().equals("[8, 10, 9]"));
		a.add(7);
		assertTrue(a.toString().equals("[7, 8, 9, 10]"));
		a.add(6);
		assertTrue(a.toString().equals("[6, 7, 9, 10, 8]"));
		a.add(5);
		assertTrue(a.toString().equals("[5, 7, 6, 10, 8, 9]"));
		a.add(4);
		assertTrue(a.toString().equals("[4, 7, 5, 10, 8, 9, 6]"));
		
		BinaryHeap<Character> b = new BinaryHeap<Character>();
		b.add('f');
		b.add('g');
		b.add('a');
		b.add('z');
		b.add('d');
		assertEquals(b.toString(), "[a, d, f, z, g]");
		
//		BinaryHeap<Integer> c = new BinaryHeap<Integer>();
//		c.add(10);
//		c.add(9);
//		c.add(8);
//		c.add(7);
//		c.add(6);
//		c.add(5);
//		c.add(4);
//		c.add(3);
//		c.add(2);
//		c.add(1);
//		assertEquals(c.toString(), "[1, 2, 4, 3, 6, 5, 8, 10, 7, 9]");
	}
	
	@Test
	void testRemove() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();		
		a.add(9);
		a.add(8);
		a.add(7);
		a.add(6);
		a.add(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);		
		assertTrue(a.toString().equals("[1, 3, 2, 4, 7, 8, 5, 9, 6]"));
		assertEquals(1, (int) a.getMin());
		assertTrue(a.toString().equals("[2, 3, 5, 4, 7, 8, 6, 9]"));
		
		
		BinaryHeap<Character> b = new BinaryHeap<Character>();
		b.add('f');
		b.add('g');
		b.add('a');
		b.add('z');
		b.add('d');
		assertEquals(b.toString(), "[a, d, f, z, g]");
		assertEquals('a', (char) b.getMin());
		assertEquals(b.toString(), "[d, g, f, z]");
	}
	
	@Test
	void testConstructorWithArgs() {
//		Integer[] aux = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1	};
//		BinaryHeap<Integer> a = new BinaryHeap<Integer>(aux);		
//		assertEquals(a.toString(), "[1, 2, 4, 3, 6, 5, 8, 10, 7, 9]");
		
		Integer[] auxB = { 9, 8, 7, 6, 5, 1, 2, 3, 4 };
		BinaryHeap<Integer> b = new BinaryHeap<Integer>(auxB);
		assertEquals(b.toString(), "[1, 3, 2, 4, 7, 8, 5, 9, 6]");
		
		Character[] auxC = { 'f', 'g', 'a', 'z', 'd' };
		BinaryHeap<Character> c = new BinaryHeap<Character>(auxC);
		assertEquals(c.toString(), "[a, d, f, z, g]");
	}

}
