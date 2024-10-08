package programs;

public class MostFrequentGrade {
    public static void main(String[] args) {
        int [] grades = new int[10];
        try {
            for (int grade=0; grade<10; grade++){
                grades[grade] = utils.CoreArrayUtilities.getValidInteger("Please enter grade: ");
            }
            int highestFrequency = utils.CoreArrayUtilities.highestFrequency(grades);
            System.out.println("Most frequent occurring grade: " + highestFrequency);
        } 
        catch (Exception e) {
            System.err.println("The following error was encountered:" + e);
        }  
    }
}
