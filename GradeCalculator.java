import java.util.LinkedList;

public class GradeCalculator {

    static String className = "No name";
    static int percentage = 100;
    LinkedList<Assignment> assignments = new LinkedList<>();

    /**
     * Basic main function of GradeCalculator, and deals with getting the algorithm
     *  to work. Its parameters include a name for the class.
     * @param args - When something is written with
     */
    public static void main(String args[]){
        //Deals with the name
        if(args.length != 0){
            className = args[args.length-1];
            for(int i = args.length-2 ; i >= 0 ; i--){
                className = args[i] + " " + className;
            }
        }


    }

}

/**
 * A representation of an assignment, with a name and a score.
 * Operates as the most basic Node for the system.
 * Specifically, it acts as a leaf for the tree that the system is built to look like.
 */
class Assignment {

    String name = "No name";
    int achievedPoints;
    int maxPoints;

    /**
     * Basic constructor for the Assignment class. 
     * Given all three intended values, the constructor simply sets the given values
     * to the corresponding value
     * @param name - intended value for name. Give null to leave name as "No name"
     * @param achievedPoints - intended value for achievedPoints
     * @param maxPoints - intended value for maxPoints
     */
    Assignment(String name, int achievedPoints, int maxPoints){
        if(name != null)
            this.name = name;
        this.achievedPoints = achievedPoints;
        this.maxPoints = maxPoints;
    }
    
    /**
     * Returns the score percentage of the calling assignment.
     * If given maxPoints is 0, it simply returns achievedPoints.
     * @return score percentage of the assignment.
     */
    int getScore(){
        if(maxPoints == 0)
            return achievedPoints*GradeCalculator.percentage;
        return achievedPoints*GradeCalculator.percentage/maxPoints;
    }

    public String toString(){
        return name;
    }
}