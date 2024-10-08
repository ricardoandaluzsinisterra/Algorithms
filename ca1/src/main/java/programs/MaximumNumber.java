package programs;

public class MaximumNumber {
    public static void main(String[] args) {
        int [] grades = new int[10];
        try {
            for (int grade=0; grade<10; grade++){
                grades[grade] = utils.CoreArrayUtilities.getValidInteger("Please enter grade");
            }
            int highestGrade = utils.CoreArrayUtilities.findMax(grades);
            System.out.println("The highest grade is: " + highestGrade);
        } 
        catch (Exception e) {
            System.err.println("The following error was encountered: " + e);
        }  
    }
}
