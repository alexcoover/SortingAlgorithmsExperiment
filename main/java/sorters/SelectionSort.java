package sorters;

/**
 * Implements selection sort with generic type array.
 *
 * @param <T> Data in received array.
 * @author Alex Coover.
 */

public class SelectionSort<T extends Comparable<T>> implements SortingInterface<T> {
  /**
   * Checks for null array before implementing primary method.
   *
   * @param array The array to sort.
   */
  public void sort(final T[] array) {
    if (array == null || array.length <= 1) {
      return;
    }
    selectionSort(array);
  }

  /**
   * Receives array and implements a selection sort with a nested loop,
   * finding the smallest value in the array and swapping it with the
   * first index of the array, then for the second index etc.
   *
   * @param array The array to sort.
   */

  private void selectionSort(T[] array) {
    // array length
    int n = array.length;

    // First loop iterates from first index to second to last.
    for (int i = 0; i < n - 1; i++) {
      // variable to track index of current minimum value
      int minIndex = i;

      // Second(nested) loop iterates from second index to last.
      for (int j = i + 1; j < n; j++) {
        // Compare current index to minimum index
        if (array[j].compareTo(array[minIndex]) < 0) {
          // minIndex set to current index if its value is smaller
          minIndex = j;
        }
      }

      // after nested loop finishes
      // store value to be swapped as a temp
      T temp = array[i];
      // set index i as minimum value found
      array[i] = array[minIndex];
      // move temp value to minimums former spot, completing the swap
      array[minIndex] = temp;
    }
  }
}
