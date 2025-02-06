package sorters;

/**
 * Implements Counting Sort sorting algorithm.
 * Counting Sort is a non-comparison-based sorting algorithm that works by
 * counting the occurrences of each element in the input array and using
 * arithmetic to determine the positions of elements in the sorted array.
 *
 * @author Zoestra Hammer
 */
public class CountingSort implements SortingInterface<Integer> {

  /**
   * Sorts the array using Counting Sort if the array has more than one element.
   * Preconditions: The array must contain non-negative integers (or elements
   * that can be mapped to non-negative integers).
   *
   * @param array The array to sort.
   */
  @Override
  public void sort(final Integer[] array) {
    if (array != null && array.length > 1) {
      countingSort(array);
    }
  }

  /**
   * Sorts the array using Counting Sort.
   * Preconditions: The array is not null and has at least two elements.
   * The array elements must be non-negative integers (or
   * elements that can be mapped to non-negative integers).
   *
   * @param input The array to be sorted.
   */

  private void countingSort(final Integer[] input) {
    Integer max = findMaxValue(input);
    int n = input.length;
    // create an array with length equal to the max value in the input array
    int[] count = new int[max + 1];
    // populate the array. the value of the entry from the input array is treated as the key
    // in a key-value pair. in the second array, the index is the key, and the value is
    // the number of times that value appears in the original array.
    for (int i = 0; i < n; i++) {
      count[input[i]] += 1;
    }

    // replace the values in the count array with the accumulated sum of all preceding values
    for (int i = 1; i < count.length; i++) {
      count[i] = count[i] + count[i - 1];
    }

    // abandon hope, all ye who enter here.
    // iterate through the input array in reverse. for each entry, check the corresponding
    // index in the count array. use the value found there to determine the index that the entry
    // should be placed into in the output array, then decrement that number by one.

    Integer[] output = new Integer[n];
    for (int i = n - 1; i >= 0; i--) {
      int currentValue = input[i];
      int targetIndex = count[currentValue] - 1;
      count[currentValue] -= 1;
      output[targetIndex] = currentValue;
    }

    // this algorithm is not normally an in-place algorithm, so we need to overwrite the input
    // to align with other testing expectations
    System.arraycopy(output, 0, input, 0, n);
  }


  /**
   * Finds the maximum value in the array.
   * Throws IllegalArgumentException if array contains negative integers.
   * Precondition: The array is not null and has at least one element.
   *
   * @param array The array to search.
   * @return The maximum value in the array.
   * @throws IllegalArgumentException if the array contains negative Integers.
   */
  private Integer findMaxValue(final Integer[] array) throws IllegalArgumentException {
    Integer max = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] < 0) {
        throw new IllegalArgumentException("All integers in array must be positive!");
      }
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }
}