package programs;

public class AboveGpa {
    public static void main(String[] args) {
        int [] grades = new int[10];
        try {
            for (int grade=0; grade<10; grade++){
                grades[grade] = utils.CoreArrayUtilities.getValidInteger("Please enter grade: ");
            }
            int count = utils.CoreArrayUtilities.countGreater(grades);
            System.out.println("Scored higher than gpa: " + count);
        } 
        catch (Exception e) {
            System.err.println("The following error was encountered: " + e);
        }  
    }
}
