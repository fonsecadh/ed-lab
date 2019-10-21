package graphs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GraphNodeTest.class, L4_Graph_EvalTest.class, L4_Graph_sampleTest.class, L5_Floyd_EvalTest_2.class,
		L6_Graph_Dijkstra_sampleTest.class, L6B_Exercises_sampleTest.class })
public class AllTests {

}
