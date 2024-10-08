package utils;

import java.util.Scanner;

public class CoreArrayUtilities {
    public static void main(String[] args) {
    }

    /**
     * Method that takes in the param numArray which would be an int array, validates it and iterates through it
     * to then print every one of its elements.
     * @param numArray array of integers.
     */
    public static void displayNumericArray(int [] numArray){
        validateArray(numArray);
        int positionNumber = 0;
        for (int number : numArray){
            positionNumber += 1;
            System.out.println(number + " In position: " + positionNumber);
        }
    }

    /**
     * Method that takes in the parameter stringArray which would be an string array, validates it, and iterates through it
     * to then print every one of its elements.
     * @param stringArray array of strings.
     */
    public static void displayStringArray(String [] stringArray){
        validateArray(stringArray);
        int positionNumber = 0;
        for (String string : stringArray){
            positionNumber += 1;
            System.err.println(string + " In position: " +positionNumber);
        }
    }

    /**
     * Method that takes in the param numArray which would be an int array, validates it and iterates through it
     * to then calculate the average by adding all of the numbers in the array and then, using the counter variable
     * which stored the number of elements in the array, divides it and gets the average.
     * @param numArray array of integers.
     * @return returns the average calculated.
     */
    public static double arrayAverage(int[] numArray){
        validateArray(numArray);
        int counter = 0;
        int sum = 0;
        for (int number : numArray){
            counter +=1;
            sum += number;
        }
        double average = (double) sum / counter;
        return average;
    }

    /**
     * The following method takes in a prompt as a parameter which will be printed to the user, to later take in an integer from the console scanner,
     * before doing this hasNextInt was used so it is indeed checked to be an integer. Notice there is no exceptions being thrown so the program keeps looping.
     * @param prompt text prompt to the user.
     * @return returns the checked input.
     */
    public static int getValidInteger(String prompt){
        var con = System.console();
        Scanner sc = new Scanner(con.reader());
        boolean flag = true;
        int number = 0;
        while (flag){
            System.out.println(prompt);
            if (sc.hasNextInt()){
                number = sc.nextInt();
                flag = false;
            }
            else{
                System.err.println("The information entered is inappropriate");
                sc.next();
            }
        }
        return number;
    }

    /**
     * Method that finds the maximum number in a integer array, by iterating through it and checking if the previous value is greater than the current.
     * @param array array of integers.
     * @return returns the maximum integer in the array.
     */
    public static int findMax(int[] array) {
        validateArray(array);
        int max = 0;
        for (int number : array){
            if(number > max){
                max = number;
            }
        }
        return max;
    }

    /**
     * Method that uses compareToIgnoreCase to check which string in the array would be the last lexicographically.
     * @param array array of strings.
     * @return returns the the word that would be the last lexicographically.
     */
    public static String lastInAlphabet(String[] array){
        validateArray(array);
        String last = array[0];
        for (String word : array){
            if (last.compareToIgnoreCase(word) < 0){
                last = word;
            }
        }
        return last;
    }

    /**
     * Method that checks how much a number is repeated in an array
     * @param array the array that is iterated.
     * @param value the value that is checked.
     * @return how many time the value appears in the array.
     */
    public static int repeatedNumber(int[] array, int value){
        validateArray(array);
        int counter = 0;
        for (int number : array){
            if (number == value){
                counter +=1;
            }
        }
        return counter;

    }

    /**
     * Method that checks which number appears the most in an integer array. This is done by creating two variables, one to store the highest frequency,
     * which would be how many times a number appears and then highestFrequencyNumber which would be the number that repeats the highest.
     * This checks are being done through iterating and compare the previous highest to the next variable on the loop.
     * @param array arrays of integers.
     * @return returns the number with the highest frequency.
     */
    public static int highestFrequency(int[] array){
        validateArray(array);
        int highestFrequency = 0;
        int highestFrequencyNumber = 0;
        for (int number : array){
            int frequency = repeatedNumber(array, number);
            if (frequency > highestFrequency){
                highestFrequency = frequency;
                highestFrequencyNumber = number;
            }
        }
        return highestFrequencyNumber;
    }

    /**
     * Method that counts how many elements in an array are greater than a given number that is given as a parameter.
     * @param array array of integers.
     * @param value value to be contrasted.
     * @return returns how many elements are greater than the given value.
     */
    public static int countGreater(int[] array, double value){
        validateArray(array);
        int counter = 0;
        for (int number : array){
            if (number > value){
                counter += 1;
            }
        }
        return counter;
    }

    /**
     * Wrapper method that only takes in an array and counts how many numbers in the array are greater than the average.
     * @param array array of integers.
     * @return returns how many numbers are greater than the average.
     */
    public static int countGreater(int[] array){
        double average = arrayAverage(array);
        int count = countGreater(array, average);
        return count;
    }

    /**
     * Guard condition to check if the string array is valid.
     * @param array array of strings
     */
    private static void validateArray(String[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("The array cant be empty");
        }
    }

    /**
     * Guard condition to check if the int array is valid.
     * @param array array of integers.
     */
    private static void validateArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("The array cant be empty");
        }
    }

}
