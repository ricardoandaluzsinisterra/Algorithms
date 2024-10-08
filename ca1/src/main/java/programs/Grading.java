package programs;

public class Grading {
    public static void main(String[] args) {
        int [] grades = new int[10];
        try {
            for (int grade=0; grade<10; grade++){
                grades[grade] = utils.CoreArrayUtilities.getValidInteger("Please enter grade:");
            }
            int howManySubjects = utils.CoreArrayUtilities.repeatedNumber(grades, 70);
            System.out.println("Subjects graded 70 " + howManySubjects);
        } 
        catch (Exception e) {
            System.err.println("The following error was encountered: " + e);
        }  
    }
}
