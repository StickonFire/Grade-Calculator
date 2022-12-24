import org.junit.*;
import static org.junit.Assert.*;


public class GradeCalculatorTester {
    Assignment zeroed = new Assignment(null, 0, 0);
    int[] score1 = {0,0};
    Assignment Normal = new Assignment("Prim",10,20);
    int[] score2 = {10,20};
    Assignment MaxPoints0 = new Assignment("",55,0);
    int[] score3 = {55,0};
    Assignment NegPoints = new Assignment("NegPoints", -5, 5);
    int[] score4 = {-5,5};

    /**
     * A basic test for the Assignment class. 
     * Tests the following items:
     *  getScore()
     *   - check if maxPoints is 0
     *   - check a normal
     *   - check if everything is 0
     *   - check if points are negative
     *  getString()
     *   - check if name is null, should cause "No name"
     *   - empty name
     */
    @Test
    public void testAssignment(){
        assertArrayEquals("This test checks a normal score (achievedPoints is 0)", Normal.getScore(), score2);
        assertArrayEquals("This test checks when maxPoints is 0", MaxPoints0.getScore(), score3);
        assertArrayEquals("This test checks when everything is 0", zeroed.getScore(), score1);
        assertArrayEquals("This test checks when achievedPoints is negative",NegPoints.getScore(),score4);

        assertEquals("This string checks if the constructor gives a null name","No name",zeroed.toString());
        assertEquals("This string checks if constructor gives a valid name","NegPoints",NegPoints.toString());
        assertEquals("This string checks if constructor gives an empty name","",MaxPoints0.toString());
    }
}
