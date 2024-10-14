package merge.utils;

public class Merge {
    public int[] Unsorted(int[] array1, int[] array2){
        //guard conditions missing
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
        //guard conditions missing
        int[] newArray = new int[array1.length + array2.length];
        int positionIndex1 = 0;
        int positionIndex2 = 0;
        for (int slot = 0; slot < newArray.length; slot++){
            if (array1[positionIndex1] >= array2[positionIndex2]){
                newArray[slot] = array1[positionIndex1++];
            }
             else if (array2[positionIndex2] >= array1[positionIndex1]){
                newArray[slot] = array2[positionIndex2++];
            }
            //best way possible for if/else statements
            else{
                if (positionIndex1 == array1.length){
                    for (int i=positionIndex2; i<newArray.length; i++){
                        newArray[slot] = array2[i];
                    }
                }
                else if (positionIndex2 == array2.length){
                    //best way
                    while (positionIndex1 < array1.length){
                        newArray[slot++] = array1[positionIndex1++];
                    }
                }
            }
        }
        return newArray;
    }
}
