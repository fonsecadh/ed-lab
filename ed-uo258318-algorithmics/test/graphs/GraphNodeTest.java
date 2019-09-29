package graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphNodeTest {
	
	GraphNode<Character> nodeA;
	GraphNode<Character> nodeB;

	@BeforeEach
	public void setup() {
		nodeA = new GraphNode<Character>();
		nodeA.setElement('a');
		nodeA.setVisited(true);
		nodeA.print();
		
		nodeB = new GraphNode<Character>();
		nodeB.setElement('b');
		nodeB.print();
	}
	
	
	@Test
	void testToString() {
		assertEquals("GN(N:a/V:true)", nodeA.toString());
		assertEquals("GN(N:b/V:false)", nodeB.toString());
	}

}
