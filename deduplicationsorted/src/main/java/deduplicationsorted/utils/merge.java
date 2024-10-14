package deduplicationsorted.utils;

public class merge {
    public int[] Unsorted(int[] array1, int[] array2){
        int[] newArray = new int[array1.length + array2.length];
        int positionIndex = 0;
        for (int num : array1){
            newArray[positionIndex] = array1[num];
            positionIndex++;
        }
        for (int num : array2){
            newArray[positionIndex] = array2[num];
            positionIndex++;
        }

        return newArray;
    }

}
