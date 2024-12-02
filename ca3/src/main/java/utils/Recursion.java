package utils;

import java.io.File;

public class Recursion {
    public static int power(int x, int y){
        if (y == 0){
            System.out.println(x + "y==0");
            return 1;
        }
        if (y == 1){
            System.out.println(x + "y==1");
            return x;
        }
        System.out.println(x + "else");
        return x * power(x, y-1);
    }

    //Wrapper method
    public static boolean isPalindrome(double number) {
        String str;
        // Had to investigate on how to check a whole number so in the case of 202.0 the translation into string doesnt
        // return 2020.
        if (number == (long) number) {
            str = Long.toString((long) number);
        } else {
            str = Double.toString(number).replace(".", "");
        }
        str = str.replace("-", "");
        return isPalindromeRecursive(str, 0, str.length() - 1);
    }

    private static boolean isPalindromeRecursive(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (str.charAt(left) != str.charAt(right)) {
            return false; 
        }
        return isPalindromeRecursive(str, left + 1, right - 1);
    }

    public static boolean isSortedDescending(String[] array) {
        return isSortedDescendingRecursive(array, 0);
    }

    private static boolean isSortedDescendingRecursive(String[] array, int index) {
        if (index >= array.length - 1) {
            return true;
        }
        if (array[index].compareTo(array[index + 1]) < 0) {
            return false;
        }
        return isSortedDescendingRecursive(array, index + 1);
    }

    public static int countSubstringOccurrences(String text, String substring) {
        if (text.length() < substring.length()) {
            return 0;
        }
        //Used substring because couldn't think of any other way. I am having some health problems atm.
        if (text.substring(0, substring.length()).equals(substring)) {
            return 1 + countSubstringOccurrences(text.substring(1), substring);
        } else {
            return countSubstringOccurrences(text.substring(1), substring);
        }
    }

    public static void listFilesAndDirectories(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                    
                    if (file.isDirectory()) {
                        listFilesAndDirectories(file.getPath());
                    }
                }
            }
        } else {
            System.out.println("The provided path is not a valid directory.");
        }
    }
}
