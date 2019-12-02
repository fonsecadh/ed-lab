package hash_tables;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyHashTableTest {

	@Test
	void testF() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);		
		assertEquals(2, a.f(7, 0));
		assertEquals(3, a.f(7, 1));
		assertEquals(4, a.f(7, 2));
		assertEquals(0, a.f(7, 3));
		
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 0.5);		
		assertEquals(2, b.f(7, 0));
		assertEquals(3, b.f(7, 1));
		assertEquals(1, b.f(7, 2));
		assertEquals(1, b.f(7, 3));		
		
		HashTable<Character> c = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);		
		assertEquals(0, c.f('A', 0));
		assertEquals(1, c.f('A', 1));
		assertEquals(2, c.f('A', 2));
		assertEquals(3, c.f('A', 3));
		
		HashTable<Character> d = new HashTable<Character>(5, HashTable.QUADRATIC_PROBING, 0.5);		
		assertEquals(0, d.f('A', 0));
		assertEquals(1, d.f('A', 1));
		assertEquals(4, d.f('A', 2));
		assertEquals(4, d.f('A', 3));
	}
	
	@Test
	void testAdd() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);		
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		a.remove(24);
		assertEquals("[0] (2) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(24));
		assertEquals(false, a.search(7));
		assertEquals(false, a.search(23));
		a.add(15);
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(true, a.search(15));
		assertEquals(false, a.search(24));
		assertEquals(false, a.search(7));
		assertEquals(false, a.search(23));
		
		
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);		
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		assertEquals("[0] (1) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(true, b.search(24));
		assertEquals(false, b.search(7));
		assertEquals(false, b.search(23));
		b.remove(24);
		assertEquals("[0] (2) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(24));
		assertEquals(false, b.search(7));
		assertEquals(false, b.search(23));
		b.add(15);
		assertEquals("[0] (1) = 15 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(true, b.search(15));
		assertEquals(false, b.search(24));
		assertEquals(false, b.search(7));
		assertEquals(false, b.search(23));
		
	}
	
	@Test
	void testRemove() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);		
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(true, a.search(24));
		assertEquals(false, a.search(7));
		assertEquals(false, a.search(23));
		
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);		
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		assertEquals("[0] (1) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(true, b.search(24));
		assertEquals(false, b.search(7));
		assertEquals(false, b.search(23));
	}
	
	@Test
	void testIsPrime() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 0.5);		
		assertEquals(true, a.isPrime(2));
		assertEquals(true, a.isPrime(3));
		assertEquals(true, a.isPrime(5));
		assertEquals(true, a.isPrime(347));
		assertEquals(true, a.isPrime(1427));
		
		assertEquals(false, a.isPrime(222));
		assertEquals(false, a.isPrime(0));
		assertEquals(false, a.isPrime(4));
		assertEquals(false, a.isPrime(1426));
		
		assertEquals(2, a.getPrevPrimeNumber(3));
		assertEquals(5, a.getNextPrimeNumber(3));
		
		assertEquals(1129, a.getPrevPrimeNumber(1151));
		assertEquals(1153, a.getNextPrimeNumber(1151));
		
	}

}
