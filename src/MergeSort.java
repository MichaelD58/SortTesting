/**
 * This class represents a merge sort
 */
public class MergeSort {

    /**
     * This is the selection sort method that sorts the array passed into it
     *
     * @param unsorted the unsorted array of numbers
     * @param sorted the array that will be filled with the numbers in ascending order
     * @param length the length of both arrays
     * @param startTime the system time at the start of the sort
     * @param endTime the system time at the end of the sort
     * @param timeTaken the time required for the sort to complete
     */
    private int[] array;
    private int[] secondArray;
    private int length;
    private long startTime;
    private long endTime;
    private long timeTaken;

    /**
     * This is the method that calls the divide aspect of the sort as well as
     * creating the arrays and assigning a value to the length
     *
     * @returns timeTaken the time taken to sort the array
     */
    public long sort(int[] array) {
        startTime = System.nanoTime();
        this.array = array;
        length = array.length;
        this.secondArray = new int[length];
        divide(0, length - 1);
        endTime = System.nanoTime();
        timeTaken = endTime - startTime;
        return timeTaken;
    }

    /**
     * This method represents the divide aspect of the merge sort, where the array
     * is continually split into single integers before being built back into two
     * separate sides of the array before calling the conquer method that
     * puts joins both sides together in order
     *
     * @param l low value used to calculate when the array is sorted
     * @param h high value used to calculate when the array is sorted
     *          Array is sorted when the h is now smaller than l
     */
    private void divide(int l, int h) {
        // checks if the low value is smaller than the high value and if it
        //is then the array is not sorted. I
        if (l < h) {
            //Gets the middle position in the array
            int m = l + (h - l) / 2;
            //The following two method calls split the array into two halves.
            //The third method call then recombines these integers again, putting
            //them in two halves that themselves are in order
            divide(l, m);
            divide(m + 1, h);
            conquer(l, m, h);

        }
    }

    /**
     * This method represents the conquer aspect of the merge sort which fills the
     * temporary array with the two halves of the first array. This allows the first
     * array to be properly sorted by having its positions written over.
     */
    private void conquer(int l, int m, int h) {

        //Fills the second array with the first array's values.
        for (int i = l; i <= h; i++) {
            secondArray[i] = array[i];
        }

        int i = l;//The start of the first half of the second array
        int j = m + 1;//The start of the second half of the second array
        int u = l;//The start of the first array which will end up sorted

        //Checks to make sure both halves still have numbers that need sorted,
        //else the remainder of the half can just be added to the sorted array as
        //its already in order
        while (i <= m && j <= h) {
            //Finds which of the half's integer's contains the smallest number
            if (secondArray[i] <= secondArray[j]) {
                array[u] = secondArray[i];//Put into the first array in its correct position
                i++;
            } else {
                array[u] = secondArray[j];//Put into the first array in its correct position
                j++;
            }
            u++;//Next position of the first array is ready to hold its correct number
        }

        // Puts the remainder of the first half into the sorted array once the second half
        //has been fully put in
        while (i <= m) {
            array[u] = secondArray[i];
            u++;
            i++;
        }
    }
}