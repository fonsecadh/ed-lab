package graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algorithmics.Algorithms;

class L2_Algorithms_and_GraphNode_EvalTest {

	@Test
    public void factorial()
    {
        long n;
        long result;
 
        n = 8;
        result = 40320L;
 
        assertEquals(result, Algorithms.factorial(n));
        assertEquals(result, Algorithms.factorialRec(n));
 
        n = 9;
        result = 362880L;
 
        assertEquals(result, Algorithms.factorial(n));
        assertEquals(result, Algorithms.factorialRec(n));
    }
 
    @Test
    public void pow()
    {
        long n;
        long result;
 
        n = 8;
        result = 256L;
 
        assertEquals(result, Algorithms.pow(n));
        assertEquals(result, Algorithms.powRec1(n));
        assertEquals(result, Algorithms.powRec2(n));
        assertEquals(result, Algorithms.powRec3(n));
        assertEquals(result, Algorithms.powRec4(n));
 
        n = 9;
        result = 512L;
 
        assertEquals(result, Algorithms.pow(n));
        assertEquals(result, Algorithms.powRec1(n));
        assertEquals(result, Algorithms.powRec2(n));
        assertEquals(result, Algorithms.powRec3(n));
        assertEquals(result, Algorithms.powRec4(n));
    }
 
    @Test
    public void graphNode()
    {
        GraphNode<Integer> integerGN = new GraphNode<Integer>(new Integer(4));
 
        assertEquals(new Integer(4), integerGN.getElement());
        assertEquals(false, integerGN.isVisited());
        assertEquals("GN(N:4/V:false)", integerGN.toString());
        integerGN.setVisited(true);
        assertEquals(new Integer(4), integerGN.getElement());
        assertEquals(true, integerGN.isVisited());
        assertEquals("GN(N:4/V:true)", integerGN.toString());
        integerGN.setElement(new Integer(1234));
        assertEquals(new Integer(1234), integerGN.getElement());
        assertEquals(true, integerGN.isVisited());
        assertEquals("GN(N:1234/V:true)", integerGN.toString());
 
        GraphNode<String> stringGN = new GraphNode<String>(new String("Hey there!"));
 
        assertEquals(new String("Hey there!"), stringGN.getElement());
        assertEquals(false, stringGN.isVisited());
        assertEquals("GN(N:Hey there!/V:false)", stringGN.toString());
        stringGN.setVisited(true);
        assertEquals(new String("Hey there!"), stringGN.getElement());
        assertEquals(true, stringGN.isVisited());
        assertEquals("GN(N:Hey there!/V:true)", stringGN.toString());
        stringGN.setElement(new String("Hi!"));
        assertEquals(new String("Hi!"), stringGN.getElement());
        assertEquals(true, stringGN.isVisited());
        assertEquals("GN(N:Hi!/V:true)", stringGN.toString());
 
        GraphNode<Character> charGN = new GraphNode<Character>(new Character('x'));
 
        assertEquals(new Character('x'), charGN.getElement());
        assertEquals(false, charGN.isVisited());
        assertEquals("GN(N:x/V:false)", charGN.toString());
        charGN.setVisited(true);
        assertEquals(new Character('x'), charGN.getElement());
        assertEquals(true, charGN.isVisited());
        assertEquals("GN(N:x/V:true)", charGN.toString());
        charGN.setElement(new Character('$'));
        assertEquals(new Character('$'), charGN.getElement());
        assertEquals(true, charGN.isVisited());
        assertEquals("GN(N:$/V:true)", charGN.toString());
 
  }


}
