package sorters;

/**
 * Implements the <a href="https://en.wikipedia.org/wiki/Bubble_sort">Bubble Sort</a> sorting algorithm.
 *
 * @param <T> The data type used in the array.
 * @author Joel Yang
 */
public class BubbleSort<T extends Comparable<T>> implements SortingInterface<T> {

  /**
   * Sorts the array with Bubble Sort given that the array exists and has more than one element.
   *
   * @param array The array to be sorted.
   */
  public void sort(final T[] array) {
    if (array != null && array.length > 1) {
      bubbleSort(array);
    }
  }

  /**
   * Sorts the array using Bubble Sort.
   *
   * @param array The array to be sorted.
   */
  private void bubbleSort(final T[] array) {
    int n = array.length;

    // If no swap was performed during a pass, we know that the array is already sorted
    // and can potentially exit early.
    boolean swapPerformed;

    // We do length - 1 passes
    for (int i = 0; i < n - 1; i++) {
      swapPerformed = false;

      // For each pass, compare every two consecutive elements
      // up to point where we have guaranteed the array is sorted
      for (int j = 0; j < n - 1 - i; j++) {

        // If a larger element exists that immediately precedes a
        // smaller element, swap the two
        if (array[j].compareTo(array[j + 1]) > 0) {
          T temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
          swapPerformed = true;
        }
      }

      // If no swap was performed, array is already sorted
      if (!swapPerformed) {
        break;
      }
    }
  }
}
