import generators.ArrayGenerator;
import generators.ChartGenerator;
import generators.ReportGenerator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import sorters.BubbleSort;
import sorters.BucketSort;
import sorters.CountingSort;
import sorters.HeapSort;
import sorters.InsertionSort;
import sorters.MergeSort;
import sorters.QuickSortWithFirstElementPivot;
import sorters.QuickSortWithMedianOfThreesPivot;
import sorters.QuickSortWithRandomElementPivot;
import sorters.RadixSort;
import sorters.SelectionSort;
import sorters.ShellSort;
import sorters.SortingInterface;

/**
 * Tests all sorting algorithms and generates corresponding charts and CSVs.
 *
 * @author Alex Coover
 *
 * @author Aune Mitchell
 */
public class Main {

  private static ArrayList<SortingInterface<Integer>> sorters;
  private static HashMap<String, ArrayList<Integer[]>> arraysHashMap;
  private static final String CSV_PARENT_DIR = "experiment-results/data/";
  private static final String IMAGES_PARENT_DIR = "experiment-results/charts/";

  /**
   * Runs the experiment.
   *
   * @param args Program arguments, none expected.
   * @throws IOException If the file(s) cannot be created.
   */
  public static void main(String[] args) throws IOException {
    sorters = new ArrayList<>();
    arraysHashMap = new HashMap<>();
    // Fill the sorters ArrayList with each sorter
    generateSorters();
    // Fills the array HashMap with type and ArrayList of arrays for each type
    // (random, 50% sorted, 75% sorted, fully sorted, reverse sorted)
    generateArrays();
    // Runs the experiment
    runExperiment();
  }

  /**
   * Adds each sorting algorithm to the sorters ArrayList.
   */
  private static void generateSorters() {
    SortingInterface<Integer> bubbleSort = new BubbleSort<>();
    sorters.add(bubbleSort);

    SortingInterface<Integer> bucketSort = new BucketSort();
    sorters.add(bucketSort);

    SortingInterface<Integer> countingSort = new CountingSort();
    sorters.add(countingSort);

    SortingInterface<Integer> heapSort = new HeapSort<>();
    sorters.add(heapSort);

    SortingInterface<Integer> insertionSort = new InsertionSort<>();
    sorters.add(insertionSort);

    SortingInterface<Integer> mergeSort = new MergeSort<>();
    sorters.add(mergeSort);

    SortingInterface<Integer> quickSortWithFirstElementPivot =
            new QuickSortWithFirstElementPivot<>();
    sorters.add(quickSortWithFirstElementPivot);

    SortingInterface<Integer> quickSortWithMedianOfThreesPivot =
            new QuickSortWithMedianOfThreesPivot<>();
    sorters.add(quickSortWithMedianOfThreesPivot);

    SortingInterface<Integer> quickSortWithRandomElementPivot =
            new QuickSortWithRandomElementPivot<>();
    sorters.add(quickSortWithRandomElementPivot);

    SortingInterface<Integer> radixSort = new RadixSort();
    sorters.add(radixSort);

    SortingInterface<Integer> selectionSort = new SelectionSort<>();
    sorters.add(selectionSort);

    SortingInterface<Integer> shellSort = new ShellSort<>();
    sorters.add(shellSort);
  }

  /**
   * Adds each group of arrays to the arrays HashMap.
   * Uses the String array types as keys and ArrayList containing the arrays as values.
   */
  private static void generateArrays() {
    // Create arraylist for each category of array
    ArrayList<Integer[]> arraysRandom = new ArrayList<>();
    ArrayList<Integer[]> arrays50PercentSorted = new ArrayList<>();
    ArrayList<Integer[]> arrays75PercentSorted = new ArrayList<>();
    ArrayList<Integer[]> arraysFullySorted = new ArrayList<>();
    ArrayList<Integer[]> arraysReverseSorted = new ArrayList<>();

    // Generate arrays for each exponent and add to corresponding arraylist
    for (int i = 2; i <= 15; i++) {
      ArrayGenerator arrayGenerator = new ArrayGenerator(i);
      Integer[] arrayRandom = arrayGenerator.getArrayRandom();
      arraysRandom.add(arrayRandom);
      Integer[] array50PercentSorted = arrayGenerator.getArray50PercentSorted();
      arrays50PercentSorted.add(array50PercentSorted);
      Integer[] array75PercentSorted = arrayGenerator.getArray75PercentSorted();
      arrays75PercentSorted.add(array75PercentSorted);
      Integer[] arrayFullySorted = arrayGenerator.getArrayFullySorted();
      arraysFullySorted.add(arrayFullySorted);
      Integer[] arrayReverseSorted = arrayGenerator.getArrayReverseSorted();
      arraysReverseSorted.add(arrayReverseSorted);
    }

    // Add arraylists to arrays hashmap
    arraysHashMap.put("Random", arraysRandom);
    arraysHashMap.put("50% Sorted", arrays50PercentSorted);
    arraysHashMap.put("75% Sorted", arrays75PercentSorted);
    arraysHashMap.put("Ordered", arraysFullySorted);
    arraysHashMap.put("Reverse Ordered", arraysReverseSorted);
  }

  /**
   * Runs the experiment.
   *
   * @throws IOException If the file(s) cannot be created.
   */
  private static void runExperiment() throws IOException {
    System.out.println("Starting experiment.");

    // For each sorting algorithm
    for (SortingInterface<Integer> sorter : sorters) {
      // Get the name to use as part of file names
      String sorterName = sorter.getClass().getSimpleName();

      // For each array type
      for (String arrayType : arraysHashMap.keySet()) {
        System.out.println("\nStarting sorting with algorithm "
                + sorterName + " and arrays type " + arrayType + ".");

        // Build the data hashmap based on microseconds runtime for each array size
        HashMap<Integer, Double> dataHashMap = new HashMap<>();

        // For each array size, time how long it takes to sort
        for (Integer[] arrayToSort : arraysHashMap.get(arrayType)) {
          double durationMicroseconds;
          // Try sorting
          try {
            double startTime = System.nanoTime();
            sorter.sort(arrayToSort);
            double endTime = System.nanoTime();
            double durationNanoseconds = endTime - startTime;
            durationMicroseconds = durationNanoseconds / 1000;
          } catch (StackOverflowError e) {
            // Catch StackOverflowError, as this can happen
            // with QuickSort if it encounters worst-case O(N^2)
            // runtime on large 75% or fully sorted arrays
            // on computers without ample RAM
            System.out.println("Encountered stack overflow during sorting. "
                    + "Using -1000 microseconds as duration to indicate timeout.");
            durationMicroseconds = -1000;
          }
          // Add array size and sorting time to the data hashmap
          dataHashMap.put(arrayToSort.length, durationMicroseconds);
        }

        // Build the report
        ReportGenerator reportGenerator = new ReportGenerator();
        String combinedSorterNameAndArrayType = sorterName + "_" + arrayType;
        String csvFileName = combinedSorterNameAndArrayType + "_Data.csv";

        // Build the chart
        File csvForChart = reportGenerator.generateReport(dataHashMap, csvFileName, CSV_PARENT_DIR);
        ChartGenerator chartGenerator = new ChartGenerator();
        String chartFileName = combinedSorterNameAndArrayType + "_Chart.png";
        chartGenerator.createChartImageFromCsv(
                csvForChart, chartFileName,
                IMAGES_PARENT_DIR, arrayType);

        System.out.println("CSV and chart generation complete.");
      }
    }
    System.out.println("\nExperiment complete.");
  }
}
