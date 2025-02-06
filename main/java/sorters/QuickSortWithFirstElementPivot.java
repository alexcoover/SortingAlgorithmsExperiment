package sorters;

/**
 * Implements Quick Sort using the first element for pivot selection.
 *
 * @param <T> The data type for the array.
 * @author Aune Mitchell
 */
public class QuickSortWithFirstElementPivot<T extends Comparable<T>>
        extends QuickSort<T> {

  /**
   * Selects the first index of the array as the pivot.
   *
   * @param array      The array to sort.
   * @param leftIndex  The left index of the array.
   * @param rightIndex The right index of the array.
   * @return The pivot index (the left index of the array).
   * @throws IllegalArgumentException if array is empty or indices are invalid.
   */
  @Override
  protected int selectPivot(
          final T[] array, final int leftIndex, final int rightIndex) {
    // Return the left index
    return leftIndex;
  }
}
