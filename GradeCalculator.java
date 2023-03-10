import java.util.LinkedList;
import java.util.Scanner;

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

    /**
     * Runs through the list of Assignments to get the total score of gradeCalculator,
     *  represented as a fraction, where the first element is the numerator 
     *  and the second the denominator.
     * @return an array where the first element is the sum of achievedPoints and second is sum of maxPoints.
     */
    @Deprecated
    int[] getScore(){
        int[] result = {0,0};
        int holder[];
        for (int i = 0 ; i < assignments.size() ; i++){
            holder = assignments.get(i).getScore();
            result[0] += holder[0];
            result[1] += holder[1];
        }
        return result;
    }


    /**
     * Runs through the list of Assignments to get the total score of the gradeCalculator
     * @return aggregate score of the course, with 0 as the default. Given as a percentage.
     */
    @Deprecated
    int getPercentage(){
        int totalAchievedScore = 0;
        int totalMaxScore = 0;
        int[] counter;
        for (int i = 0 ; i < assignments.size() ; i++) {
            counter = assignments.get(i).getScore();
            totalAchievedScore += counter[0];
            totalMaxScore += counter[1];
        }
        if(totalMaxScore == 0)
            return totalAchievedScore*percentage;
        return (totalAchievedScore*percentage) / totalMaxScore;
    }

}

/**
 * Basic interface with one method: getScore.
 * Set up to allow the two classes Category and Assignment
 * to act as nodes in a tree, where category is inner nodes and assignment is a leaf.
 */
interface Node {
    int[] getScore();
}

/**
 * A representation of a category, which can hold multiple Assignments.
 */
class Category implements Node {
    String name = "No name";
    int weight;
    int otherWeight = 100;
    LinkedList<Assignment> assignments = new LinkedList<>();

    /**
     * Basic constructor. Only receives the weight and name, and the others are set to normal.
     * @name - intended name for the name field. Set to null for default name.
     * @param weight - intended value for the weight field.
     */
    Category(String name, int weight){
        if(name != null)
            this.name = name;
        this.weight = weight;
    }

    /**
     * Category constructor. Receives and sets name, weight and assignments to the corresponding argument.
     * @throws NullPointerException if assignments is null
     * @param name - intended name of the category. Set to null for default name.
     * @param weight - intended value for the weight field.
     * @param assignments - intended value for the assignment field.
     */
    Category(String name, int weight, LinkedList<Assignment> assignments){
        if(name != null)
            this.name = name;
        this.weight = weight;

        if(assignments == null)
            throw new NullPointerException("The assignments list given is null!");
        this.assignments = assignments;
    }

    /**
     * Runs through all the assignments and adds their achievedScore and maxScores together separately.
     * Then multiplies both with weight to get the weighted score.
     * @return an array with the first element as the weighted achievedScore, and second as the weighted maxScore.
     */
    public int[] getScore(){
        int[] result = {0,0};
        int holder[];
        for (int i = 0 ; i < assignments.size() ; i++){
            holder = assignments.get(i).getScore();
            result[0] += holder[0];
            result[1] += holder[1];
        }
        return result;
    }


    /**
     * Adds an assignment into the assignments field, with a condition.
     * The assignment given cannot be null.
     * @param assignment - intended assignment to add to the assignments field.
     * @return true if the assignment isn't null, and false if it is.
     */
    boolean addAssignment(Assignment assignment){
        if(assignment == null)
            return false;

        assignments.add(assignment);
        return true;
    }

    /**
     * Adds the given assignments to the assignments field, with the condition
     *  that the assignments cannot be null.
     * In the case that assigment is found null, the assignment list ends immediately.
     * @param assignmentList - assignments to add to the assignments field.
     * @return the distance from the end of the list and the last element added.
     */
    int addAssignments(LinkedList<Assignment> assignmentList){
        if(assignmentList == null)
            return 0;

        for(int i = 0 ; i < assignmentList.size() ; i++){
            if( !(this.addAssignment(assignmentList.get(i))) ){
                return assignmentList.size()-i-1;
            }
        }
        return 0;
    }
}

/**
 * A representation of an assignment, with a name and a score.
 * Operates as the most basic Node for the system.
 * Specifically, it acts as a leaf for the tree that the system is built to look like.
 * Note: maxPoints cannot be negative.
 */
class Assignment implements Node {

    String name = "No name";
    int achievedPoints;
    int maxPoints;

    /**
     * Basic constructor for the Assignment class. 
     * Given all three intended values, the constructor simply sets the given values
     * to the corresponding value.
     * @throws IllegalArgumentException if maxPoints is negative.
     * @param name - intended value for name. Give null to leave name as "No name"
     * @param achievedPoints - intended value for achievedPoints
     * @param maxPoints - intended value for maxPoints
     */
    Assignment(String name, int achievedPoints, int maxPoints){
        if(name != null)
            this.name = name;
        if(maxPoints < 0)
            throw new IllegalArgumentException();
        this.achievedPoints = achievedPoints;
        this.maxPoints = maxPoints;
    }

    /**
     * Returns the score in a form of an array,
     *  where the numerator is the first element and the denominator is second.
     * @return array in this order: {Assignment.achievedPoints, Assignment.maxPoints}
     */
    public int[] getScore(){
        int[] result = {this.achievedPoints, this.maxPoints};
        return result;
    }
    
    /**
     * Returns the score percentage of the calling assignment.
     * If given maxPoints is 0, it simply returns achievedPoints.
     * @return score percentage of the assignment.
     */
    @Deprecated
    int getPercentage(){
        if(maxPoints == 0)
            return achievedPoints*GradeCalculator.percentage;
        return achievedPoints*GradeCalculator.percentage/maxPoints;
    }

    public String toString(){
        return name;
    }
}