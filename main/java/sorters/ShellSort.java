package sorters;

/**
 * Implements Shell Sort sorting algorithm.
 *
 * @param <T> The data type for the array.
 * @author Zoestra Hammer
 */
public class ShellSort<T extends Comparable<T>>
        implements SortingInterface<T> {

  /**
   * Sorts the array using Shell Sort if the array has more than one element.
   *
   * @param array The array to sort.
   */

  @Override
  public void sort(final T[] array) {
    if (array == null || array.length <= 1) {
      return;
    }
    shellSort(array);
  }

  /**
   * Sorts the array using Shell Sort.
   * Preconditions: The array is not null and has at least two elements.
   *
   * @param array The array to be sorted.
   */
  private void shellSort(final T[] array) {
    int n = array.length;
    // outer loop sets the gap size and halves the gap each loop
    for (int gap = n / 2; gap > 0; gap /= 2) {
      // inner loop starts at array[gap], then moves through the rest of the array one at a time
      for (int i = gap; i < n; i++) {
        int current = i; // store index of the entry we are looking at
        // compare entry at current index to entry 1 gap distance below it. if current is greater,
        // swap the two.
        // this effectively divides the array into sub-arrays
        // continue doing this until we reach the beginning of the sub array, or if entry is sorted.
        while (current >= gap && array[current].compareTo(array[current - gap]) < 0) {
          swapElements(array, current, current - gap);
          current -= gap;
        }
      }
    }
  }

  /**
   * Swaps two elements in an array by their indices.
   * Precondition: The array must contain the indices.
   *
   * @param array       The array.
   * @param firstIndex  The first index.
   * @param secondIndex The second index.
   */
  private void swapElements(
          final T[] array, final int firstIndex, final int secondIndex) {
    // Swap the elements at the given indices.
    T temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }
}
