package graphs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyGraphTest {
	
	Graph<Integer> graph = new Graph<Integer>(8);
	
	@BeforeEach
	public void setup() {
		try {
			graph.addNode(1);
			graph.addNode(2);
			graph.addNode(3);
			graph.addNode(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			graph.addEdge(1, 4, 2.0);
			graph.addEdge(2, 4, 3.0);
			graph.addEdge(1, 3, 4.0);
			graph.addEdge(2, 3, 5.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDrainNodes() {		
		assertEquals(false, graph.isDrainNode(1));
		assertEquals(false, graph.isDrainNode(2));
		assertEquals(true, graph.isDrainNode(3));
		assertEquals(true, graph.isDrainNode(4));			
		assertEquals(2, graph.countDrainNodes());
		
		try {
			graph.addNode(5);
			graph.addEdge(1, 5, 6.0);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(true, graph.isDrainNode(5));		
		assertEquals(3, graph.countDrainNodes());
	}
	
	@Test
	void testSourceNodes() {
		assertEquals(true, graph.isSourceNode(1));
		assertEquals(true, graph.isSourceNode(2));
		assertEquals(false, graph.isSourceNode(3));
		assertEquals(false, graph.isSourceNode(4));	
		assertEquals(2, graph.countSourceNodes());
		
		try {
			graph.addNode(5);
			graph.addEdge(5, 4, 6.0);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(true, graph.isSourceNode(5));		
		assertEquals(3, graph.countSourceNodes());
	}

}
