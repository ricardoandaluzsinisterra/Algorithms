package programs;

public class GradePointAverage {
    public static void main(String[] args) {
        int [] grades = new int[10];
        try {
            for (int grade=0; grade<10; grade++){
                grades[grade] = utils.CoreArrayUtilities.getValidInteger("Please enter grade");
            }
            double gpa =utils.CoreArrayUtilities.arrayAverage(grades);
            System.out.println("The grade point average is: " + gpa);
        } catch (Exception e) {
            System.err.println("The following error has been encountered: " + e);
        }
    }
}
