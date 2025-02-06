package sorters;

/**
 * Implements Quick Sort sorting algorithm.
 *
 * @param <T> The data type for the array.
 * @author Aune Mitchell
 */
public abstract class QuickSort<T extends Comparable<T>>
        implements SortingInterface<T> {

  /**
   * Sorts the array using Quick Sort if the array has more than one element.
   *
   * @param array The array to sort.
   */
  public void sort(final T[] array) {
    if (array != null && array.length > 1) {
      quickSort(array, 0, array.length - 1);
    }
  }

  /**
   * Sorts the array using Quick Sort.
   * Preconditions: valid left and right indices.
   * Runtime complexity: O(N^2), N is the number of elements in the array.
   * While technically O(N^2), this depends on the pivot selection.
   * Worst case: O(N^2), occurs when the smallest or largest element
   * is chosen as the pivot.
   * Average case: O(N log N), occurs when the median element
   * is chosen as the pivot.
   *
   * @param array      The array to be sorted.
   * @param leftIndex  The left index for the array.
   * @param rightIndex The right index for the array.
   */
  private void quickSort(
          final T[] array, final int leftIndex, final int rightIndex) {
    assert array.length > 0 && leftIndex >= 0 && rightIndex < array.length;

    // If there are at least two elements in the array
    if (leftIndex < rightIndex) {
      // Selects the pivot
      int initialPivotIndex = selectPivot(array, leftIndex, rightIndex);
      // Partitions the array using the pivot
      int partitionedPivotIndex = partitionArray(
              array, leftIndex, rightIndex, initialPivotIndex);
      // Recursively sorts the subarray to the left of the pivot
      quickSort(array, leftIndex, partitionedPivotIndex - 1);
      // Recursively sorts the subarray to the right of the pivot
      quickSort(array, partitionedPivotIndex + 1, rightIndex);
    }
  }

  /**
   * Selects the pivot from the range of provided indices.
   * Needs to be implemented by a class extending Quick Sort.
   *
   * @param array      The array to sort.
   * @param leftIndex  The left index of the array.
   * @param rightIndex The right index of the array.
   * @return The pivot index.
   */
  protected abstract int selectPivot(T[] array, int leftIndex, int rightIndex);

  /**
   * Partitions the array so that values less than the array
   * are to the left of the pivot.
   * Preconditions: valid left, right, and initial pivot indices.
   * Runtime complexity: O(N), N is the number of elements in the array segment.
   *
   * @param array             The array to sort.
   * @param leftIndex         The left index.
   * @param rightIndex        The right index.
   * @param initialPivotIndex The initial pivot index.
   * @return The new pivot index.
   */
  private int partitionArray(
          final T[] array, final int leftIndex,
          final int rightIndex, final int initialPivotIndex) {
    assert initialPivotIndex >= leftIndex && initialPivotIndex <= rightIndex;

    T pivotValue = array[initialPivotIndex];

    // Swap the pivot with the right index value
    swapTwoArrayElements(array, initialPivotIndex, rightIndex);

    // Set left and right markers
    int leftMarker = leftIndex;
    int rightMarker = rightIndex - 1;

    while (leftMarker <= rightMarker) {
      // Move left marker to the right while the markers have not crossed
      // and the value at left is not more than the pivot
      while (leftMarker <= rightMarker
              && array[leftMarker].compareTo(pivotValue) < 0) {
        leftMarker++;
      }

      // Move right marker to the left while the markers have not crossed
      // and the value at right is not less than the pivot {
      while (leftMarker <= rightMarker
              && array[rightMarker].compareTo(pivotValue) > 0) {
        rightMarker--;
      }

      // If the markers still haven't crossed,
      // swap the left and right marker index values
      if (leftMarker <= rightMarker) {
        swapTwoArrayElements(array, leftMarker, rightMarker);
        leftMarker++;
        rightMarker--;
      }
    }

    // Move pivot from end to the correct location
    swapTwoArrayElements(array, leftMarker, rightIndex);
    return leftMarker;
  }

  /**
   * Swaps two elements in an array by their indices.
   * Precondition: the array must contain the indices.
   *
   * @param array       The array.
   * @param firstIndex  The first index.
   * @param secondIndex The second index.
   */
  private void swapTwoArrayElements(
          final T[] array, final int firstIndex, final int secondIndex) {
    assert firstIndex >= 0 && secondIndex < array.length;

    // Set the first index value to a temp variable
    T temp = array[firstIndex];

    // Set the first index value to the second index's value
    array[firstIndex] = array[secondIndex];

    // Set the second index value to the temp value
    array[secondIndex] = temp;
  }
}
