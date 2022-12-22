public class GradeCalculatorTester {
    Assignment zeroed = new Assignment(null, 0, 0);
    Assignment Normal = new Assignment("Prim",10,20);
    Assignment MaxPoints0 = new Assignment("",55,0);
    Assignment NegPoints = new Assignment("NegPoints", -5, 5);

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
    void testAssignment(){
        assertEquals("This test checks a normal score (achievedPoints is 0)", Normal.getScore(), 50);
        assertEquals("This test checks when maxPoints is 0", MaxPoints0.getScore(), 5500);
        assertEquals("This test checks when everything is 0", zeroed.getScore(), 0);
        assertEquals("This test checks when achievedPoints is negative",NegPoints.getScore(),-100);
    }
}
