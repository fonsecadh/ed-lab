package trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyAVLTreeTest {

	@Test
	void testAdd() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');
		assertEquals("b(0)--", a.toString());
		
		a.add('a');
		assertEquals("b(-1)a(0)---", a.toString());
		
		a.add('d');
		assertEquals("b(0)a(0)--d(0)--", a.toString());
		
		a.add('c');
		assertEquals("b(1)a(0)--d(-1)c(0)---", a.toString());
		
		a.add('g');
		assertEquals("b(1)a(0)--d(0)c(0)--g(0)--", a.toString());
		
		a.add('i');
		assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.toString());
		
		a.add('h');
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
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
	
	@Test
	void testRemove() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');		
		a.add('a');		
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
		a.remove('b');
		assertEquals("d(0)a(1)-c(0)--h(0)g(0)--i(0)--", a.toString());
		a.remove('g');
		assertEquals("d(0)a(1)-c(0)--h(1)-i(0)--", a.toString());
	}
	
	@Test
	void testJoins() {		
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('a');		
		a.add('b');		
		a.add('d');
		
		assertEquals("b(0)a(0)--d(0)--", a.toString());
		
		AVLTree<Character> b = new AVLTree<Character>();
		
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		
		assertEquals("g(-1)c(1)-d(0)--i(0)--", b.toString());		
		
		
		assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.joins(b).toString());
	}
	
	@Test
	void testIntersection() {		
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');		
		a.add('a');		
		a.add('d');
		
		AVLTree<Character> b = new AVLTree<Character>();
		
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		
		assertEquals("d(0)--", a.intersection(b).toString());
	}
	
	@Test
	void testBF() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');		
		a.add('a');		
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
	}
	
	@Test
	void testSingleRotation() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('a');		
		a.add('b');		
		a.add('c');
		a.add('d');
		a.add('e');
		
		assertEquals("b(1)a(0)--d(0)c(0)--e(0)--", a.toString());
		
		a.add('f');
		
		assertEquals("d(0)b(0)a(0)--c(0)--e(1)-f(0)--", a.toString());
	}
	
	@Test
	void testDoubleRotation() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('e');		
		a.add('g');		
		a.add('b');
		a.add('d');
		a.add('c');
		
		assertEquals("e(-1)c(0)b(0)--d(0)--g(0)--", a.toString());
	}
	
	@Test
	void testTreeHeight() {
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');		
		a.add('a');		
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals(3, a.getHeight());
	}
	

}
