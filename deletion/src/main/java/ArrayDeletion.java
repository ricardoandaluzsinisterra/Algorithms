
public class ArrayDeletion {
    public static void main(String[] args) {
        
    }


    //is not static because its a structure method
    //and we dont give it data because it uses its own
    //static means shared, there is only one instance of the method
    public int overwrite( int position){
        //Guard conditions
        validateParams(array, position);

        int deletedNumber = array[position];
        array[position] = 0;
        return deletedNumber;
    }

    private static void validateParams(int[] array, int position) {
        if (array == null){
            //have to raise/throw since try and catch is reserved for programmer to use. 
            //This exceptions will raise and therefore be caught by the try and catch of the programmer
            //Unchecked exception ¬
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        if (position < 0 || position > array.length){
            throw new ArrayIndexOutOfBoundsException("Supplied position must be within boundaries of array.");
        }
    }

    public static int[] resize(int[] array, int position){
        //More expensive given that every iteration of the loop has an if statement being checked.
        int [] nums2 = new int[array.length-1];
        int counter = 0;
        for (int number : array){
            if (number != array[position]){
                counter += 1;
                nums2[counter - 1] = number;
            }
        }
        return nums2;
    }

    public static int shift(int[] array, int position){
        int deletedNumber = array[position];
        for (int i = position; i < array.length-1; i++){
            array[i] = array[i+1];
        }
        array[array.length-1] = 0; 
        return deletedNumber;
    }
}
