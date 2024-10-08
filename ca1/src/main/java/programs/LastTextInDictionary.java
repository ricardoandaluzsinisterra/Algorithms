package programs;

import java.util.Scanner;

public class LastTextInDictionary {
    public static void main(String[] args) {
        String [] texts = new String[10];
        var con = System.console();
        Scanner sc = new Scanner(con.reader());
        System.out.println("Please enter your 10 pieces of text: ");
        try {
            for (int i=0; i<10; i++){
                texts[i] = sc.nextLine();
            }
            String lastText = utils.CoreArrayUtilities.lastInAlphabet(texts);
            System.out.println("The last text that would appear in the dictionary is: " + lastText);
        } 
        catch (Exception e) {
            System.err.println("The following error was encountered: " + e);
        }  
        sc.close();
    }
}
