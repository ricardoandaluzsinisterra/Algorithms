package utils;

public class Sort { 

    public void mergeSort(int[] data, int start, int end){
        //if there is more than one element in the section. Ask for explanation on the order this is being executed and what the pc does.
        if (start < end){
            int middlePosition = start + (end - start) / 2;
            mergeSort(data, start, middlePosition);
            mergeSort(data, middlePosition+1, end);
            merge(data, start, middlePosition, end);
        }

    }

    public void merge(int[] data, int start, int middlePosition, int end){
        int leftLength = (middlePosition - start) + 1;
        int rightLength = (end - middlePosition);

        //Create temp arrays for each side of section
        int [] leftArray = new int[leftLength];
        int [] rightArray = new int[rightLength];

        for (int i=0; i<leftLength; i++){
            leftArray[i] = data[start + i];
        }

        int leftPos = 0;
        int rightPos = 0;

        //Track where we are inserting into the arrray
        int mergedArrayPos = leftPos;

        while (leftPos < leftLength && rightPos < rightLength){
            if (leftArray[leftPos] <= rightArray[rightPos]){
                data[mergedArrayPos] = leftArray[leftPos];
                leftPos++;
            }
            else{
                data[mergedArrayPos] = rightArray[rightPos];
                rightPos++;
            }
            mergedArrayPos++;
        }

        while(leftPos < leftLength){
            data[mergedArrayPos] = leftArray[leftPos];
            leftPos++;
            mergedArrayPos++;
        }

        while(rightPos < rightLength){
            data[mergedArrayPos] = rightArray[rightPos];
            rightPos++;
            mergedArrayPos++;
        }
    }
}
