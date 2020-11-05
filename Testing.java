import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This is the main class where the random arrays are created, sort times are
 * collected, medians are found and sort medians are output to file.
 */
public class Testing {

    /**
     * @param maximum The maximum size of arraylist to be tested
     * @param increment Used to skip over a group of array sizes, this is used
     *                  to speed up larger maximum runs
     * @param numberForMedian Number of runs for each array size, the larger the
     *                        number, the more accurate the median
     * @param mergeSorts ArrayList used to store each time to complete a merge
     *                   sort for a specific array size
     * @param selectionSorts Arraylist used to store each time to complete a
     *                       selection sort for a specific array size
     * @param mergeSortMedianTimes ArrayList used to store all median times
     *                             for every array size acted on by the merge sort
     * @param selectionSortMedianTimes ArrayList used to store all median times
     *                                 for every array size acted on by the
     *                                 slection sort
     * @param selectionSort An instance of the SelectionSort class to run its sort
     *                      method
     * @param mergeSort An instance of the mergeSort class to run its sort method
     */
    private final static int maximum =10000;
    private final static int increment = 1;
    private final static int numberForMedian = 10;


    private static ArrayList<Long> mergeSorts = new ArrayList<>();
    private static ArrayList<Long> selectionSorts = new ArrayList<>();
    private static ArrayList<Long> mergeSortMedianTimes = new ArrayList<>();
    private static ArrayList<Long> selectionSortMedianTimes = new ArrayList<>();

    private static SelectionSort selectionSort = new SelectionSort();
    private static MergeSort mergeSort = new MergeSort();

     public static void main(String[] args) {

         //Loops until the maximum array size wanted is reached
         for(int i = 1; i <= maximum; i+= increment){
             //Loops until the number wanted for the median is reached
            for(int j = 0; j < numberForMedian; j++){
                //Creates an array of the current array size being looked at
                //and fills it with random numbers
                 int[] array = createArray(i);
                 //Runs the find median method for both sorts
                 findSortMedian(true, mergeSort.sort(array));
                 findSortMedian(false, selectionSort.sort(array));             }
         }
         //Once both for loops are completed the data is written to the file
         writeToFile(mergeSortMedianTimes, selectionSortMedianTimes);
    }

    /**
     * Method that fills an array with randomly generated numbers
     * @returns array A completed array full of randomly generated numbers
     */
    public static int[] createArray(int length){
        Random random = new Random();//Creates a new random object
        int[] array = new int[length];
        //For the position in the array being looked at, a random number between
        //0-1000 is assigned to it
        for (int i = 0; i <array.length; i++){
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    /**
     * Method that adds the time passed into it into specific arrayLists, until
     * the arrayList reaches the size needed for the median, then the median is found
     * and the arrayList is cleared for the next array size.
     */
    private static void findSortMedian(boolean type, long time){

        long mergeSortMedianTime; //Median time for the merge sort
        long selectionSortMedianTime;//Median time for the selection sort

        //Checks to see what sort is being used
         if(type) {
             mergeSorts.add(time);
             //Checks to see if the median number sought after has been reached
             if (mergeSorts.size() == numberForMedian) {
                 Collections.sort(mergeSorts);//Sorts the sort specific small time arrayList
                 mergeSortMedianTime = mergeSorts.get(numberForMedian / 2);//Selects the median time
                 mergeSortMedianTimes.add(mergeSortMedianTime);//Adds the median time to the sort specific large time ArrayList
                 mergeSorts.clear();
             }
         } else {
             selectionSorts.add(time);
             //Checks to see if the median number sought after has been reached
             if (selectionSorts.size() == numberForMedian) {
                 Collections.sort(selectionSorts);//Sorts the sort specific small time arrayList
                 selectionSortMedianTime = selectionSorts.get(numberForMedian / 2);//Selects the median time
                 selectionSortMedianTimes.add(selectionSortMedianTime);//Adds the median time to the sort specific large time ArrayList
                 selectionSorts.clear();
             }
         }
     }

    /**
     * Method that prints the size of the array, the median time taken for the
     * merge sort to finish and the median time taken for the selection sort to
     * finish to the file.
     */
    private static void writeToFile(ArrayList<Long> mergeSortTimes, ArrayList<Long> selectionSortTimes){
         try{
             FileWriter fw = new FileWriter("data.csv", false);
             //For every array size looked at
             for (int i = 1; i < (maximum/increment) ; i++){
                fw.write((i*increment) + "," + mergeSortTimes.get(i) + "," + selectionSortTimes.get(i) + "\n");
             }
             fw.close();//fw no longer needed
         } catch (IOException e){
             e.printStackTrace();
         }
    }

}
