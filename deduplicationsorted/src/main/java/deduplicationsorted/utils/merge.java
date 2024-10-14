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

    public int[] Sorted(int[] array1, int[] array2){
        int[] newArray = new int[array1.length + array2.length];
        int positionIndex1 = 0;
        int positionIndex2 = 0;
        for (int slot = 0; slot < newArray.length; slot++){
            if (array1[positionIndex1] >= array2[positionIndex2]){
                newArray[slot] = array1[positionIndex1++];
            }
            if (array2[positionIndex2] >= array1[positionIndex1]){
                newArray[slot] = array2[positionIndex2++];
            }
            if (positionIndex1 > array1.length){
                for (int num: array2){
                    newArray[slot] = array2[num];
                }
            }
            if (positionIndex2 > array2.length){
                for (int num: array1){
                    newArray[slot] = array1[num];
                }
            }

        }
        return newArray;
    }
}
