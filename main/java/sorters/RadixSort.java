package sorters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implements the <a href="https://en.wikipedia.org/wiki/Radix_sort">Radix Sort</a> algorithm.
 *
 * @author Joel Yang
 */
public class RadixSort implements SortingInterface<Integer> {

  /**
   * Sorts an array of positive Integer objects with Radix Sort given that the array exists
   * and has more than one element.
   *
   * @param array The array to be sorted.
   */
  public void sort(final Integer[] array) {
    if (array != null && array.length > 1) {
      radixSort(array);
    }
  }

  /**
   * Sorts the array using Radix Sort. Throws an IllegalArgumentException if array contains
   * negative Integer objects.
   *
   * @param array The array to be sorted.
   * @throws IllegalArgumentException if the array contains negative Integers.
   */
  private void radixSort(final Integer[] array) throws IllegalArgumentException {

    // Find the largest element in the array
    Integer max = array[0];

    for (int i = 0; i < array.length; i++) {
      if (array[i] < 0) {
        throw new IllegalArgumentException("All integers in array must be positive!");
      }
      if (array[i] > max) {
        max = array[i];
      }
    }

    // Get number of digits of max element
    int maxDigits = max.toString().length();

    // Create ten queues for sorting elements by next least significant digit
    int decimalBase = 10;
    List<Queue<Integer>> decimalDigits = digitQueues(decimalBase);

    // Sort elements lexicographically
    for (int i = 0; i < maxDigits; i++) {

      for (Integer currentElement : array) {

        // Get the next least significant digit of each element
        int nextLeastSignificantDigit = currentElement / (int) Math.pow(decimalBase, i)
                % decimalBase;

        // Insert into corresponding digit queue
        decimalDigits.get(nextLeastSignificantDigit).add(currentElement);
      }

      // Rearrange array, sorting by current significant digit
      int currentIndex = 0;

      for (int j = 0; j < decimalBase; j++) {

        // Empty out all queues into array in order
        Queue<Integer> currentQueue = decimalDigits.get(j);
        while (!currentQueue.isEmpty()) {
          array[currentIndex] = currentQueue.remove();
          currentIndex++;
        }
      }
    }
  }

  /**
   * Helper function for creating the queues used for each pass in radix sort.
   * Number of queues created depends on the number base being worked with,
   * i.e. 10 queues for base-10 counting.
   *
   * @param base The number base being worked with, the number of queues
   *             being created.
   * @return An ArrayList of Queues, each representing a digit in the base.
   */
  private List<Queue<Integer>> digitQueues(int base) {
    List<Queue<Integer>> decimalDigits = new ArrayList<>();
    for (int i = 0; i < base; i++) {
      decimalDigits.add(new LinkedList<>());
    }
    return decimalDigits;
  }
}