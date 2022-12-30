import org.junit.*;
import static org.junit.Assert.*;

import java.util.LinkedList;


public class GradeCalculatorTester {
    GradeCalculator test = new GradeCalculator();

    //Note: if trying again, just add the below Assignments to the end of the assignment list to be created in tests.
    Assignment[] oneNormList = {new Assignment("A",10,20)};
    Assignment[] emptyList = new Assignment[0];
    Assignment[] zeroedList = {new Assignment(null,0,0)};
    Assignment[] MaxPoints0List = {new Assignment("B",15,0)};
    Assignment[] NegPointsList = {new Assignment("C",-5,4)};


    Assignment zeroed = new Assignment(null, 0, 0);
    int[] score1 = {0,0};
    Assignment Normal = new Assignment("Prim",10,20);
    int[] score2 = {10,20};
    Assignment MaxPoints0 = new Assignment("",55,0);
    int[] score3 = {55,0};
    boolean failed = false;
    Assignment NegPoints = new Assignment("NegPoints", -5, 5);
    int[] score4 = {-5,5};
    Assignment NegMax;

    /**
     * A basic test for the Assignment class. 
     * Tests the following items:
     *  constructor
     *   - check if maxPoints is negative.
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
        try{
            NegMax = new Assignment("NegPoints", 5, -5);
        }
        catch(IllegalArgumentException e){
            failed = true;
        }
        assertTrue("This test checks if a negative maxPoint in the constructor fails.",failed);
        failed = false;

        assertArrayEquals("This test checks a normal score (achievedPoints is 0)", Normal.getScore(), score2);
        assertArrayEquals("This test checks when maxPoints is 0", MaxPoints0.getScore(), score3);
        assertArrayEquals("This test checks when everything is 0", zeroed.getScore(), score1);
        assertArrayEquals("This test checks when achievedPoints is negative",NegPoints.getScore(),score4);

        assertEquals("This string checks if the constructor gives a null name","No name",zeroed.toString());
        assertEquals("This string checks if constructor gives a valid name","NegPoints",NegPoints.toString());
        assertEquals("This string checks if constructor gives an empty name","",MaxPoints0.toString());
    }

    /**
     * Test for the GradeCalculator.getScore() method)
     * It checks the following cases:
     *  - No assignments
     *  - Just Normal scores
     *  - Just all 0 or null scores
     *  - Totally negative scores.
     *  - all assignments have 0 maxPoints
     *  - assignment has 0 maxPoints and negative achievedPoints
     *  - a combination of all possible assignment types.
     */
    @Test
    public void testMainGetScore(){
        
        LinkedList<Assignment> empty = new LinkedList<>();
        test.assignments = empty;
        assertEquals("Checks if an empty Assignment list returns 0 as required.",0,test.getScore());

        Assignment oneNormalA = new Assignment("A",5,10);
        LinkedList<Assignment> oneNormal = new LinkedList<>();
        oneNormal.add(oneNormalA);
        test.assignments = oneNormal;
        assertEquals("Checks if a single normal element works as intended.",50,test.getScore());

        Assignment zeroedA = new Assignment(null,0,0);
        LinkedList<Assignment> zeroed = new LinkedList<>();
        zeroed.add(zeroedA);
        test.assignments = zeroed;
        assertEquals("Checks if a zeroed assignment returns 0 as required.",0,test.getScore());

        Assignment negOneA =  new Assignment("B",-1,1);
        LinkedList<Assignment> negOne = new LinkedList<>();
        negOne.add(negOneA);
        test.assignments = negOne;

        assertEquals("Checks if a negative achievedPoints returns correctly", -100,test.getScore());

        Assignment negAnd0MaxA = new Assignment("C",-1,0);
        LinkedList<Assignment> negAnd0Max = new LinkedList<>();
        negAnd0Max.add(negAnd0MaxA);
        test.assignments = negAnd0Max;

        assertEquals("Check if a negative achievedPoints and 0'd maxPoints returns correctly", -100, test.getScore());

        Assignment max0A = new Assignment("B", 5, 0);
        LinkedList<Assignment> max0 = new LinkedList<>();
        max0.add(max0A);
        test.assignments = max0;
        assertEquals("check if 0'd maxPoints returns achievedPoints*percentage",500,test.getScore());

        LinkedList<Assignment> allTogether = new LinkedList<>();
        allTogether.add(oneNormalA);
        allTogether.add(zeroedA);
        allTogether.add(negOneA);
        allTogether.add(negAnd0MaxA);
        allTogether.add(max0A);
        test.assignments = allTogether;

        assertEquals("Check if all assignments together won't make the incorrect result.",72,test.getScore());
    }
}
