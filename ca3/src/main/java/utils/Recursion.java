package utils;

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
        String str = Double.toString(number).replace("-", "").replace(".", "");
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

    

}
