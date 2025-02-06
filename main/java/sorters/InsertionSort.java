package sorters;

/**
 * Implements Insertion Sort sorting algorithm.
 *
 * @param <T> The data type for the array.
 * @author Zoestra Hammer
 */
public class InsertionSort<T extends Comparable<T>>
        implements SortingInterface<T> {

  /**
   * Sorts the array using Insertion Sort if the array has more than one element.
   *
   * @param array The array to sort.
   */
  @Override
  public void sort(final T[] array) {
    if (array != null && array.length > 1) {
      insertionSort(array);
    }
  }

  /**
   * Sorts the array using Insertion Sort.
   *
   * @param array The array to be sorted.
   */
  private void insertionSort(final T[] array) {
    int n = array.length;
    for (int i = 0; i < n; i++) {
      int index = i;
      while (index > 0 && array[index].compareTo(array[index - 1]) < 0) {
        swapElements(array, index, index - 1);
        index--;
      }
    }
  }

  /**
   * Swaps two elements in an array by their indices.
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
