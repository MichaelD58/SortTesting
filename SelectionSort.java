/**
 * This class represents a selection sort
 */
public class SelectionSort {

    /**
     * This is the selection sort method that sorts the array passed into it
     * @param length the length of the array passed into it
     * @param startTime the system time at the start of the sort
     * @param endTime the system time at the end of the sort
     * @param timeTaken the time required for the sort to complete
     */
    int length;
    long startTime;
    long endTime;
    long timeTaken;

    /**
     * This is the selection sort method that sorts the array passed into it
     * @returns timeTaken the time taken to sort the array
     */
    public long sort(int array[]) {
        length = array.length;

        startTime = System.nanoTime();
        for (int i = 0; i < length - 1; i++) {
            //The integer is automatically the smallest number at the beginning
            //of the unsorted section of the array at each pass through the loop
            int smallest = i;
            for (int j = i + 1; j < length; j++)//Checks the rest of the array for a smaller variable
                //If the current position in the array's value is smaller than the
                //current smallest value, then its set to the smallest variable
                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            //Once the pass through has been done, the smallest variable is moved
            //to the end of the sorted section, putting it in its correct place
            int temp;
            temp = array[smallest];
            array[smallest] = array[i];
            array[i] = temp;
        }
        endTime = System.nanoTime();
        timeTaken = endTime - startTime;
        return timeTaken;
    }
}
