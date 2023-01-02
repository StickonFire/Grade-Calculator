import org.junit.*;
import static org.junit.Assert.*;

import java.util.LinkedList;


public class GradeCalculatorTester {
    Category test = new Category(null,0);


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
     * Tests the Category.getScore method)
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
    public void testCategoryGetScore(){

        LinkedList<Assignment> empty = new LinkedList<>();

        Assignment oneNormalA = new Assignment("A",5,10);
        LinkedList<Assignment> oneNormal = new LinkedList<>();
        oneNormal.add(oneNormalA);
    
        Assignment zeroedA = new Assignment(null,0,0);
        LinkedList<Assignment> zeroedList = new LinkedList<>();
        zeroedList.add(zeroedA);
    
        Assignment negOneA =  new Assignment("B",-1,1);
        LinkedList<Assignment> negOne = new LinkedList<>();
        negOne.add(negOneA);
    
        Assignment negAnd0MaxA = new Assignment("C",-1,0);
        LinkedList<Assignment> negAnd0Max = new LinkedList<>();
        negAnd0Max.add(negAnd0MaxA);
    
        Assignment max0A = new Assignment("B", 5, 0);
        LinkedList<Assignment> max0 = new LinkedList<>();
        max0.add(max0A);
    
        LinkedList<Assignment> allTogether = new LinkedList<>();
        allTogether.add(oneNormalA);
        allTogether.add(zeroedA);
        allTogether.add(negOneA);
        allTogether.add(negAnd0MaxA);
        allTogether.add(max0A);

        int[] exp1 = {0,0};
        int[] exp2 = {5,10};
        int[] exp3 = {0,0};
        int[] exp4 = {-1,1};
        int[] exp5 = {-1,0};
        int[] exp6 = {5,0};
        int[] exp7 = {8,11};

        test.assignments = empty;
        assertArrayEquals("Checks if an empty Assignment list returns 0 as required.",exp1,test.getScore());

        test.assignments = oneNormal;
        assertArrayEquals("Checks if an empty Assignment list returns 0 as required.",exp2,test.getScore());

        test.assignments = zeroedList;
        assertArrayEquals("Checks if an empty Assignment list returns 0 as required.",exp3,test.getScore());

        test.assignments = negOne;
        assertArrayEquals("Checks if an empty Assignment list returns 0 as required.",exp4,test.getScore());

        test.assignments = negAnd0Max;
        assertArrayEquals("Checks if an empty Assignment list returns 0 as required.",exp5,test.getScore());
        
        test.assignments = max0;
        assertArrayEquals("Checks if an empty Assignment list returns 0 as required.",exp6,test.getScore());

        test.assignments = allTogether;
        assertArrayEquals("Checks if an empty Assignment list returns 0 as required.",exp7,test.getScore());
        
    }

    /**
     * Tests the constructors for category. It checks the following instances:
     *  - normal small constructor
     *  - normal wide constructor
     *  - normal wide constructor, null assignments
     *  - normal wide constructor, empty assignment list
     *  - small constructor, null name
     *  - wide constructor, null name
     *  - negative weight (should be treated as normal)
     */
    @Test
    public void testCategoryConstructors(){
        Category cat1 = new Category("A",30);

        assertEquals("This checks if a normal 2 argument constructor has the right name", cat1.name, "A");
        assertEquals("This checks if a normal 2 argument constructor has the right weight", cat1.weight, 30);
        assertEquals("This checks if a normal 2 argument constructor has the right assignments", cat1.assignments, new LinkedList<Assignment>());

        Category newcat = new Category(null, 75);
        
        assertEquals("This checks if a normal 2 argument constructor with null name has the right name", newcat.name, "No name");
        assertEquals("This checks if a normal 2 argument constructor with null name has the right weight", newcat.weight, 75);
        assertEquals("This checks if a normal 2 argument constructor with null name has the right assignments", newcat.assignments, new LinkedList<Assignment>());

        boolean failed = false;
        try{
            Category cat2 = new Category("B",50,null);
        }
        catch (NullPointerException e){
            failed = true;
        }

        assertTrue("This checks if a null assignments argument causes an exception", failed);

        Category cat3 = new Category("A",45, new LinkedList<Assignment>());

        assertEquals("This checks if a normal 3 argument constructor with an empty list has the right name", cat3.name, "A");
        assertEquals("This checks if a normal 3 argument constructor with an empty list has the right weight", cat3.weight, 45);
        assertEquals("This checks if a normal 3 argument constructor with an empty list has the right assignments", cat3.assignments, new LinkedList<Assignment>());

        LinkedList<Assignment> ex4 = new LinkedList<>();
        ex4.add(new Assignment(null, 95, 100));
        Category cat4 = new Category("None",46, ex4);

        assertEquals("This checks if a normal 3 argument constructor has the right name", cat4.name, "None");
        assertEquals("This checks if a normal 3 argument constructor has the right weight", cat4.weight, 46);
        assertEquals("This checks if a 3 argument constructor has the right assignments.", cat4.assignments.get(0), new Assignment(null, 95, 100));
    }
}
