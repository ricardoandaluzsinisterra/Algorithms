
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class SumAverage {
    public static void main(String[] args) {
        boolean flag = true;
        List<Double> numbers =new LinkedList<>();
        Scanner keyboard = new Scanner(System.in);
        double sum = 0.00;
        
        while (flag) { 
            System.out.println("Please enter number: \n(type -1 to stop the program and get the average)");
            try {
                double input = keyboard.nextDouble();
                if (input == -1){
                    for(double number: numbers){
                        sum += number;
                    }
                    int size = numbers.size();
                    double average = sum/size;
                    System.out.println("Your average is:" + average);
                    flag = false;
                }
                else{
                    numbers.add(input);
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again");
                keyboard.next();
            }
        }
        keyboard.close();
    }
}