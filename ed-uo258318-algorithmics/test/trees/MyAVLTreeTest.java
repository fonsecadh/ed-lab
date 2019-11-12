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
		assertEquals("b(1)a(0)---", a.toString());
		
		a.add('d');
		assertEquals("b(1)a(0)--d(0)--", a.toString());
		
		a.add('c');
		assertEquals("b(2)a(0)--d(1)c(0)---", a.toString());
		
		a.add('g');
		assertEquals("b(2)a(0)--d(1)c(0)--g(0)--", a.toString());
		
		a.add('i');
		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", a.toString());
		
		a.add('h');
		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
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
		
		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		a.remove('b');
		assertEquals("a(4)-d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		a.remove('g');
		assertEquals("a(3)-d(2)c(0)--i(1)h(0)---", a.toString());
	}
	
	@Test
	void testJoins() {		
		AVLTree<Character> a = new AVLTree<Character>();
		
		a.add('b');		
		a.add('a');		
		a.add('d');
		
		AVLTree<Character> b = new AVLTree<Character>();
		
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		
		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", a.joins(b).toString());
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
		
		assertEquals("b(3)a(0)--d(2)c(0)--g(2)-i(-1)h(0)---", a.toString());
	}
	

}
