package sorters;

/**
 * Implements Quick Sort using the median of threes method for pivot selection.
 *
 * @param <T> The data type for the array.
 * @author Aune Mitchell
 */
public class QuickSortWithMedianOfThreesPivot<
        T extends Comparable<T>>
        extends QuickSort<T> {

  /**
   * Select the pivot using the median of threes method.
   * Takes the first, last, and middle values, determines the median,
   * and returns the index value of the median.
   * If there are only two values, it returns the first value.
   *
   * @param array      The array to sort.
   * @param leftIndex  The left index of the array.
   * @param rightIndex The right index of the array.
   * @return The pivot index using the median of threes.
   * @throws IllegalArgumentException if array is empty or indices are invalid.
   */
  @Override
  protected int selectPivot(
          final T[] array, final int leftIndex, final int rightIndex) {
    // If there are less than three elements,
    // return the left index
    if (rightIndex - leftIndex < 2) {
      return leftIndex;
    }

    // Get the values from the first, middle, and last indexes
    int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
    T firstValue = array[leftIndex];
    T secondValue = array[middleIndex];
    T thirdValue = array[rightIndex];

    // Return the median value
    if (firstValue == secondValue && secondValue == thirdValue) {
      return middleIndex;
    } else if ((firstValue.compareTo((secondValue)) > 0)
            == (firstValue.compareTo(thirdValue) < 0)) {
      return leftIndex;
    } else if ((secondValue.compareTo((firstValue)) > 0)
            == (secondValue.compareTo(thirdValue) < 0)) {
      return middleIndex;
    } else {
      return rightIndex;
    }
  }
}
