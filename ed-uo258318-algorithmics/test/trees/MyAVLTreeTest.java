package trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyAVLTreeTest {

	@Test
	void testAdd() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');
		assertEquals("b--", a.toString());
		
		a.add('a');
		assertEquals("ba---", a.toString());
		
		a.add('d');
		assertEquals("ba--d--", a.toString());
		
		a.add('c');
		assertEquals("ba--dc---", a.toString());
		
		a.add('g');
		assertEquals("ba--dc--g--", a.toString());
		
		a.add('i');
		assertEquals("ba--dc--g-i--", a.toString());
		
		a.add('h');
		assertEquals("ba--dc--g-ih---", a.toString());
	}
	
	@Test
	void testSearch() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');		
		a.add('a');		
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals(true, a.search('i'));
		assertEquals(false, a.search('f'));
	}
	
	@Test
	void testMax() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');		
		a.add('a');		
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals('i', a.getMax(a.getRoot()));
	}

}
