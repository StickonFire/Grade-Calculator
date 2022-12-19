public class GradeCalculator {

    static String className = "No name";

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